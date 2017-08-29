package codegen.gen;

import java.io.IOException;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Controller生成类
 * @author qianlishui
 *
 */
public class ControllerGen extends BaseGen {

	public ControllerGen(GenConfiguration genConfiguration, TableConfig tableConfig, Configuration ftlCfg) {
		super(genConfiguration, tableConfig, ftlCfg);
	}

	@Override
	public Template getTemplate() throws IOException {
		Template template = ftlCfg.getTemplate("controller.ftl");
		template.setEncoding("utf-8");
		return template;
	}

	@Override
	public void preGen() throws Exception{

	}

	@Override
	public String getOutPath() throws Exception {
		return genCfg.getBase().getController().replace('.', '/') + "/" + tableConfig.getTableMeta().getEntityName() + "Controller.java";
	}

}
