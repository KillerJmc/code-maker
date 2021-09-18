package ${package.ServiceImpl};

import ${package.Service}.${table.serviceName};
<#if cfg.autowired>
import ${package.Mapper}.${table.mapperName};
import lombok.RequiredArgsConstructor;
</#if>
import org.springframework.stereotype.Service;

/**
 * @author ${author}
 */
@Service
<#if cfg.autowired>
@RequiredArgsConstructor
</#if>
public class ${table.serviceImplName} implements ${table.serviceName} {
<#--如果自动注入-->
<#if cfg.autowired>
    private final ${table.mapperName} ${table.mapperName? uncap_first};
</#if>
}
