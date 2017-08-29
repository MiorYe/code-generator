<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${cfg.base.mapperExt}.${table.tableMeta.entityName}Dao">
  <resultMap id="BaseResultMap" type="${cfg.base.mapperModel}.${table.tableMeta.entityName}" extends="${cfg.base.mapper}.${table.tableMeta.entityName}Mapper.BaseResultMap">
   
  </resultMap>
  
  <sql id="Base_Column_List">
  	<#list table.tableMeta.primaryKeys as key>
  		${key.columnName},
  	</#list>
  	<#list table.tableMeta.columns as column>
  		${column.columnName}<#if column_has_next>,</#if>
  	</#list>
  </sql>
  
		
  <#if table.page == true>
  <select id="getListByPage" resultMap="BaseResultMap" parameterType="map">
    select 
    <include refid="Base_Column_List" />
    from ${table.tableMeta.tableName} LIMIT ${'#'}{pager.start}, ${'#'}{pager.pageSize}
  </select>
  
  <select id="count" resultType="int" parameterType="map">
    select count(1) from ${table.tableMeta.tableName}
  </select>
  </#if>
  
  <#if table.list == true>
  <select id="getList" resultMap="BaseResultMap" parameterType="map">
    select 
    <include refid="Base_Column_List" />
    from ${table.tableMeta.tableName}
  </select>
  </#if>
  
  
  <select id="selectBySelective" resultMap="BaseResultMap" parameterType="${cfg.base.mapperModel}.${table.tableMeta.entityName}">
    select 
    <include refid="Base_Column_List" />
    from ${table.tableMeta.tableName}
  </select>
  
  
  
</mapper>