package ${package}.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#if queryHasTimestamp >
import java.sql.Timestamp;
</#if>
<#if queryHasBigDecimal >
import java.math.BigDecimal;
</#if>

@Data
@ApiModel("查询${apiAliasShort}vo")
public class Retrieve${className}Vo{

    <#list queryColumns as col>
        <#if col.formType == '下拉框[多选]'>
    @ApiModelProperty(value = "${col.remark}")
    private String[] ${col.changeColumnName};
        <#else>
    @ApiModelProperty("${col.remark}")
    private ${col.columnType} ${col.changeColumnName};
        </#if>

    </#list>
    <#list betweens as bet>
      <#if bet.columnType == 'Timestamp'>
    @ApiModelProperty(value = "起始${bet.remark}",dataType = "String",example = "2022-03-03")
    private Timestamp start${bet.capitalColumnName};

    @ApiModelProperty(value = "结束${bet.remark}",dataType = "String",example = "2022-03-22")
    private Timestamp end${bet.capitalColumnName};
          <#else >
    @ApiModelProperty(value = "起始${bet.remark}")
    private ${bet.columnType} start${bet.capitalColumnName};

    @ApiModelProperty(value = "结束${bet.remark}")
    private ${bet.columnType} end${bet.capitalColumnName};
      </#if>

    </#list>
}