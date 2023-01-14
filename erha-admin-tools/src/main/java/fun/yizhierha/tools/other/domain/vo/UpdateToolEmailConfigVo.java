package fun.yizhierha.tools.other.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateToolEmailConfigVo extends UpdateVo{

    @ApiModelProperty(value = "收件人")
    private String fromUser;

    @ApiModelProperty(value = "邮件服务器SMTP地址")
    private String host;

    @ApiModelProperty(value = "密码")
    private String pass;

    @ApiModelProperty(value = "端口")
    private String port;

    @ApiModelProperty(value = "发件者用户名")
    private String user;

}