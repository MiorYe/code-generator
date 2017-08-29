<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE generatorConfiguration  
      PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"  
      "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">  
  
<generatorConfiguration>  
    <classPathEntry location="D:/mysql-connector-java-5.1.38-bin.jar" />  
  
    <context id="DB2Tables" targetRuntime="MyBatis3">  
	
		<commentGenerator>                                                    
            <property name="suppressDate" value="true" />                                                
			<property name="suppressAllComments" value="true" />      
        </commentGenerator>   
		
        <jdbcConnection driverClass="${cfg.jdbcDriver}"  
            connectionURL="${cfg.jdbcUrl}"  
            userId="${cfg.jdbcUser}"  
            password="${cfg.jdbcPassword}">  
        </jdbcConnection>  
		
        <javaTypeResolver >  
            <property name="forceBigDecimals" value="false" />  
        </javaTypeResolver>  
  
        <!-- generate Model -->  
        <javaModelGenerator targetPackage="${cfg.base.mapperModel}" targetProject="target\">  
            <property name="enableSubPackages" value="true" />  
            <property name="trimStrings" value="true" />  
        </javaModelGenerator>  
          
          
  
        <!-- generate xml -->  
        <sqlMapGenerator targetPackage="${cfg.base.mapperXml}"  targetProject="target\">  
            <property name="enableSubPackages" value="true" />  
        </sqlMapGenerator>  
          
        <!-- generate Mapper -->  
        <javaClientGenerator type="XMLMAPPER" targetPackage="${cfg.base.mapper}"  targetProject="target\">  
            <property name="enableSubPackages" value="true" />  
			<property name="methodNameCalculator" value="org.mybatis.generator.api.DAOMethodNameCalculator" />  
        </javaClientGenerator>  


<#list cfg.tables! as table>
		<table schema="" tableName="${table.name}" domainObjectName="${table.tableMeta.entityName}"  enableCountByExample="false" enableUpdateByExample="false"                                                    
		    enableDeleteByExample="false" enableSelectByExample="false"                                                            
		    selectByExampleQueryId="false">
		      <!--
			<#if (table.tableMeta.primaryKeys?size>0) >
			 <generatedKey column="${table.tableMeta.primaryKeys[0].columnName!}" sqlStatement="MySql" identity="true"/>
			 </#if>
			 -->
		</table> 
</#list>	

    </context>  
</generatorConfiguration>  	