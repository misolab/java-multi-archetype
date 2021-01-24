#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.exception;

import ${package}.${artifactId}.ApiStatus;

/**
 * @author ock
 */
public class InternalServerErrorException extends ApiException {
    /**
     *
     */
    public InternalServerErrorException() {
        super(ApiStatus.INTERNAL_SERVER_ERROR, "");
    }

    /**
     * @param message
     */
    public InternalServerErrorException(String message) {
        super(ApiStatus.INTERNAL_SERVER_ERROR, message);
    }
}
