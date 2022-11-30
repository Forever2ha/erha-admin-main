package ${package}.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
<#list columns as col>
    <#if col.istNotNull && col.formShow>
import javax.validation.constraints.NotNull;
        <#break>
    </#if>
</#list>

@Data
public class Create${className}Vo{

<#list columns as col>
    <#if col.formShow>
      <#if col.formType == '下拉框[多选]'>
          <#if col.istNotNull>
    @NotNull(message = "新增时[${col.remark}]不能为空")
          </#if>
    @ApiModelProperty(value = "${col.remark}")
    private String[] ${col.changeColumnName};
          <#else>
              <#if col.istNotNull>
    @NotNull(message = "新增时[${col.remark}]不能为空")
              </#if>
    @ApiModelProperty(value = "${col.remark}")
    private ${col.columnType} ${col.changeColumnName};
      </#if>

    </#if>
</#list>
}