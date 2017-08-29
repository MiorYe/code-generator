package codegen.configxml;

import codegen.meta.TableMeta;

public class TableConfig {

	/** 表名 */
	String name;
	
	/** 对应实体名称 */
	String entityName;
	
	/** 是否生成controller */
	boolean controller;
	
	/** 是否生成service */
	boolean service;
	
	/** 是否生成dao */
	boolean dao;
	
	/** 是否生成参数实体 */
	boolean param;
	
	/** 是否生成jsp */
	boolean jsp;
	
	/** service是否生成分页方法 */
	boolean page;
	
	/** service是否生成list方法 */
	boolean list;
	
	/** 是否生成mybatis扩展相关类 */
	boolean mapperExt;

	/** 数据表元信息 */
	TableMeta tableMeta;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}

	public boolean isDao() {
		return dao;
	}

	public boolean isController() {
		return controller;
	}

	public void setController(boolean controller) {
		this.controller = controller;
	}

	public void setDao(boolean dao) {
		this.dao = dao;
	}

	public boolean isJsp() {
		return jsp;
	}

	public void setJsp(boolean jsp) {
		this.jsp = jsp;
	}

	public boolean isParam() {
		return param;
	}

	public void setParam(boolean param) {
		this.param = param;
	}

	public boolean isPage() {
		return page;
	}

	public void setPage(boolean page) {
		this.page = page;
	}

	public TableMeta getTableMeta() {
		return tableMeta;
	}

	public void setTableMeta(TableMeta tableMeta) {
		this.tableMeta = tableMeta;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}

	public boolean isMapperExt() {
		return mapperExt;
	}

	public void setMapperExt(boolean mapperExt) {
		this.mapperExt = mapperExt;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public String toString() {
		return "TableConfig [name=" + name + ", entityName=" + entityName
				+ ", service=" + service + ", dao=" + dao + ", page=" + page
				+ ", list=" + list + ", mapperExt=" + mapperExt
				+ ", tableMeta=" + tableMeta + "]";
	}

}
