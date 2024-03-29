package ${package.Entity};

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * ${table.comment!}
 * @author ${author}
 */
@Data
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
    <#-- primary key -->
    <#if field.keyIdentityFlag>
    @TableId(type = IdType.AUTO)
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}
