package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
    * 七牛云配置
    */
@ApiModel(value="七牛云配置")
@Data
@TableName(value = "tool_qiniu_config")
public class ToolQiniuConfig implements Serializable {
    /**
     * ID
     */
    @TableId(value = "config_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long configId;

    /**
     * accessKey
     */
    @TableField(value = "access_key")
    @ApiModelProperty(value="accessKey")
    private String accessKey;

    /**
     * Bucket 识别符
     */
    @TableField(value = "bucket")
    @ApiModelProperty(value="Bucket 识别符")
    private String bucket;

    /**
     * 外链域名
     */
    @TableField(value = "`host`")
    @ApiModelProperty(value="外链域名")
    private String host;

    /**
     * secretKey
     */
    @TableField(value = "secret_key")
    @ApiModelProperty(value="secretKey")
    private String secretKey;

    /**
     * 空间类型
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="空间类型")
    private String type;

    /**
     * 机房
     */
    @TableField(value = "`zone`")
    @ApiModelProperty(value="机房")
    private String zone;

    private static final long serialVersionUID = 1L;

    public static final String COL_CONFIG_ID = "config_id";

    public static final String COL_ACCESS_KEY = "access_key";

    public static final String COL_BUCKET = "bucket";

    public static final String COL_HOST = "host";

    public static final String COL_SECRET_KEY = "secret_key";

    public static final String COL_TYPE = "type";

    public static final String COL_ZONE = "zone";
}