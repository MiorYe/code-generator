package ${cfg.base.service};


import java.util.List;

<#if table.page == true || table.list == true>
import java.util.List;
import ${cfg.base.param}.${table.tableMeta.entityName}Param;
</#if>



import ${cfg.base.mapperModel}.${table.tableMeta.entityName};

/**
 * ${table.tableMeta.entityName}Service.java
 * @autor qianlishui
 * @version 1.0.0
 */
public interface ${table.tableMeta.entityName}Service {
	
	/**
	 * 新增
	 */
    public int save(${table.tableMeta.entityName} ${table.tableMeta.referenceName});
    
    /**
	 * 修改
	 */
    public int update(${table.tableMeta.entityName} ${table.tableMeta.referenceName});
    
    /**
	 * 删除
	 */
    public int delete(Long id);
    
    /**
	 * 根据id查询
	 */
    public ${table.tableMeta.entityName} get${table.tableMeta.entityName}(Long id);
    
    <#if table.page == true>
    /**
	 * 分页查询
	 */
    public List<${table.tableMeta.entityName}> getListByPage(PageBean pageBean, ${table.tableMeta.entityName}Param param);
    
    </#if>
    <#if table.list == true>
    /**
	 * 查询所有
	 */
    public List<${table.tableMeta.entityName}> getList(${table.tableMeta.entityName}Param param);
    
    </#if>
    
    /**
	 *根据javaBean对象 查询
	 */
    public List< ${table.tableMeta.entityName}> selectBySelective(${table.tableMeta.entityName} record);
    
}
