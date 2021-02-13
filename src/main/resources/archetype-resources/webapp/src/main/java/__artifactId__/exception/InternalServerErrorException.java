#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.exception;

import ${package}.${artifactId}.ApiStatus;

/**
 * @author ock
 */
public class InternalServerErrorException extends ApiException {
    private static final long serialVersionUID = -5784998240975162135L;

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
