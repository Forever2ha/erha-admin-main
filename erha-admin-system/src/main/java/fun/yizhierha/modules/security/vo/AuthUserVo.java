
package fun.yizhierha.modules.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel
public class AuthUserVo {

    @NotBlank(message = "用户名不能为空且必须有实际字符")
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空且必须有实际字符")
    private String password;

    @ApiModelProperty("验证码值")
    @NotBlank(message = "验证码不能为空且必须有实际字符")
    private String captcha;

    @ApiModelProperty("验证码uuid")
    @NotBlank(message = "验证码uuid不能为空且必须有实际字符")
    private String uuid ;
}
