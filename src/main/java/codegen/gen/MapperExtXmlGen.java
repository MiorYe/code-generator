package codegen.gen;

import java.io.IOException;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class MapperExtXmlGen extends BaseGen {

	public MapperExtXmlGen(GenConfiguration genConfiguration, TableConfig tableConfig,
			Configuration ftlCfg) {
		super(genConfiguration, tableConfig, ftlCfg);
	}

	@Override
	public Template getTemplate() throws IOException {
		Template template = ftlCfg.getTemplate("mapperExtXml.ftl");
		template.setEncoding("utf-8");
		return template;
	}

	@Override
	public void preGen() throws Exception{

	}

	@Override
	public String getOutPath() throws Exception {
		return genCfg.getBase().getMapperXmlExt().replace('.', '/') + "/" + tableConfig.getTableMeta().getEntityName() + "Dao.xml";
	}

}
