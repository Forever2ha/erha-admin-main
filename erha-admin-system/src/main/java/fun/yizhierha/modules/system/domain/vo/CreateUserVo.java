package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
@ApiModel("创建用户Vo")
public class CreateUserVo {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2,max = 20,message = "用户名长度在2到20个字符")
    private String username;

    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不能为空")
    @Size(min = 2,max = 20,message = "昵称长度在2到20个字符")
    private String nickName;

    @ApiModelProperty("部门id")
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @ApiModelProperty("电话号")
    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "1[3-9][0-9]{9}",message = "电话号码格式错误")
    private String phone;

    @ApiModelProperty("邮箱")
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为空")
    @Size(min = 1,max = 1,message = "性别只能是一个字符")
    private String gender;

    @ApiModelProperty("状态")
    @NotNull(message = "用户状态不能为空")
    private Boolean enabled;

    @ApiModelProperty("岗位id")
    @NotNull(message = "岗位id不能为空")
    private List<Long> jobIds;

    @ApiModelProperty("角色id")
    @NotNull(message = "角色id不能为空")
    private List<Long> roleIds;

    @ApiModelProperty(hidden = true)
    private String password;
    @ApiModelProperty(hidden = true)
    private String createBy;
}