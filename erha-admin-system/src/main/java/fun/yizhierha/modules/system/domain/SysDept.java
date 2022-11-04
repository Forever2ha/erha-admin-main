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
 * 部门
 */
@ApiModel(value = "部门")
@Data
@TableName(value = "sys_dept")
public class SysDept implements Serializable {
    /**
     * ID
     */
    @TableId(value = "dept_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private Long deptId;

    /**
     * 上级部门
     */
    @TableField(value = "pid")
    @ApiModelProperty(value = "上级部门")
    private Long pid;

    /**
     * 子部门数目
     */
    @TableField(value = "sub_count")
    @ApiModelProperty(value = "子部门数目")
    private Integer subCount;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 排序
     */
    @TableField(value = "dept_sort")
    @ApiModelProperty(value = "排序")
    private Integer deptSort;

    /**
     * 状态
     */
    @TableField(value = "enabled")
    @ApiModelProperty(value = "状态")
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

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_PID = "pid";

    public static final String COL_SUB_COUNT = "sub_count";

    public static final String COL_NAME = "name";

    public static final String COL_DEPT_SORT = "dept_sort";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}