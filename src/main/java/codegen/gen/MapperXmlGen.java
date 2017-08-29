package codegen.gen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class MapperXmlGen extends BaseGen {

	public MapperXmlGen(GenConfiguration genCfg, TableConfig tableConfig,
			Configuration ftlCfg) {
		super(genCfg, tableConfig, ftlCfg);
	}

	@Override
	public Template getTemplate() throws IOException {
		Template template = ftlCfg.getTemplate("mapperXml.ftl");
		template.setEncoding("utf-8");
		return template;
	}

	@Override
	public void preGen() throws Exception{

	}

	@Override
	public String getOutPath() throws Exception {
		return genCfg.getBase().getMapperXml().replace('.', '/')+"/"+tableConfig.getTableMeta().getEntityName()+"Mapper.xml";
	}

	@Override
	public void gen() throws Exception {
		this.preGen();
		
		StringWriter out = new StringWriter();  
		Template temp = getTemplate();  
        temp.process(root, out);  
        out.flush();
        String str=out.toString();
        
        File file=new File(genCfg.getBaseDir()+"/"+getOutPath());
        BufferedReader br=new BufferedReader(new FileReader(file));
        StringBuffer sb=new StringBuffer();
        char[] buf=new char[1024]; 
        int rSize=0;
        while((rSize=br.read(buf))!=-1){
        	sb.append(buf,0 , rSize);
        }
        sb.insert(sb.lastIndexOf("</mapper>")-1, str);
        FileWriter writer=new FileWriter(file);
        writer.write(sb.toString());
        writer.flush();
        
        br.close();
        writer.close();
	}

}
