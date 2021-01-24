#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.exception;

import ${package}.${artifactId}.ApiStatus;

/**
 * @author ock
 */
public class BadRequestException extends ApiException {
    /**
     *
     */
    public BadRequestException() {
        super(ApiStatus.BAD_REQUEST, "");
    }

    /**
     * @param message
     */
    public BadRequestException(String message) {
        super(ApiStatus.BAD_REQUEST, message);
    }
}
