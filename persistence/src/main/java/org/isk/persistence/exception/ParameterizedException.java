package org.isk.persistence.exception;

/**
 * Exception that can hold parameters for the message.
 * 
 * @author Yohan Beschi
 */
public class ParameterizedException extends Exception {
    private static final long serialVersionUID = 1L;

    final Object[] parameters;

    /**
     * Exception with a message.
     * 
     * @param message
     *            is the message of the exception.
     */
    public ParameterizedException(String message) {
        super(message);
        this.parameters = null;
    }

    /**
     * Exception with a message with parameters.
     * 
     * @param message
     *            is the message of the exception.
     * @param parameters
     *            are the parameters of the message.
     */
    public ParameterizedException(String message, Object[] parameters) {
        super(message);
        this.parameters = parameters;
    }

    /**
     * Exception with a message and a cause.
     * 
     * @param message
     *            is the message of the exception.
     * @param cause
     *            is the cause of the exception.
     */
    public ParameterizedException(String message, Throwable cause) {
        super(message, cause);
        this.parameters = null;
    }

    public final Object[] getParameters() {
        return parameters;
    }
}
