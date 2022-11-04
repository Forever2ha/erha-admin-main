package fun.yizhierha.modules.system.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import fun.yizhierha.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ApiModel
public class UserDto extends BaseDTO implements Serializable {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("部门id")
    private Long deptId;

     @ApiModelProperty("部门名称")
    private String deptName;

     @ApiModelProperty("用户名")
    private String username;

     @ApiModelProperty("昵称")
    private String nickName;

     @ApiModelProperty("邮件")
    private String email;

     @ApiModelProperty("电话")
    private String phone;

     @ApiModelProperty("性别")
    private String gender;

     @ApiModelProperty("头像照片文件名")
    private String avatarName;

     @ApiModelProperty("头像文件地址")
    private String avatarPath;

    @JSONField(serialize = false)
    private String password;//密码

    private Boolean enabled;//是否启用
    @JSONField(serialize = false)
    private Boolean isAdmin = false;//是不是admin

     @ApiModelProperty("密码重置的时间")
    private Date pwdResetTime;

     @ApiModelProperty("当前角色id")
    private Long nowRoleId;



}