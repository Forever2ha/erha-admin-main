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
    * 邮箱配置
    */
@ApiModel(value="邮箱配置")
@Data
@TableName(value = "tool_email_config")
public class ToolEmailConfig implements Serializable {
    /**
     * ID
     */
    @TableId(value = "config_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long configId;

    /**
     * 收件人
     */
    @TableField(value = "from_user")
    @ApiModelProperty(value="收件人")
    private String fromUser;

    /**
     * 邮件服务器SMTP地址
     */
    @TableField(value = "`host`")
    @ApiModelProperty(value="邮件服务器SMTP地址")
    private String host;

    /**
     * 密码
     */
    @TableField(value = "pass")
    @ApiModelProperty(value="密码")
    private String pass;

    /**
     * 端口
     */
    @TableField(value = "port")
    @ApiModelProperty(value="端口")
    private String port;

    /**
     * 发件者用户名
     */
    @TableField(value = "`user`")
    @ApiModelProperty(value="发件者用户名")
    private String user;

    private static final long serialVersionUID = 1L;

    public static final String COL_CONFIG_ID = "config_id";

    public static final String COL_FROM_USER = "from_user";

    public static final String COL_HOST = "host";

    public static final String COL_PASS = "pass";

    public static final String COL_PORT = "port";

    public static final String COL_USER = "user";
}