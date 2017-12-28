package codegen.configxml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;

import codegen.util.Utils;


public class ConfigurationParser {
	
	GenConfiguration genConfiguration=new GenConfiguration();
	
	public GenConfiguration parse(InputStream xmlPath) throws Exception{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		Document document = null;
		DocumentBuilder docBuilder = null;
		docBuilder = factory.newDocumentBuilder();
		DefaultHandler handler=new DefaultHandler();
		docBuilder.setEntityResolver(handler);
		docBuilder.setErrorHandler(handler);
		
		document = docBuilder.parse(xmlPath);
		
		Element rootEl=document.getDocumentElement();
		
		NodeList children=rootEl.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node instanceof Element) {

				Element element = (Element)node;
				if (elementNameMatch(element, "jdbc")) {
					genConfiguration.setJdbcDriver(element.getAttribute("driver"));
					genConfiguration.setJdbcUrl(element.getAttribute("url"));
					genConfiguration.setJdbcUser(element.getAttribute("user"));
					genConfiguration.setJdbcPassword(element.getAttribute("password"));
//					genConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
//					genConfiguration.setJdbcUrl("dbc:mysql://192.168.1.93:3306/wmtzt_04?characterEncoding=utf-8");
//					genConfiguration.setJdbcUser("test_pt_wm_04");
//					genConfiguration.setJdbcPassword("Jmybdn783");
					
				} else if (elementNameMatch(element, "baseDir")) {
					genConfiguration.setBaseDir(element.getTextContent().trim());
					
				} else if (elementNameMatch(element, "base")) {
					parseBase(element);
					
				} else if(elementNameMatch(element, "tables")) {
					genConfiguration.setTablePattern(element.getAttribute("pattern"));
					TableConfig tableConfig = new TableConfig();
					tableConfig.setController(booleanValue(element.getAttribute("controller"), false));
					tableConfig.setService(booleanValue(element.getAttribute("service"), false));
					tableConfig.setDao(booleanValue(element.getAttribute("dao"), false));
					tableConfig.setJsp(booleanValue(element.getAttribute("jsp"), false));
					tableConfig.setParam(booleanValue(element.getAttribute("param"), false));
					tableConfig.setMapperExt(booleanValue(element.getAttribute("mapperExt"), false));
					
					tableConfig.setPage(booleanValue(element.getAttribute("page"), false));
					tableConfig.setList(booleanValue(element.getAttribute("list"), false));
					genConfiguration.setDefaultTableConfig(tableConfig);
					
					String excludesStr=element.getAttribute("excludes");
					if(excludesStr!=null && excludesStr.length()>0){
						String[] excludesArr=excludesStr.split(",");
						for(String str: excludesArr){
							str=str.trim();
							if(!Utils.isBlank(str)){
								genConfiguration.getExcludeTables().add(str);
							}
						}
					}
					
					parseTables(element);
				}
				
			}
		}
		return genConfiguration;
	}
	
	private void parseTables(Element el) {
		
		List<TableConfig> tables = new ArrayList<TableConfig>();
		TableConfig defaultTableConfig = genConfiguration.getDefaultTableConfig();
		NodeList children = el.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				if (elementNameMatch(element, "table")) {
					TableConfig tableConfig = new TableConfig();
					tableConfig.setName(element.getAttribute("name"));
					tableConfig.setEntityName(element.getAttribute("entityName"));
					
					tableConfig.setController(booleanValue(element.getAttribute("controller"), defaultTableConfig.isController()));
					tableConfig.setService(booleanValue(element.getAttribute("service"), defaultTableConfig.isService()));
					tableConfig.setDao(booleanValue(element.getAttribute("dao"), defaultTableConfig.isDao()));
					tableConfig.setJsp(booleanValue(element.getAttribute("jsp"), defaultTableConfig.isJsp()));
					tableConfig.setParam(booleanValue(element.getAttribute("param"), defaultTableConfig.isParam()));
					tableConfig.setMapperExt(booleanValue(element.getAttribute("mapperExt"), defaultTableConfig.isMapperExt()));
					
					tableConfig.setPage(booleanValue(element.getAttribute("page"), defaultTableConfig.isPage()));
					tableConfig.setList(booleanValue(element.getAttribute("list"), defaultTableConfig.isList()));
					
					tables.add(tables.size(), tableConfig);
				}
			}
		}
		genConfiguration.setTables(tables);
	}

	private void parseBase(Element el){
		BaseConfig baseConfig=new BaseConfig();
		NodeList children=el.getChildNodes();
		for(int i=0; i<children.getLength(); i++){
			Node node=children.item(i);
			if(node instanceof Element){
				Element element=(Element)node;
				if (elementNameMatch(element, "resource")) {
					baseConfig.setResource(element.getTextContent().trim());
					
				} else if (elementNameMatch(element, "service")) {
					baseConfig.setService(element.getTextContent().trim());
					
				} else if (elementNameMatch(element, "mybatis")) {
					parseBaseMybatis(element, baseConfig);
					
				} else if (elementNameMatch(element, "mapperExt")) {
					baseConfig.setMapperExt(element.getTextContent().trim());
					
				} else if (elementNameMatch(element, "mapperXmlExt")) {
					baseConfig.setMapperXmlExt(element.getTextContent().trim());
					
				} else if (elementNameMatch(element, "controller")) {
					baseConfig.setController(element.getTextContent().trim());
					
				} else if (elementNameMatch(element, "jsp")) {
					baseConfig.setJsp(element.getTextContent().trim());
					
				} else if (elementNameMatch(element, "param")) {
					baseConfig.setParam(element.getTextContent().trim());
				}
			}
		}
		genConfiguration.setBase(baseConfig);
	}
	
	private void parseBaseMybatis(Element el, BaseConfig baseConfig){
		NodeList children=el.getChildNodes();
		for(int i=0; i<children.getLength(); i++){
			Node node=children.item(i);
			if(node instanceof Element){
				Element element=(Element)node;
				if(elementNameMatch(element, "mapper")){
					baseConfig.setMapper(element.getTextContent().trim());
				}else if(elementNameMatch(element, "mapperXml")){
					baseConfig.setMapperXml(element.getTextContent().trim());
				}else if(elementNameMatch(element, "mapperModel")){
					baseConfig.setMapperModel(element.getTextContent().trim());
				}
			}
		}
	}
	
	public static boolean nodeNameMatch(Node node, String desiredName) {
		return (desiredName.equals(node.getNodeName()) || desiredName.equals(node.getLocalName()));
	}
	
	public static boolean elementNameMatch(Node node, String desiredName) {
		return (node instanceof Element && nodeNameMatch(node, desiredName));
	}
	
	public static boolean booleanValue(String value, boolean defaultValue) {
		if(value==null || value.length()==0){
			return defaultValue;
		}
		return (value.equalsIgnoreCase("true"));
	}

}
