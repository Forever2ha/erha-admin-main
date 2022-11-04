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
 * 数据字典详情
 */
@ApiModel(value = "数据字典详情")
@Data
@TableName(value = "sys_dict_detail")
public class SysDictDetail implements Serializable {
    /**
     * ID
     */
    @TableId(value = "detail_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private Long detailId;

    /**
     * 字典id
     */
    @TableField(value = "dict_id")
    @ApiModelProperty(value = "字典id")
    private Long dictId;

    /**
     * 字典标签
     */
    @TableField(value = "`label`")
    @ApiModelProperty(value = "字典标签")
    private String label;

    /**
     * 字典值
     */
    @TableField(value = "`value`")
    @ApiModelProperty(value = "字典值")
    private String value;

    /**
     * 排序
     */
    @TableField(value = "dict_sort")
    @ApiModelProperty(value = "排序")
    private Integer dictSort;

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

    public static final String COL_DETAIL_ID = "detail_id";

    public static final String COL_DICT_ID = "dict_id";

    public static final String COL_LABEL = "label";

    public static final String COL_VALUE = "value";

    public static final String COL_DICT_SORT = "dict_sort";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}