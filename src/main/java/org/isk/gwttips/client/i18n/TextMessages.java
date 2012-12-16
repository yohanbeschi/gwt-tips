package org.isk.gwttips.client.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * This interface contains i18n messages with parameters.
 * 
 * @author Yohan Beschi
 */
public interface TextMessages extends Messages {
    /**
     * Translated "Welcome {0} {1}".
     * 
     * @return translated "Welcome {0} {1}"
     */
    @DefaultMessage("Welcome {0} {1}")
    @Key("connection.successful")
    String connectionSuccessful(String firstname, String lastName);
}
