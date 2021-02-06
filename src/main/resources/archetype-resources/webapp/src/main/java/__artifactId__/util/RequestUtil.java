#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import ${package}.common.util.StringUtils;

@Slf4j
public class RequestUtil {

    public static String getClientIP(HttpServletRequest request) {
        final List<String> HEADERS = Arrays.asList("X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR");
        String ip = "";
        for (String header : HEADERS) {
            ip = request.getHeader(header);
            if (StringUtils.isNotEmpty(ip)) {
                log.info("client IP - {} : {}", header, ip);
                break;
            }
        }

        if (StringUtils.isEmpty(ip)) {
            ip = request.getRemoteAddr();
            log.info("getRemoteAddr {}", ip);
        }

        int idx = ip.indexOf(',');
        if (idx > -1) {
            ip = ip.substring(0, idx);
        }
        log.info("Result : IP Address {} ", ip);
        return ip;
    }
}
