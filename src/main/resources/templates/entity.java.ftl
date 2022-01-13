package ${package.Entity};

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * ${table.comment!}
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

    <#-- comments -->
    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    <#-- PK -->
    <#if field.keyIdentityFlag>
    @TableId(type = IdType.AUTO)
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
}
