#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.vo;

import ${package}.common.util.CheckUtils;
import ${package}.${artifactId}.exception.ApiException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ock
 */
@Getter
@NoArgsConstructor
public class ApiResponse {

    Map<String, Object> data;
    Map<String, Object> error;

    /**
     * @param data
     */
    public ApiResponse(Map<String, Object> data) {
        this.data = data;
    }

    public static ApiResponse of(Map<String, Object> data) {
        return new ApiResponse(data);
    }

    public static ApiResponse of(String key, Object value) {
        return new ApiResponse().add(key, value);
    }

    public static ApiResponse error(ApiException exception) {
        return new ApiResponse().error(exception.getCode(), exception.getMessage());
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
        if (error == null) {
            error = new HashMap<>();
        }
        error.put("code", code);
        error.put("message", message);
        return this;
    }

    public ResponseEntity<Object> toResponseEntity() {
        if (!CheckUtils.isEmpty(error)) {
            return ResponseEntity
                    .status((int) error.get("code"))
                    .body(error);
        }
        return ResponseEntity.ok(data);
    }
}
