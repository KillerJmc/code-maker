package ${package.Controller};

import org.springframework.web.bind.annotation.RestController;
<#-- these imports will be added if user specify autowired in @CodeMaker -->
<#if cfg.autowired>
import lombok.RequiredArgsConstructor;
import ${package.Service}.${table.serviceName};
</#if>

/**
 * @author ${author}
 */
@RestController
<#-- this annotation will be added if user specify autowired in @CodeMaker -->
<#if cfg.autowired>
@RequiredArgsConstructor
</#if>
public class ${table.controllerName} {
<#-- this arg will be added if user specify autowired in @CodeMaker -->
<#if cfg.autowired>
    private final ${table.serviceName} ${table.serviceName? uncap_first};
</#if>
}
