package codegen.gen;

import java.io.IOException;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 参数实体生成类
 * @author qianlishui
 *
 */
public class ParamGen extends BaseGen {

	public ParamGen(GenConfiguration genConfiguration, TableConfig tableConfig, Configuration ftlCfg) {
		super(genConfiguration, tableConfig, ftlCfg);
	}

	@Override
	public Template getTemplate() throws IOException {
		Template template = ftlCfg.getTemplate("param.ftl");
		template.setEncoding("utf-8");
		return template;
	}

	@Override
	public void preGen() throws Exception{

	}

	@Override
	public String getOutPath() throws Exception {
		return genCfg.getBase().getParam().replace('.', '/') + "/" + tableConfig.getTableMeta().getEntityName() + "Param.java";
	}

}
