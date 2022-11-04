package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统菜单
 */
@ApiModel(value = "系统菜单")
@Data
@TableName(value = "sys_menu")
public class SysMenu implements Serializable {
    /**
     * ID
     */
    @TableId(value = "menu_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    @ExcelExport(value = "ID")

    private Long menuId;

    /**
     * 上级菜单ID
     */
    @TableField(value = "pid")
    @ApiModelProperty(value = "上级菜单ID")
    @ExcelExport(value = "上级菜单ID")

    private Long pid;

    /**
     * 子菜单数目
     */
    @TableField(value = "sub_count")
    @ApiModelProperty(value = "子菜单数目")
    @ExcelExport(value = "子菜单数目")
    private Integer subCount;

    /**
     * 菜单类型
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "菜单类型")
    @ExcelExport(value = "菜单类型")
    private Long type;

    /**
     * 菜单标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "菜单标题")
    @ExcelExport(value = "菜单标题")
    private String title;

    /**
     * 组件名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "组件名称")
    @ExcelExport(value = "组件名称")
    private String name;

    /**
     * 组件
     */
    @TableField(value = "locale")
    @ApiModelProperty(value = "语言包键名")
    @ExcelExport(value = "语言包键名")
    private String locale;

    /**
     * 排序
     */
    @TableField(value = "`order`")
    @ApiModelProperty(value = "排序")
    @ExcelExport(value = "排序")
    private Integer order;

    /**
     * 图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "图标")
    @ExcelExport(value = "图标")
    private String icon;

    /**
     * 链接地址
     */
    @TableField(value = "`path`")
    @ApiModelProperty(value = "链接地址")
    @ExcelExport(value = "链接地址")
    private String path;

    /**
     * 是否外链
     */
    @TableField(value = "i_frame")
    @ApiModelProperty(value = "是否外链")
    @ExcelExport(value = "是否外链")
    private Boolean iFrame;

    /**
     * 缓存
     */
    @TableField(value = "`cache`")
    @ApiModelProperty(value = "缓存")
    @ExcelExport(value = "缓存")
    private Boolean cache;

    /**
     * 隐藏
     */
    @TableField(value = "hidden")
    @ApiModelProperty(value = "隐藏")
    @ExcelExport(value = "隐藏")
    private Boolean hidden;

    /**
     * 权限
     */
    @TableField(value = "permission")
    @ApiModelProperty(value = "权限")
    @ExcelExport(value = "权限")
    private String permission;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建者")
    @ExcelExport(value = "创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value = "更新者")
    @ExcelExport(value = "更新者")
    private String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建日期")
    @ExcelExport(value = "创建日期")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    @ExcelExport(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_MENU_ID = "menu_id";

    public static final String COL_PID = "pid";

    public static final String COL_SUB_COUNT = "sub_count";

    public static final String COL_TYPE = "type";

    public static final String COL_TITLE = "title";

    public static final String COL_NAME = "name";

    public static final String COL_COMPONENT = "component";

    public static final String COL_MENU_SORT = "menu_sort";

    public static final String COL_ICON = "icon";

    public static final String COL_PATH = "path";

    public static final String COL_I_FRAME = "i_frame";

    public static final String COL_CACHE = "cache";

    public static final String COL_HIDDEN = "hidden";

    public static final String COL_PERMISSION = "permission";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}