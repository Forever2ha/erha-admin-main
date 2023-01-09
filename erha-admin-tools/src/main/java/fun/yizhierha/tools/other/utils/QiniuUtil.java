package fun.yizhierha.tools.other.utils;

import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.utils.file.FileUtil;
import fun.yizhierha.tools.other.domain.ToolQiniuConfig;
import fun.yizhierha.tools.other.domain.ToolQiniuContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 二哈
 */
@Slf4j
public class QiniuUtil {


    public static void upload(MultipartFile file, ToolQiniuConfig toolQiniuConfig, ToolQiniuContent toolQiniuContent) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String upToken = getUpToken(toolQiniuConfig);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
        String name = FileUtil.getFileNameNoEx(file.getOriginalFilename());
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
        String nowStr = "-" + format.format(date);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key =  name + nowStr + "." + suffix;
        toolQiniuContent.setName(key);
        try {
            byte[] uploadBytes = file.getBytes();
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = JSONUtil.toBean(response.bodyString(),DefaultPutRet.class);
            } catch (QiniuException ex) {
                Response r = ex.response;
                throw new BadRequestException(r.toString());
            }
        } catch (IOException exx) {
            //ignore
        }

    }

    private static String getUpToken(ToolQiniuConfig toolQiniuConfig) {
        if (toolQiniuConfig == null) return "";
        Auth auth = Auth.create(toolQiniuConfig.getAccessKey(), toolQiniuConfig.getSecretKey());
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\"," +
                "\"hash\":\"$(etag)\"," +
                "\"bucket\":\"$(bucket)\"," +
                "\"fsize\":\"$(fsize)\"," +
                "\"fname\":$(fname)}" +
                "");
        return auth.uploadToken(toolQiniuConfig.getBucket(),null,3600,putPolicy);
    }

    public static String getUrl(String fileName, ToolQiniuConfig toolQiniuConfig) {
        if (toolQiniuConfig == null) return "";
        String domainOfBucket = "http://" +toolQiniuConfig.getHost();
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        String accessKey = toolQiniuConfig.getAccessKey();
        String secretKey = toolQiniuConfig.getSecretKey();
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;// 1小时，可以自定义链接过期时间
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }

    public static Map<String,Object> delFiles(List<ToolQiniuContent> delList, ToolQiniuConfig toolQiniuConfig) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释

        String accessKey = toolQiniuConfig.getAccessKey();
        String secretKey = toolQiniuConfig.getSecretKey();
        String bucket = toolQiniuConfig.getBucket();

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);

        Map<String,Long> fileNameToId = new HashMap<>();


        HashMap<String, Object> resMap = new HashMap<>();
        try {
            String[] keyList = new String[delList.size()];
            //单次批量请求的文件数量不得超过1000
            for (int i = 0; i < delList.size(); i++) {
                ToolQiniuContent content = delList.get(i);
                keyList[i] = content.getName();
                fileNameToId.put(content.getName(),content.getId());
            }

            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keyList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);

            ArrayList<Long> successID = new ArrayList<>();

            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                if (status.code == 200) {
                    successID.add(fileNameToId.get(key));
                }else {
                    resMap.put(key,status.data.error);
                }
            }
            resMap.put("successId",successID);
        } catch (QiniuException ex) {
            throw new BadRequestException(ex.response.toString());
        }
        return resMap;
    }
}
