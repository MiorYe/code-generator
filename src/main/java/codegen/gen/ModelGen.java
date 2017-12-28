package codegen.gen;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;

/**
 * Copyright (c) 2017 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author Administrator
 * @since 2017/12/28 0028
 */
public class ModelGen extends BaseGen {

    public ModelGen(GenConfiguration genCfg, TableConfig tableConfig, Configuration ftlCfg) {
        super(genCfg, tableConfig, ftlCfg);
    }

    public Template getTemplate() throws IOException {
        Template template = ftlCfg.getTemplate("model.ftl");
        template.setEncoding("utf-8");
        return template;
    }

    public void preGen() throws Exception {

    }

    public String getOutPath() throws Exception {
        return genCfg.getBase().getMapperXml().replace('.', '/')+"/"+tableConfig.getTableMeta().getEntityName()+".java";
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
        sb.insert(sb.lastIndexOf("}")-1, str);
        FileWriter writer=new FileWriter(file);
        writer.write(sb.toString());
        writer.flush();

        br.close();
        writer.close();
    }
}
