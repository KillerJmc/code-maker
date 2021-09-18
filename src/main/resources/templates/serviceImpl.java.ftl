package ${package.ServiceImpl};

import ${package.Service}.${table.serviceName};
<#if cfg.autowired>
import ${package.Mapper}.${table.mapperName};
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.stereotype.Service;

/**
 * @author ${author}
 */
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {
<#--如果自动注入-->
<#if cfg.autowired>
    <#assign mapperVarName = "${table.mapperName? uncap_first}">
    private ${table.mapperName} ${mapperVarName};

    @Autowired
    public void set${table.mapperName}(${table.mapperName} ${mapperVarName}) {
        this.${mapperVarName} = ${mapperVarName};
    }

</#if>

}
