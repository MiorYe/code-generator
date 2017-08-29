package codegen.meta;

/**
 * 数据库字段元信息
 * @author yejianzhong
 *
 */
public class ColumnMeta {

	/** 生成实体类的属性字段时用此字段 */
	protected String name;

	/** 生成实体类getter，setter方法时用此字段 */
	protected String fieldName;

	/** 显示名称，形如：Dispaly Name */
	protected String displayName;

	/** 数据表字段名 */
	protected String columnName;

	/** 数据库字段类型 */
	protected int dataType;

	protected String jdbcTypeName;

	/** 映射成java的数据类型 */
	protected String javaType;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getJdbcTypeName() {
		return jdbcTypeName;
	}

	public void setJdbcTypeName(String jdbcTypeName) {
		this.jdbcTypeName = jdbcTypeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	@Override
	public String toString() {
		return "ColumnMeta [name=" + name + ", displayName=" + displayName
				+ ", columnName=" + columnName + ", dataType=" + dataType
				+ ", jdbcTypeName=" + jdbcTypeName + "]";
	}
}
