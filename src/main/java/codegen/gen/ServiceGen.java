package codegen.gen;

import java.io.IOException;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * service生成类
 * @author yejianzhong
 *
 */
public class ServiceGen extends BaseGen {

	public ServiceGen(GenConfiguration genConfiguration, TableConfig tableConfig, Configuration ftlCfg) {
		super(genConfiguration, tableConfig, ftlCfg);
	}

	@Override
	public Template getTemplate() throws IOException {
		Template template = ftlCfg.getTemplate("service.ftl");
		template.setEncoding("utf-8");
		return template;
	}

	@Override
	public void preGen() throws Exception{

	}

	@Override
	public String getOutPath() throws Exception {
		return genCfg.getBase().getService().replace('.', '/') + "/" + tableConfig.getTableMeta().getEntityName() + "Service.java";
	}

}
