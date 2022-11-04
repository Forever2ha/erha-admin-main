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
    * 支付宝配置类
    */
@ApiModel(value="支付宝配置类")
@Data
@TableName(value = "tool_alipay_config")
public class ToolAlipayConfig implements Serializable {
    /**
     * ID
     */
    @TableId(value = "config_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long configId;

    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    @ApiModelProperty(value="应用ID")
    private String appId;

    /**
     * 编码
     */
    @TableField(value = "charset")
    @ApiModelProperty(value="编码")
    private String charset;

    /**
     * 类型 固定格式json
     */
    @TableField(value = "format")
    @ApiModelProperty(value="类型 固定格式json")
    private String format;

    /**
     * 网关地址
     */
    @TableField(value = "gateway_url")
    @ApiModelProperty(value="网关地址")
    private String gatewayUrl;

    /**
     * 异步回调
     */
    @TableField(value = "notify_url")
    @ApiModelProperty(value="异步回调")
    private String notifyUrl;

    /**
     * 私钥
     */
    @TableField(value = "private_key")
    @ApiModelProperty(value="私钥")
    private String privateKey;

    /**
     * 公钥
     */
    @TableField(value = "public_key")
    @ApiModelProperty(value="公钥")
    private String publicKey;

    /**
     * 回调地址
     */
    @TableField(value = "return_url")
    @ApiModelProperty(value="回调地址")
    private String returnUrl;

    /**
     * 签名方式
     */
    @TableField(value = "sign_type")
    @ApiModelProperty(value="签名方式")
    private String signType;

    /**
     * 商户号
     */
    @TableField(value = "sys_service_provider_id")
    @ApiModelProperty(value="商户号")
    private String sysServiceProviderId;

    private static final long serialVersionUID = 1L;

    public static final String COL_CONFIG_ID = "config_id";

    public static final String COL_APP_ID = "app_id";

    public static final String COL_CHARSET = "charset";

    public static final String COL_FORMAT = "format";

    public static final String COL_GATEWAY_URL = "gateway_url";

    public static final String COL_NOTIFY_URL = "notify_url";

    public static final String COL_PRIVATE_KEY = "private_key";

    public static final String COL_PUBLIC_KEY = "public_key";

    public static final String COL_RETURN_URL = "return_url";

    public static final String COL_SIGN_TYPE = "sign_type";

    public static final String COL_SYS_SERVICE_PROVIDER_ID = "sys_service_provider_id";
}