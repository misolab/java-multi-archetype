#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import ${package}.common.util.DateTimeUtils;
import ${package}.common.util.StringUtils;
import ${package}.webapp.vo.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"/", "/login"})
    public String entry() {
        return "index";
    }

    @GetMapping("/hello")
    public ResponseEntity<Object> index(String error) {
        ApiResponse response;
        if (StringUtils.isNotEmpty(error)) {
            response = ApiResponse.of().error(500, error);
        } else {
            response = ApiResponse.of()
                    .add("message", "This is admin module")
                    .add("current", DateTimeUtils.toString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        }

        return response.toResponseEntity();
    }
}

