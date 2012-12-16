package org.isk.gwttips.shared.exception;

/**
 * Exception to throw to the client.
 * <p>
 * It should be the only exception that we should handle on the client side to display messages.
 * <p>
 * If you need multiple exceptions to modify the view in different ways, they should inherit {@link ClientException}.
 * 
 * @author Yohan Beschi
 */
public class ClientException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ClientException() {
        super();
    }

    public ClientException(String key) {
        super(key);
    }

}
