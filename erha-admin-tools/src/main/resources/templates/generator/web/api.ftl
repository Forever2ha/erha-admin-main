export interface ${className} {
<#list columns as col>
  <#if col.columnShow>
  ${col.changeColumnName}: <#if col.columnType == 'Long' || col.columnType == 'Integer' || col.columnType == 'Float' || col.columnType == 'Double' || col.columnType == 'Long' || col.columnType == 'BigDecimal'>number<#elseif col.columnType == 'Boolean'>boolean<#else >string</#if>;
  </#if>
</#list>
}