package ${package}.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

<#list columns as col>
    <#if col.columnType == 'BigDecimal'>
import java.math.BigDecimal;
        <#break>
    </#if>
</#list>
<#list columns as col>
    <#if col.columnType == 'Timestamp'>
import java.sql.Timestamp;
        <#break>
    </#if>
</#list>

/** generated by EH-Admin
* @author ${author}
* @date ${date}
**/
@ApiModel(value = "${apiAliasShort}")
@Data
@TableName(value = "${tableName}")
public class ${className} implements Serializable{

    <#list columns as col>
    <#if col.columnKey == 'PRI'>
    @TableId(value = "${col.columnName}", type = <#if auto>IdType.AUTO<#else >IdType.INPUT</#if>)
    @ApiModelProperty(value = "ID")
    @ExcelExport("id")
    private Long id;

    <#elseif col.formType == '下拉框[多选]'>
    @TableField(value = "${col.columnName}")
    @ApiModelProperty(value = "${col.remark}")
    @ExcelExport("${col.remark}")
    private String ${col.changeColumnName};

    <#else >
    @TableField(value = "${col.columnName}")
    @ApiModelProperty(value = "${col.remark}")
    @ExcelExport("${col.remark}")
    private ${col.columnType} ${col.changeColumnName};

    </#if>
    </#list>
    private static final long serialVersionUID = 1L;

    <#list columns as col>
    public static final String COL_${col.allCaps} = "${col.columnName}";

    </#list>
}