package ${cfg.base.mapperExt};

import java.util.List;
<#if table.page == true || table.list == true>
import java.util.Map;
</#if>
import ${cfg.base.mapper}.${table.tableMeta.entityName}Mapper;
import ${cfg.base.mapperModel}.${table.tableMeta.entityName};


public interface ${table.tableMeta.entityName}Dao extends ${table.tableMeta.entityName}Mapper {
	
	<#if table.page == true>
	
	public int count(Map<String, Object> params);

	public List<${table.tableMeta.entityName}> getListByPage(Map<String, Object> params);

	</#if>
	<#if table.list == true>
	public List<${table.tableMeta.entityName}> getList(Map<String, Object> params);

	</#if>
	
	public List< ${table.tableMeta.entityName}> selectBySelective(${table.tableMeta.entityName} record);
	
}