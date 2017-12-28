package ${cfg.base.model};

/**
* ${table.tableMeta.entityName}Entity.java
* @autor qianlishui
* @version 1.0.0
*/
public class ${table.tableMeta.entityName}Entity {

<#list table.tableMeta.primaryKeys as key>
private ${key.javaType} ${key.name};
</#list>

<#list table.tableMeta.columns as column>
    <#if column.javaType == 'java.util.Date'>
    private String ${column.name};
    <#else>
    private ${column.javaType} ${column.name};
    </#if>

</#list>
<#list table.tableMeta.primaryKeys as key>
    public void set${key.fieldName}(${key.javaType} ${key.name}) {
    this.${key.name} = ${key.name};
    }

    public ${key.javaType} get${key.fieldName}() {
    return this.${key.name};
    }
</#list>

<#list table.tableMeta.columns as column>
    <#if column.javaType == 'java.util.Date'>
        public void set${column.fieldName}(String ${column.name}) {
        this.${column.name} = ${column.name};
        }

        public String get${column.fieldName}() {
        return this.${column.name};
        }
    <#else>
        public void set${column.fieldName}(${column.javaType} ${column.name}) {
        this.${column.name} = ${column.name};
        }

        public ${column.javaType} get${column.fieldName}() {
        return this.${column.name};
        }
    </#if>

</#list>
@Override
public String toString() {
return "${table.tableMeta.entityName}Entity [" +
<#list table.tableMeta.columns as column>
"${column.name}=" + ${column.name} +
</#list>
"]";
}
}
