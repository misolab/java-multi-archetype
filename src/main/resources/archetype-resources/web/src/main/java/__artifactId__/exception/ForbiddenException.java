#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.exception;

import ${package}.${artifactId}.ApiStatus;

/**
 * @author ock
 */
public class ForbiddenException extends ApiException {
    /**
     *
     */
    public BadRequestException() {
        super(ApiStatus.FORBIDDEN, "");
    }

    /**
     * @param message
     */
    public BadRequestException(String message) {
        super(ApiStatus.FORBIDDEN, message);
    }
}
