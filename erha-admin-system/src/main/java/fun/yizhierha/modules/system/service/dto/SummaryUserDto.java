package fun.yizhierha.modules.system.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class SummaryUserDto extends BaseDTO {

    @ApiModelProperty("用户id")
    @ExcelExport(value = "用户id")
    private Long id;

    @ApiModelProperty("部门id")
    @ExcelExport(value = "部门id")
    private Long deptId;

    @ApiModelProperty("部门名称")
    @ExcelExport(value = "部门名称")
    private String deptName;

    @ApiModelProperty("用户名")
    @ExcelExport(value = "用户名")
    private String username;

    @ApiModelProperty("昵称")
    @ExcelExport(value = "昵称")
    private String nickName;

    @ApiModelProperty("邮件")
    @ExcelExport(value = "邮件")
    private String email;

    @ApiModelProperty("电话")
    @ExcelExport(value = "电话")
    private String phone;

    @ApiModelProperty("性别")
    @ExcelExport(value = "性别")
    private String gender;

    @ApiModelProperty("头像照片文件名")
    @ExcelExport(value = "头像照片文件名")
    private String avatarName;

    @ApiModelProperty("头像文件地址")
    @ExcelExport(value = "头像文件地址")
    private String avatarPath;

    @JSONField(serialize = false)
    private String password;//密码

    private Boolean enabled;//是否启用

    @JSONField(serialize = false)
    private Boolean isAdmin = false;//是不是admin

    @ApiModelProperty("密码重置的时间")
    @ExcelExport(value = "密码重置的时间")
    private Date pwdResetTime;

    @ApiModelProperty("当前角色id")
    @ExcelExport(value = "当前角色id")
    private Long nowRoleId;

    private Set<Role> roles;
    private Set<Job> jobs;

    @ExcelExport("角色")
    public String role (){
        Iterator<Role> iterator = roles.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()){
            Role role = iterator.next();
            sb.append("[").append(role.id).append("]").append(role.name).append("  ");
        }
        return sb.toString();
    }

    @ExcelExport("岗位")
    public String job (){
        Iterator<Job> iterator = jobs.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()){
            Job job = iterator.next();
            sb.append(job.name).append("  ");
        }
        return sb.toString();
    }



    @Data
    @ApiModel
    public static class Role{

        @ApiModelProperty
        private String name;
        @ApiModelProperty
        private Long id;
        @ApiModelProperty
        private Integer level;
        @ApiModelProperty
        private String dataScope;
    }

    @Data
    @ApiModel
    public static class Job{
        @ApiModelProperty
        private String name;
        @ApiModelProperty
        private Long id;
    }
}
