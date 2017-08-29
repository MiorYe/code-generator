package ${cfg.base.controller};

<#if table.page == true || table.list == true>
import java.util.List;
</#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhf.boss.resp.ResponseUtils;
import ${cfg.base.service}.${table.tableMeta.entityName}Service;
import ${cfg.base.param}.${table.tableMeta.entityName}Param;
import ${cfg.base.mapperModel}.${table.tableMeta.entityName};
import com.dhf.boss.param.PageBean;

/**
 * ${table.tableMeta.entityName}Controller.java
 * @autor qianlishui
 *  
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/${table.tableMeta.referenceName}")
public class ${table.tableMeta.entityName}Controller extends BaseController {
	
	@Autowired
    private ${table.tableMeta.entityName}Service ${table.tableMeta.referenceName}Service;
    
    <#if table.page == true>
	/**
     * 分页列表页面
     */
    public String listPage(@ModelAttribute ${table.tableMeta.entityName}Param param, ModelMap modelMap,
            @RequestParam(value = "pageNum", required = false , defaultValue = "1") int pageNum,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

		PageBean pageBean = new PageBean(30, pageNum);
       	List<${table.tableMeta.entityName}> list = ${table.tableMeta.referenceName}Service.getListByPage(pageBean, param);
		modelMap.addAttribute("pageBean", pageBean);
		modelMap.addAttribute("list", list);

        return AUTO_PAGE;
    }
	</#if>
	
	/**
     * 新增/修改页面
     */
    public String detailPage(@RequestParam(value = "id", required = true) Integer id, ModelMap modelMap,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (id != null && id > 0) {
			${table.tableMeta.entityName} entity = ${table.tableMeta.referenceName}Service.get${table.tableMeta.entityName}(id);
        	modelMap.addAttribute("detail", entity);
		}

        return AUTO_PAGE;
    }
    
    /**
     * 新增/修改页面
     */
    public String editPage(@RequestParam(required = false, value = "cType", defaultValue = "-1") Integer cType,
    		@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (id != null && id > 0) {
			${table.tableMeta.entityName} entity = ${table.tableMeta.referenceName}Service.get${table.tableMeta.entityName}(id);
        	modelMap.addAttribute("detail", entity);
		}
        modelMap.addAttribute("cType", cType);

        return AUTO_PAGE;
    }
    
    /**
     * 修改for ajax
     */
    public Object updateData(@ModelAttribute ${table.tableMeta.entityName}Param param, ModelMap modelMap, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	${table.tableMeta.entityName} entity = new ${table.tableMeta.entityName}();
    	BeanUtils.copyProperties(param, entity);
    	${table.tableMeta.referenceName}Service.update(entity);
        return ResponseUtils.succ();
    }
    
    /**
     * 新增for ajax
     */
    public Object saveData(@ModelAttribute ${table.tableMeta.entityName}Param param, ModelMap map, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	${table.tableMeta.entityName} entity = new ${table.tableMeta.entityName}();
    	BeanUtils.copyProperties(param, entity);
    	${table.tableMeta.referenceName}Service.save(entity);
        return ResponseUtils.succ();
    }
    
    /**
     * 删除
     */
    public Object delData(@RequestParam(value = "id", required = true) Integer id, ModelMap map, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	${table.tableMeta.referenceName}Service.delete(id);
    	return ResponseUtils.succ();
    }

}
