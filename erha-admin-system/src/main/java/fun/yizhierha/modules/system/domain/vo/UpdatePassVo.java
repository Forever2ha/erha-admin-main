package fun.yizhierha.modules.system.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("修改密码vo")
public class UpdatePassVo {

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

}
