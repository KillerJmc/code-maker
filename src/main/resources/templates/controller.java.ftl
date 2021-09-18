package ${package.Controller};

import org.springframework.web.bind.annotation.RestController;
<#if cfg.autowired>
import lombok.RequiredArgsConstructor;
import ${package.Service}.${table.serviceName};
</#if>

/**
 * @author ${author}
 */
@RestController
<#if cfg.autowired>
@RequiredArgsConstructor
</#if>
public class ${table.controllerName} {
<#--如果自动注入-->
<#if cfg.autowired>
    private final ${table.serviceName} ${table.serviceName? uncap_first};
</#if>
}
