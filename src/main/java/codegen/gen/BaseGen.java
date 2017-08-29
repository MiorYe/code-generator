package codegen.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class BaseGen {
	
	protected GenConfiguration genCfg;
	protected TableConfig tableConfig;
	protected Configuration ftlCfg;
	protected  Map<String, Object> root;
	public BaseGen(GenConfiguration genCfg, TableConfig tableConfig, Configuration ftlCfg) {
		super();
		this.genCfg = genCfg;
		this.tableConfig = tableConfig;
		this.ftlCfg = ftlCfg;
		root = new HashMap<String, Object>();
		if (tableConfig != null) {
			root.put("table", tableConfig);  
			if (tableConfig.getTableMeta().getPrimaryKeys().size() > 0) {
				root.put("keyColumn", tableConfig.getTableMeta().getPrimaryKeys().get(0));
			}
		}
        root.put("cfg", genCfg);
        
	}
	
	public abstract Template getTemplate() throws IOException ;
	
	public abstract void preGen() throws Exception ;
	
	public abstract String getOutPath() throws Exception ;
	
	public void gen() throws Exception {
		
		this.preGen();
		
		String path = genCfg.getBaseDir() + "/" + getOutPath();
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		Writer out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");  
		Template temp = getTemplate();  
        temp.process(root, out);  
        out.flush();  
	}

}
