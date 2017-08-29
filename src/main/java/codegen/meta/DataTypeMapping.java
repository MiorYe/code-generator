package codegen.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库字段类型与java属性类型映射
 * @author qianlishui
 *
 */
public class DataTypeMapping {
	
	static Map<Integer, String> jdbc2Java = new HashMap<Integer, String>();
	
	static {
		jdbc2Java.put(-7, "Byte");
		jdbc2Java.put(-7, "Byte");
		jdbc2Java.put(-6, "Short");
		jdbc2Java.put(-6, "Short");
		jdbc2Java.put(-5, "Long");
		jdbc2Java.put(-5, "Long");
		jdbc2Java.put(-4, "Byte[]");
		jdbc2Java.put(-4, "Byte[]");
		jdbc2Java.put(-4, "Byte[]");
		jdbc2Java.put(-4, "Byte[]");
		jdbc2Java.put(-4, "Byte[]");
		jdbc2Java.put(-3, "Byte[]");
		jdbc2Java.put(-2, "Byte[]");
		jdbc2Java.put(-1, "String");
		jdbc2Java.put(-1, "String");
		jdbc2Java.put(-1, "String");
		jdbc2Java.put(-1, "String");
		jdbc2Java.put(-1, "String");
		jdbc2Java.put(1, "Character");
		jdbc2Java.put(2, "Float");
		jdbc2Java.put(3, "Float");
		jdbc2Java.put(4, "Integer");
		jdbc2Java.put(4, "Integer");
		jdbc2Java.put(4, "Integer");
		jdbc2Java.put(4, "Integer");
		jdbc2Java.put(4, "Integer");
		jdbc2Java.put(4, "Integer");
		jdbc2Java.put(5, "Short");
		jdbc2Java.put(5, "Short");
		jdbc2Java.put(7, "Float");
		jdbc2Java.put(8, "Double");
		jdbc2Java.put(8, "Double");
		jdbc2Java.put(8, "Double");
		jdbc2Java.put(12, "String");
		jdbc2Java.put(12, "String");
		jdbc2Java.put(12, "String");
		jdbc2Java.put(91, "java.util.Date");
		jdbc2Java.put(92, "java.util.Date");
		jdbc2Java.put(93, "java.util.Date");
		jdbc2Java.put(93, "java.util.Date");
	}
	
	public static String getJavaTypeName(int jdbcDataType){
		
		return jdbc2Java.get(jdbcDataType);
	}

}
