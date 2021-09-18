package ${package.Controller};

<#if cfg.autowired>
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${author}
 */
@RestController
public class ${table.controllerName} {
<#--如果自动注入-->
<#if cfg.autowired>
    <#assign serviceVarName = "${table.serviceName? uncap_first}">
    private ${table.serviceName} ${serviceVarName};

    @Autowired
    public void set${table.serviceName}(${table.serviceName} ${serviceVarName}) {
        this.${serviceVarName} = ${serviceVarName};
    }

</#if>

}
