<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.mapper.${className}Mapper">
  <resultMap id="BaseResultMap" type="${package}.domain.${className}">
    <!--generated by EH-Admin @date ${date}-->
    <!--@Table ${tableName}-->
    <#list columns as col>
    <#if col.columnKey == "PRI">
    <id column="${col.columnName}" property="id" />
    <#else>
    <result column="${col.columnName}" property="${col.changeColumnName}" />
    </#if>
    </#list>
  </resultMap>
  <sql id="Base_Column_List">
    <#list columns as col>
        `${col.columnName}`<#sep>,
    </#list>

  </sql>
</mapper>