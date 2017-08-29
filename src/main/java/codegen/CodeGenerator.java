package codegen;

import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import codegen.gen.ControllerGen;
import codegen.gen.DetailPageGen;
import codegen.gen.EditPageGen;
import codegen.gen.ListPageGen;
import codegen.gen.MapperExtGen;
import codegen.gen.MapperExtXmlGen;
import codegen.gen.MybatisGenCfgGen;
import codegen.gen.ParamGen;
import codegen.gen.ServiceGen;
import codegen.gen.ServiceImplGen;
import codegen.util.Utils;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class CodeGenerator {
	
	GenConfiguration genCfg;
	
	public CodeGenerator() throws Exception {
		genCfg = GenConfiguration.parse(this.getClass().getResourceAsStream("/genConfig.xml"));
	}
	
	public CodeGenerator(String cfgPath) throws Exception {
		genCfg = GenConfiguration.parse(new FileInputStream(cfgPath));
	}
	
	public static void main(String[] args) throws Exception {
		CodeGenerator codeGenerator = null;
		if (args.length > 0) {
			System.out.println("loading genConfig: " + args[0]);
			codeGenerator = new CodeGenerator(args[0]);
		} else {
			System.out.println("loading default genConfig");
			codeGenerator = new CodeGenerator();
		}
		if (args.length > 1 && args[1].equals("2")) {
			codeGenerator.gen2();
		} else {
			codeGenerator.gen();
		}
	}
	
	public void gen() throws Exception {
		
        /** 创建一个合适的configuration */  
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
        
        // 设置模板加载的方式  
		//cfg.setDirectoryForTemplateLoading(new File("/template"));  
        
        ClassTemplateLoader ctl = new ClassTemplateLoader(CodeGenerator.class, "/template"); 
        cfg.setTemplateLoader(ctl);
        
          
        // 指定模板如何查看数据模型  
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        

        for (TableConfig tableConfig : genCfg.getTables()) {
        	System.out.println("gen table: " + tableConfig.getName());
        	
        	/** 生成Controller */
        	if (!isBlank(genCfg.getBase().getController()) && tableConfig.isController()) {
        		ControllerGen controllerGen = new ControllerGen(genCfg, tableConfig, cfg);
        		controllerGen.gen();
        	}
        	
        	/** 生成Service和ServiceImpl */
        	if (!isBlank(genCfg.getBase().getService()) && tableConfig.isService()) {
        		ServiceGen serviceGen=new ServiceGen(genCfg, tableConfig, cfg);
    			serviceGen.gen();
    			
    			ServiceImplGen serviceImplGen=new ServiceImplGen(genCfg, tableConfig, cfg);
    			serviceImplGen.gen();
        	}
        	
        	/** 生成参数实体类 */
        	if (!isBlank(genCfg.getBase().getParam()) && tableConfig.isParam()) {
        		ParamGen paramGen = new ParamGen(genCfg, tableConfig, cfg);
        		paramGen.gen();
        	}
        	
        	/** 生成list,edit,detail页面 */
        	if (!isBlank(genCfg.getBase().getJsp()) && tableConfig.isJsp()) {
        		ListPageGen listPageGen = new ListPageGen(genCfg, tableConfig, cfg);
        		listPageGen.gen();
        		
        		EditPageGen editPageGen = new EditPageGen(genCfg, tableConfig, cfg);
        		editPageGen.gen();
        		
        		DetailPageGen detailPageGen = new DetailPageGen(genCfg, tableConfig, cfg);
        		detailPageGen.gen();
        	}
        	
        	/** 生成ext */
        	if (!isBlank(genCfg.getBase().getMapperExt()) && tableConfig.isMapperExt()) {
        		MapperExtGen mapperExtGen = new MapperExtGen(genCfg,tableConfig,cfg);
        		mapperExtGen.gen();
        		
        		MapperExtXmlGen mapperExtXmlGen = new MapperExtXmlGen(genCfg,tableConfig,cfg);
        		mapperExtXmlGen.gen();
        	}
        	
        	//MapperGen mapperGen = new MapperGen(genCfg, tableConfig, cfg);
        	//mapperGen.gen();
        	//MapperXmlGen mapperXmlGen = new MapperXmlGen(genCfg, tableConfig, cfg);
        	//mapperXmlGen.gen();
        }
        
        System.out.println("gen tables completed.");
	}
	
	boolean isBlank(String str){
		return Utils.isBlank(str);
	}
	
	/**
	 * gen mybatis tables gen configuration
	 * @throws Exception
	 */
	public void gen2() throws Exception {
		
        /** 创建一个合适的configuration */  
		Configuration cfg = new Configuration();  
        
        // 设置模板加载的方式  
		//cfg.setDirectoryForTemplateLoading(new File("/template"));  
        
        ClassTemplateLoader ctl = new ClassTemplateLoader(CodeGenerator.class, "/template"); 
        cfg.setTemplateLoader(ctl);
          
        /** 指定模板如何查看数据模型   */
        cfg.setObjectWrapper(new DefaultObjectWrapper());             
    	
    	MybatisGenCfgGen mybatisGenCfgGen = new MybatisGenCfgGen(genCfg, null, cfg);
    	mybatisGenCfgGen.gen();
    	System.out.println("gen mybatisGenCfg.xml completed.");
	}

	
	public static String xmlPrettyFormat(String input) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", 2);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}

}
