package codegen.meta;

public class PrimaryKeyMeta extends ColumnMeta{

	@Override
	public String toString() {
		return "PrimaryKeyMeta [name=" + name + ", displayName=" + displayName
				+ ", columnName=" + columnName + ", dataType=" + dataType
				+ ", jdbcTypeName=" + jdbcTypeName + "]";
	}



}
