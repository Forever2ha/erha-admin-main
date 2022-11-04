package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户
 */
@ApiModel(value = "系统用户")
@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    /**
     * ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private Long userId;

    /**
     * 用户有多个角色时，选一个作为当前角色
     */
    @ApiModelProperty(value = "用户有多个角色时，选一个作为当前角色")
    private Long nowRoleId;

    /**
     * 部门名称
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value = "部门名称")
    private Long deptId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 性别
     */
    @TableField(value = "gender")
    @ApiModelProperty(value = "性别")
    private String gender;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 头像地址
     */
    @TableField(value = "avatar_name")
    @ApiModelProperty(value = "头像地址")
    private String avatarName;

    /**
     * 头像真实路径
     */
    @TableField(value = "avatar_path")
    @ApiModelProperty(value = "头像真实路径")
    private String avatarPath;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 是否为admin账号
     */
    @TableField(value = "is_admin")
    @ApiModelProperty(value = "是否为admin账号")
    private Boolean isAdmin;

    /**
     * 状态：1启用、0禁用
     */
    @TableField(value = "enabled")
    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Boolean enabled;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /**
     * 修改密码的时间
     */
    @TableField(value = "pwd_reset_time")
    @ApiModelProperty(value = "修改密码的时间")
    private Date pwdResetTime;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_NOW_ROLE_ID = "now_role_id";

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_USERNAME = "username";

    public static final String COL_NICK_NAME = "nick_name";

    public static final String COL_GENDER = "gender";

    public static final String COL_PHONE = "phone";

    public static final String COL_EMAIL = "email";

    public static final String COL_AVATAR_NAME = "avatar_name";

    public static final String COL_AVATAR_PATH = "avatar_path";

    public static final String COL_PASSWORD = "password";

    public static final String COL_IS_ADMIN = "is_admin";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_PWD_RESET_TIME = "pwd_reset_time";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}