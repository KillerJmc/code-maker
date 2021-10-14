package ${package.ServiceImpl};

import ${package.Service}.${table.serviceName};
<#-- these imports will be added if user specify autowired in @CodeMaker -->
<#if cfg.autowired>
import ${package.Mapper}.${table.mapperName};
import lombok.RequiredArgsConstructor;
</#if>
import org.springframework.stereotype.Service;

/**
 * @author ${author}
 */
@Service
<#-- this annotation will be added if user specify autowired in @CodeMaker -->
<#if cfg.autowired>
@RequiredArgsConstructor
</#if>
public class ${table.serviceImplName} implements ${table.serviceName} {
<#-- this arg will be added if user specify autowired in @CodeMaker -->
<#if cfg.autowired>
    private final ${table.mapperName} ${table.mapperName? uncap_first};
</#if>
}
