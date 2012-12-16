package org.isk.gwttips.client.i18n;

import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * This interface contains i18n messages that can be found by their key.<br>
 * It's really useful if a key is wrapped in an exception for example.
 * 
 * @author Yohan Beschi
 */
public interface ErrorConstants extends ConstantsWithLookup {
    /**
     * Translated "Username or password invalid".
     * 
     * @return translated "Username or password invalid"
     */
    @DefaultStringValue("Username or password invalid")
    @Key("connection.user.unknown")
    String connectionUserUnknown();
}
