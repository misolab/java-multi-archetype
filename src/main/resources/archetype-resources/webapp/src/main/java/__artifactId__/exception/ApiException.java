#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.exception;

import ${package}.${artifactId}.ApiStatus;

/**
 * @author ock
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 5145867487543420979L;

    private int code;

    /**
     * @param code
     * @param message
     */
    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param status
     * @param message
     */
    public ApiException(ApiStatus status, String message) {
        super(message);
        this.code = status.getCode();
    }

    /**
     * @param message
     */
    public ApiException(String message) {
        super(message);
        this.code = ApiStatus.SUCCESS.getCode();
    }

    /**
     * @return
     */
    public int getCode() {
        return this.code;
    }
}
