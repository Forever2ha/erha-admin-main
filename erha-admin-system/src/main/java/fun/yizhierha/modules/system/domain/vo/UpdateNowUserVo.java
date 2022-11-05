package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ApiModel("更新当前用户vo")
public class UpdateNowUserVo {

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 2,max = 20,message = "昵称长度在2到20个字符")
    private String nickName;

    @Pattern(regexp = "1[3-9][0-9]{9}",message = "电话号码格式错误")
    private String phone;
}
