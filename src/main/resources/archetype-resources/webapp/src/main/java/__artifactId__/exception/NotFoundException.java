#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.exception;

import ${package}.${artifactId}.ApiStatus;

/**
 * @author ock
 */
public class NotFoundException extends ApiException {
    private static final long serialVersionUID = -2344956899066064615L;

    /**
     *
     */
    public NotFoundException() {
        super(ApiStatus.NOT_FOUND, "");
    }

    /**
     * @param message
     */
    public NotFoundException(String message) {
        super(ApiStatus.NOT_FOUND, message);
    }
}
