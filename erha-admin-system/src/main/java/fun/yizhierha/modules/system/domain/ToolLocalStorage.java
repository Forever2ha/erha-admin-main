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
    * 本地存储
    */
@ApiModel(value="本地存储")
@Data
@TableName(value = "tool_local_storage")
public class ToolLocalStorage implements Serializable {
    /**
     * ID
     */
    @TableId(value = "storage_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long storageId;

    /**
     * 文件真实的名称
     */
    @TableField(value = "real_name")
    @ApiModelProperty(value="文件真实的名称")
    private String realName;

    /**
     * 文件名
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="文件名")
    private String name;

    /**
     * 后缀
     */
    @TableField(value = "suffix")
    @ApiModelProperty(value="后缀")
    private String suffix;

    /**
     * 路径
     */
    @TableField(value = "`path`")
    @ApiModelProperty(value="路径")
    private String path;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="类型")
    private String type;

    /**
     * 大小
     */
    @TableField(value = "`size`")
    @ApiModelProperty(value="大小")
    private String size;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value="更新者")
    private String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建日期")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_STORAGE_ID = "storage_id";

    public static final String COL_REAL_NAME = "real_name";

    public static final String COL_NAME = "name";

    public static final String COL_SUFFIX = "suffix";

    public static final String COL_PATH = "path";

    public static final String COL_TYPE = "type";

    public static final String COL_SIZE = "size";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}