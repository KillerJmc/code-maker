package ${package.Entity};

import lombok.Data;
<#-- if there has table prefixes, add import -->
<#if table.convert>
import com.baomidou.mybatisplus.annotation.TableName;
</#if>

/**
 * @author ${author}
 */
@Data
<#-- if there has table prefixes, add annotation -->
<#if table.convert>
@TableName("${table.name}")
</#if>
public class ${entity} {
<#-- list and inject table fields -->
<#list table.fields as field>
    private ${field.propertyType} ${field.propertyName};
</#list>
}
