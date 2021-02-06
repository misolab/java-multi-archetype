#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class WebErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest ${artifactId}Request, boolean includeStackTrace) {
        Map<String, Object> result = super.getErrorAttributes(${artifactId}Request, includeStackTrace);

        //  code, message 추가해서 ApiResponse 구조를 만들자
        result.put("code", result.get("status"));
        result.put("message", result.get("message"));
        return result;
    }


}
