#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.vo;

import lombok.Getter;
import ${package}.web.ApiStatus;
import ${package}.web.exception.ApiException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ock
 */
@Getter
public class ApiResponse {

    int code;
    String message;
    Map<String, Object> data;

    /**
     * @param data
     */
    public ApiResponse(Map<String, Object> data) {
        this.code = ApiStatus.SUCCESS.getCode();
        this.data = data;
    }

    public static ApiResponse of() {
        return new ApiResponse(new HashMap<>());
    }

    public static ApiResponse of(Map<String, Object> data) {
        return new ApiResponse(data);
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResponse error(ApiException exception) {
        return new ApiResponse(exception.getCode(), exception.getMessage());
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public ApiResponse add(String key, Object value) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, value);
        return this;
    }

    /**
     * @param code
     * @param message
     * @return
     */
    public ApiResponse error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseEntity<Object> toResponseEntity() {
        if (code != ApiStatus.SUCCESS.getCode()) {
            return ResponseEntity
                    .status(code)
                    .body(this);
        }
        return ResponseEntity.ok(this);
    }
}
