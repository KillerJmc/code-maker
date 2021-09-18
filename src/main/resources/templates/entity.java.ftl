package ${package.Entity};

import lombok.Data;

/**
 * @author ${author}
 */
@Data
public class ${entity} {
<#list table.fields as field>
    private ${field.propertyType} ${field.propertyName};
</#list>
}
