package ${package.Controller};

import org.springframework.web.bind.annotation.RestController;
<#-- if user specify autowired in @CodeMaker -->
<#if autowired>
import lombok.RequiredArgsConstructor;
import ${package.Service}.${table.serviceName};
</#if>

/**
 * @author ${author}
 */
@RestController
<#if autowired>
@RequiredArgsConstructor
</#if>
public class ${table.controllerName} {
<#if autowired>
    private final ${table.serviceName} ${table.serviceName? uncap_first};
</#if>
}
