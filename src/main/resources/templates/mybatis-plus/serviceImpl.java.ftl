package ${package.ServiceImpl};

import ${package.Service}.${table.serviceName};
<#-- if user specify autowired in @CodeMaker -->
<#if autowired>
import ${package.Mapper}.${table.mapperName};
import lombok.RequiredArgsConstructor;
</#if>
import org.springframework.stereotype.Service;

/**
 * @author ${author}
 */
@Service
<#if autowired>
@RequiredArgsConstructor
</#if>
public class ${table.serviceImplName} implements ${table.serviceName} {
<#if autowired>
    private final ${table.mapperName} ${table.mapperName? uncap_first};
</#if>
}
