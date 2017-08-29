package codegen.meta;

import java.util.ArrayList;
import java.util.List;

public class TableMeta {

	private String name;
	
	private String tableName;
	
	private String displayName;
	
	private String entityName;
	
	private String referenceName;

	private List<PrimaryKeyMeta> primaryKeys = new ArrayList<PrimaryKeyMeta>();
	private List<ColumnMeta> columns = new ArrayList<ColumnMeta>();

	public void addPrimaryKey(PrimaryKeyMeta e) {
		primaryKeys.add(primaryKeys.size(), e);
	}

	public boolean addColumn(ColumnMeta e) {
		return columns.add(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PrimaryKeyMeta> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<PrimaryKeyMeta> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public List<ColumnMeta> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnMeta> columns) {
		this.columns = columns;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	@Override
	public String toString() {
		return "TableMeta [name=" + name + ", tableName=" + tableName
				+ ", displayName=" + displayName + ", entityName=" + entityName
				+ ", primaryKeys=" + primaryKeys + ", columns=" + columns + "]";
	}

}
