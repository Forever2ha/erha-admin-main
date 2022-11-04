package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
    * 代码生成字段信息存储
    */
@ApiModel(value="代码生成字段信息存储")
@Data
@TableName(value = "code_column_config")
public class CodeColumnConfig implements Serializable {
    /**
     * ID
     */
    @TableId(value = "column_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long columnId;

    @TableField(value = "`table_name`")
    @ApiModelProperty(value="")
    private String tableName;

    @TableField(value = "`column_name`")
    @ApiModelProperty(value="")
    private String columnName;

    @TableField(value = "column_type")
    @ApiModelProperty(value="")
    private String columnType;

    @TableField(value = "dict_name")
    @ApiModelProperty(value="")
    private String dictName;

    @TableField(value = "extra")
    @ApiModelProperty(value="")
    private String extra;

    @TableField(value = "form_show")
    @ApiModelProperty(value="")
    private Boolean formShow;

    @TableField(value = "form_type")
    @ApiModelProperty(value="")
    private String formType;

    @TableField(value = "`key_type`")
    @ApiModelProperty(value="")
    private String keyType;

    @TableField(value = "list_show")
    @ApiModelProperty(value="")
    private Boolean listShow;

    @TableField(value = "not_null")
    @ApiModelProperty(value="")
    private Boolean notNull;

    @TableField(value = "query_type")
    @ApiModelProperty(value="")
    private String queryType;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    @TableField(value = "date_annotation")
    @ApiModelProperty(value="")
    private String dateAnnotation;

    private static final long serialVersionUID = 1L;

    public static final String COL_COLUMN_ID = "column_id";

    public static final String COL_TABLE_NAME = "table_name";

    public static final String COL_COLUMN_NAME = "column_name";

    public static final String COL_COLUMN_TYPE = "column_type";

    public static final String COL_DICT_NAME = "dict_name";

    public static final String COL_EXTRA = "extra";

    public static final String COL_FORM_SHOW = "form_show";

    public static final String COL_FORM_TYPE = "form_type";

    public static final String COL_KEY_TYPE = "key_type";

    public static final String COL_LIST_SHOW = "list_show";

    public static final String COL_NOT_NULL = "not_null";

    public static final String COL_QUERY_TYPE = "query_type";

    public static final String COL_REMARK = "remark";

    public static final String COL_DATE_ANNOTATION = "date_annotation";
}