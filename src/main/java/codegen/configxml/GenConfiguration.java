package codegen.configxml;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import codegen.meta.ColumnMeta;
import codegen.meta.DataTypeMapping;
import codegen.meta.PrimaryKeyMeta;
import codegen.meta.TableMeta;


public class GenConfiguration {
	
	String jdbcDriver;
	String jdbcUrl;
	String jdbcUser;
	String jdbcPassword;
	
	String baseDir;
	BaseConfig base;
	List<TableConfig> tables;
	List<String> excludeTables=new ArrayList<String>();
	TableConfig defaultTableConfig;
	String tablePattern;
	Map<String, TableMeta> allTableInfo;
	
	public static GenConfiguration parse(URL xmlPath) throws Exception{
		return parse(xmlPath.openStream());
	}
	public static GenConfiguration parse(InputStream inputStream) throws Exception{
		GenConfiguration genConfiguration=new ConfigurationParser().parse(inputStream);
		genConfiguration.init();
		Map<String, TableMeta> allTableInfo=genConfiguration.findAllTableInfo();
		genConfiguration.allTableInfo=allTableInfo;
		TableConfig defaultTableConfig = genConfiguration.getDefaultTableConfig();
		if(genConfiguration.tablePattern!=null && genConfiguration.tablePattern.length()>0){
			String tablePattern=genConfiguration.tablePattern.replace("*", ".*");
			for(String tableName: allTableInfo.keySet()){
				if(tableName.matches(tablePattern)){
					boolean isExclude=false;
					for(String exclude: genConfiguration.excludeTables){
						if(tableName.equalsIgnoreCase(exclude)){
							isExclude=true;
							break;
						}
					}
					if(isExclude){
						continue;
					}
					boolean has=false;
					for(TableConfig tableConfig: genConfiguration.tables){
						if(tableConfig.getName().equalsIgnoreCase(tableName)){
							has=true;
							break;
						}
					}
					if(!has){
						TableConfig tableConfig=new TableConfig();
						tableConfig.setName(tableName);
						tableConfig.setDao(defaultTableConfig.isDao());
						tableConfig.setPage(defaultTableConfig.isPage());
						tableConfig.setService(defaultTableConfig.isService());
						tableConfig.setList(defaultTableConfig.isList());
						tableConfig.setMapperExt(defaultTableConfig.isMapperExt());
						genConfiguration.tables.add(tableConfig);
					}
				}
			}
		}
		
		for(TableConfig tableConfig: genConfiguration.tables){
			TableMeta tableMeta = allTableInfo.get(tableConfig.getName());
			if (tableConfig.getEntityName() != null && tableConfig.getEntityName().length()>0){
				tableMeta.setEntityName(tableConfig.getEntityName());
				tableMeta.setReferenceName(tableConfig.getEntityName().substring(0, 1).toLowerCase() + tableConfig.getEntityName().substring(1));
			}
			tableConfig.setTableMeta(tableMeta);
		}
		return genConfiguration;
	}
		
	public String getJdbcDriver() {
		return jdbcDriver;
	}
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getJdbcUser() {
		return jdbcUser;
	}
	public void setJdbcUser(String jdbcUser) {
		this.jdbcUser = jdbcUser;
	}
	public String getJdbcPassword() {
		return jdbcPassword;
	}
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
	public String getBaseDir() {
		return baseDir;
	}
	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}
	public BaseConfig getBase() {
		return base;
	}
	public void setBase(BaseConfig base) {
		this.base = base;
	}
	public List<TableConfig> getTables() {
		return tables;
	}
	public void setTables(List<TableConfig> tables) {
		this.tables = tables;
	}
	public String getTablePattern() {
		return tablePattern;
	}
	public void setTablePattern(String tablePattern) {
		this.tablePattern = tablePattern;
	}
	
	Connection connection;
	
	void init() throws Exception{
		Class.forName(this.getJdbcDriver());
		connection=DriverManager.getConnection(this.jdbcUrl, this.getJdbcUser(), this.getJdbcPassword());
	}
	
	Map<String, TableMeta> findAllTableInfo()throws Exception{
		
		DatabaseMetaData m_DBMetaData = connection.getMetaData(); 
		ResultSet colRet = m_DBMetaData.getTables(null, "%", "%", null); 
		
		Map<String, TableMeta> tableMetas=new HashMap<String, TableMeta>();
		while(colRet.next()) {
			String tableName=colRet.getString("TABLE_NAME");
			TableMeta tableMeta=getTableInfo(tableName);
			tableMetas.put(tableName, tableMeta);
		}
		this.allTableInfo=tableMetas;
		
		return tableMetas;
	}
	
	TableMeta getTableInfo(String tableName)  throws Exception{
		DatabaseMetaData m_DBMetaData = connection.getMetaData(); 
		
		TableMeta tableMeta = new TableMeta();
		tableMeta.setTableName(tableName);
		
		String name = formatName(tableName);
		String displayName = formatDisplayName(tableName);
		tableMeta.setName(name);
		tableMeta.setDisplayName(displayName);
		tableMeta.setEntityName(name.substring(0, 1).toUpperCase() + name.substring(1));
		tableMeta.setReferenceName(name.substring(0, 1).toLowerCase() + name.substring(1));
		
		ResultSet primaryKeyResultSet = m_DBMetaData.getPrimaryKeys(null, null, tableName);  
		List<String> primaryKeyNames=new ArrayList<String>();
		
		while(primaryKeyResultSet.next()){  
			primaryKeyNames.add(primaryKeyResultSet.getString("COLUMN_NAME"));			
		}
		
		ResultSet colRet = m_DBMetaData.getColumns(null,"%", tableName,"%"); 
		
		while(colRet.next()) {
			boolean isKey=false;
			String colName=colRet.getString("COLUMN_NAME");
			name=formatName(colName);
			displayName=formatDisplayName(colName);
			String fieldName=(name.substring(0, 1).toUpperCase()+name.substring(1));
			int dataType=colRet.getInt("DATA_TYPE");
			String javaType=DataTypeMapping.getJavaTypeName(dataType);
			
			for(String keyName: primaryKeyNames ){
				if(keyName.equalsIgnoreCase(colName)){
					isKey=true;
					break;
				}
			}
			if(isKey){
				PrimaryKeyMeta primaryKey=new PrimaryKeyMeta();
				primaryKey.setName(colName);
				primaryKey.setDataType(colRet.getInt("DATA_TYPE"));
				primaryKey.setJdbcTypeName(colRet.getString("TYPE_NAME"));
				primaryKey.setColumnName(colName);
				primaryKey.setName(name);
				primaryKey.setFieldName(fieldName);
				primaryKey.setDisplayName(displayName);
				primaryKey.setJavaType(javaType);
				tableMeta.addPrimaryKey(primaryKey);
			}else{
				ColumnMeta column=new ColumnMeta();
				column.setName(colName);
				column.setDataType(colRet.getInt("DATA_TYPE"));
				column.setJdbcTypeName(colRet.getString("TYPE_NAME"));
				column.setColumnName(colName);
				column.setName(name);
				column.setDisplayName(displayName);
				column.setFieldName(fieldName);
				column.setJavaType(javaType);
				
				tableMeta.addColumn(column);
			}

		}
		return tableMeta;
	}
	
	private String formatDisplayName(String column){
		column=column.toLowerCase();
		if(column.indexOf('_')!=-1){
			StringTokenizer stringTokenizer=new StringTokenizer(column,"_");
			StringBuffer sb=new StringBuffer();
			while(stringTokenizer.hasMoreElements()){
				String str=stringTokenizer.nextToken();
				if(str.matches("[\\d]+")){
					sb.append(str);
				}else{
					sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
				}
				sb.append(" ");
			}
			return sb.toString().trim();
		}else{
			StringBuffer sb=new StringBuffer();
			sb.append(column.substring(0, 1).toUpperCase()).append(column.substring(1));
			return sb.toString().trim();
		}
	}
	private String formatName(String column){
		column=column.toLowerCase();
		if(column.indexOf('_')!=-1){
			StringTokenizer stringTokenizer=new StringTokenizer(column,"_");
			StringBuffer sb=new StringBuffer(stringTokenizer.nextToken());
			while(stringTokenizer.hasMoreElements()){
				String str=stringTokenizer.nextToken();
				if(str.matches("[\\d]+")){
					sb.append(str);
				}else{
					sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
				}
			}
			return sb.toString();
		}
		return column;
	}



	@Override
	public String toString() {
		return "GenConfiguration [jdbcDriver=" + jdbcDriver + ", jdbcUrl="
				+ jdbcUrl + ", jdbcUser=" + jdbcUser + ", jdbcPassword="
				+ jdbcPassword + ", baseDir=" + baseDir + ", base=" + base
				+ ", tables=" + tables + ", tablePattern=" + tablePattern + "]";
	}
	public TableConfig getDefaultTableConfig() {
		return defaultTableConfig;
	}
	public void setDefaultTableConfig(TableConfig defaultTableConfig) {
		this.defaultTableConfig = defaultTableConfig;
	}
	public List<String> getExcludeTables() {
		return excludeTables;
	}
	public void setExcludeTables(List<String> excludeTables) {
		this.excludeTables = excludeTables;
	}
	

}
