package fun.yizhierha.tools.other.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;


/** generated by EH-Admin
* @author erha
* @date Sun Jan 08 13:57:58 CST 2023
**/
@ApiModel(value = "七牛云")
@Data
@TableName(value = "tool_qiniu_config")
public class ToolQiniuConfig implements Serializable{

    @TableId(value = "config_id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    @ExcelExport("id")
    private Long id;

    @TableField(value = "access_key")
    @ApiModelProperty(value = "accessKey")
    @ExcelExport("accessKey")
    @JSONField(serialize = false)
    private String accessKey ;

    @TableField(value = "bucket")
    @ApiModelProperty(value = "Bucket 识别符")
    @ExcelExport("Bucket 识别符")
    private String bucket;

    @TableField(value = "host")
    @ApiModelProperty(value = "外链域名")
    @ExcelExport("外链域名")
    private String host;

    @TableField(value = "secret_key")
    @ApiModelProperty(value = "secretKey")
    @ExcelExport("secretKey")
    @JSONField(serialize = false)
    private String secretKey;

    @TableField(value = "type")
    @ApiModelProperty(value = "空间类型")
    @ExcelExport("空间类型")
    private String type;

    @TableField(value = "zone")
    @ApiModelProperty(value = "机房")
    @ExcelExport("机房")
    private String zone;

    @TableField(value = "active")
    @ApiModelProperty(value = "是否激活")
    @ExcelExport("是否激活")
    private Boolean active;

    private static final long serialVersionUID = 1L;

    public static final String COL_CONFIG_ID = "config_id";

    public static final String COL_ACCESS_KEY = "access_key";

    public static final String COL_BUCKET = "bucket";

    public static final String COL_HOST = "host";

    public static final String COL_SECRET_KEY = "secret_key";

    public static final String COL_TYPE = "type";

    public static final String COL_ZONE = "zone";

    public static final String COL_ACTIVE = "active";

}