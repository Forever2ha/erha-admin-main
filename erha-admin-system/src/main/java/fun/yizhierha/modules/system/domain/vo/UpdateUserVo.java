package fun.yizhierha.modules.system.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("修改用户Vo")
public class UpdateUserVo extends UpdateVo {

    @Size(min = 2,max = 20,message = "昵称长度在2到20个字符")
    @ApiModelProperty("昵称")
    private String nickName;

    @Size(min = 1,max = 1,message = "性别只能是一个字符")
    @ApiModelProperty("性别")
    private String gender;

    @Pattern(regexp = "1[3-9][0-9]{9}",message = "电话号码格式错误")
    @ApiModelProperty("电话")
    private String phone;

    @Email(message = "邮箱格式错误")
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("岗位编号")
    private List<Long> jobIds;

    @ApiModelProperty("角色编号")
    private List<Long> roleIds;

    @ApiModelProperty("部门编号")
    private Long deptId;

    @ApiModelProperty("状态")
    private Boolean enabled;
}
