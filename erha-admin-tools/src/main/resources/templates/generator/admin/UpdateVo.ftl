package ${package}.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

<#list columns as col>
    <#if col.columnType == 'BigDecimal' && col.formShow>
import java.math.BigDecimal;
        <#break>
    </#if>
</#list>
<#list columns as col>
    <#if col.columnType == 'Timestamp' && col.formShow>
import java.sql.Timestamp;
        <#break>
    </#if>
</#list>


@EqualsAndHashCode(callSuper = true)
@Data
public class Update${className}Vo extends UpdateVo{

    <#list columns as col>
    <#if col.formShow>
      <#if col.formType == '下拉框[多选]'>
    @ApiModelProperty(value = "${col.remark}")
    private String[] ${col.changeColumnName};
          <#else>
    @ApiModelProperty(value = "${col.remark}")
    private ${col.columnType} ${col.changeColumnName};
      </#if>

    </#if>
    </#list>
}