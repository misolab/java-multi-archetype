#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import lombok.RequiredArgsConstructor;
import ${package}.common.util.DateTimeUtils;
import ${package}.web.vo.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping(value = {"/", "/login"})
    public String entry() {
        return "index";
    }

    @GetMapping("/ajax")
    public ResponseEntity<Object> index() {
        ApiResponse response = ApiResponse.of("result", true)
                .add("message", "This is admin module")
                .add("current", DateTimeUtils.toString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        return response.toResponseEntity();
    }
}

