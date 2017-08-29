package codegen.configxml;

public class BaseConfig {
	
	String resource;
	
	/** 控制器生成路径 */
	String controller;
	
	/** 业务类生成路径 */
	String service;
	
	/** jsp页面生成路径 */
	String jsp;
	
	/** 参数实体生成路径 */
	String param;
	
	/** mybatis映射dao生成路径 */
	String mapper;
	
	/** 扩展dao生成路径 */
	String mapperExt;
	
	/** mybatis映射Xml生成路径 */
	String mapperXml;
	
	/** 扩展mybatisXML生成路径 */
	String mapperXmlExt;
	
	/** entity路径 */
	String mapperModel;
	
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getJsp() {
		return jsp;
	}
	public void setJsp(String jsp) {
		this.jsp = jsp;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMapper() {
		return mapper;
	}
	public void setMapper(String mapper) {
		this.mapper = mapper;
	}
	public String getMapperXml() {
		return mapperXml;
	}
	public void setMapperXml(String mapperXml) {
		this.mapperXml = mapperXml;
	}
	public String getMapperModel() {
		return mapperModel;
	}
	public void setMapperModel(String mapperModel) {
		this.mapperModel = mapperModel;
	}
	
	public String getMapperExt() {
		return mapperExt;
	}
	public void setMapperExt(String mapperExt) {
		this.mapperExt = mapperExt;
	}
	public String getMapperXmlExt() {
		return mapperXmlExt;
	}
	public void setMapperXmlExt(String mapperXmlExt) {
		this.mapperXmlExt = mapperXmlExt;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	@Override
	public String toString() {
		return "BaseConfig [resource=" + resource + ", service=" + service
				+ ", mapperExt=" + mapperExt + ", mapperXmlExt=" + mapperXmlExt
				+ ", mapper=" + mapper + ", mapperXml=" + mapperXml
				+ ", mapperModel=" + mapperModel + "]";
	}

	

}
