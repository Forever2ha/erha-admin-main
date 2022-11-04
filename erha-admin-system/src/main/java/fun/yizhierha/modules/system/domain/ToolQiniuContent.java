package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    * 七牛云文件存储
    */
@ApiModel(value="七牛云文件存储")
@Data
@TableName(value = "tool_qiniu_content")
public class ToolQiniuContent implements Serializable {
    /**
     * ID
     */
    @TableId(value = "content_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long contentId;

    /**
     * Bucket 识别符
     */
    @TableField(value = "bucket")
    @ApiModelProperty(value="Bucket 识别符")
    private String bucket;

    /**
     * 文件名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="文件名称")
    private String name;

    /**
     * 文件大小
     */
    @TableField(value = "`size`")
    @ApiModelProperty(value="文件大小")
    private String size;

    /**
     * 文件类型：私有或公开
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="文件类型：私有或公开")
    private String type;

    /**
     * 文件url
     */
    @TableField(value = "url")
    @ApiModelProperty(value="文件url")
    private String url;

    /**
     * 文件后缀
     */
    @TableField(value = "suffix")
    @ApiModelProperty(value="文件后缀")
    private String suffix;

    /**
     * 上传或同步的时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="上传或同步的时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_CONTENT_ID = "content_id";

    public static final String COL_BUCKET = "bucket";

    public static final String COL_NAME = "name";

    public static final String COL_SIZE = "size";

    public static final String COL_TYPE = "type";

    public static final String COL_URL = "url";

    public static final String COL_SUFFIX = "suffix";

    public static final String COL_UPDATE_TIME = "update_time";
}