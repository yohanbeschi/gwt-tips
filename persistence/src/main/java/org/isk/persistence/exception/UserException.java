package org.isk.persistence.exception;

/**
 * Exception to display to the user.<br>
 * Note: The message can have parameters.
 * 
 * @author Yohan BESCHI
 */
public class UserException extends ParameterizedException {

    private static final long serialVersionUID = 6426043895309922357L;

    /**
     * Exception with a message.
     * 
     * @param message
     *            is the message of the exception.
     */
    public UserException(String message) {
        super(message);
    }

    /**
     * Exception with a message with parameters.
     * 
     * @param message
     *            is the message of the exception.
     * @param parameters
     *            are the parameters of the message.
     */
    public UserException(String message, Object... parameters) {
        super(message, parameters);
    }

    /**
     * Exception with a message and a cause.
     * 
     * @param message
     *            is the message of the exception.
     * @param cause
     *            is the cause of the exception.
     */
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
