package codegen.gen;

import java.io.IOException;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class MybatisGenCfgGen extends BaseGen {

	public MybatisGenCfgGen(GenConfiguration genCfg, TableConfig tableConfig, Configuration ftlCfg) {
		super(genCfg, tableConfig, ftlCfg);
	}

	@Override
	public Template getTemplate() throws IOException {
		Template template = ftlCfg.getTemplate("mybatisGenCfg.ftl");
		template.setEncoding("utf-8");
		return template;
	}

	@Override
	public void preGen() throws Exception{

	}

	@Override
	public String getOutPath() throws Exception {
		return "mybatisGenCfg.xml";
	}

}
