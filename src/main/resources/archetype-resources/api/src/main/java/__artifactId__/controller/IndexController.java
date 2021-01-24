#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import lombok.RequiredArgsConstructor;
import ${package}.common.util.DateTimeUtils;
import ${package}.web.vo.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping
    public ResponseEntity<Object> index() {
        ApiResponse response = ApiResponse.of("result", true)
                .add("message", "This is api module")
                .add("current", DateTimeUtils.toString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        return response.toResponseEntity();
    }
}

