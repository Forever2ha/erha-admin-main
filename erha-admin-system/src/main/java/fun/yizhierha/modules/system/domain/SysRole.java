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
 * 角色表
 */
@ApiModel(value = "角色表")
@Data
@TableName(value = "sys_role")
public class SysRole implements Serializable {
    /**
     * ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Long roleId;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 角色级别
     */
    @TableField(value = "`level`")
    @ApiModelProperty(value = "角色级别")
    private Integer level;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 数据权限
     */
    @TableField(value = "data_scope")
    @ApiModelProperty(value = "数据权限")
    private String dataScope;

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

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_NAME = "name";

    public static final String COL_LEVEL = "level";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_DATA_SCOPE = "data_scope";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}