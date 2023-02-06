package ${package.Entity};

import java.time.*;
import jakarta.persistence.*;
import lombok.Data;

/**
 * ${table.comment!}
 * @author ${author}
 */
@Data
<#-- if there has table prefixes, add annotation -->
<#if table.convert>
@Entity(name = "${table.name}")
<#else>
@Entity
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}
