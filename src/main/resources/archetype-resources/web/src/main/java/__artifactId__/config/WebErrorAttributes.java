#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.config;

import org.springframework.boot.${artifactId}.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.${artifactId}.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest ${artifactId}Request, boolean includeStackTrace) {
        Map<String, Object> result = super.getErrorAttributes(${artifactId}Request, includeStackTrace);

        Map<String, Object> error = new HashMap<>();
        error.put("code", result.get("status"));
        error.put("message", result.get("message"));
        result.put("error", error);
        return result;
    }


}
