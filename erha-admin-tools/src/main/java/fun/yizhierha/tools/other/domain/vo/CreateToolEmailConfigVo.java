package fun.yizhierha.tools.other.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateToolEmailConfigVo{

    @NotNull(message = "新增时[收件人]不能为空")
    @ApiModelProperty(value = "收件人")
    private String fromUser;

    @NotNull(message = "新增时[邮件服务器SMTP地址]不能为空")
    @ApiModelProperty(value = "邮件服务器SMTP地址")
    private String host;

    @NotNull(message = "新增时[密码]不能为空")
    @ApiModelProperty(value = "密码")
    private String pass;

    @NotNull(message = "新增时[端口]不能为空")
    @ApiModelProperty(value = "端口")
    private String port;

    @NotNull(message = "新增时[发件者用户名]不能为空")
    @ApiModelProperty(value = "发件者用户名")
    private String user;

}