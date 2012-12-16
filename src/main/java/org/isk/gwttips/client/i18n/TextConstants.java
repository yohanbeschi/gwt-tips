package org.isk.gwttips.client.i18n;

import com.google.gwt.i18n.client.Constants;

/**
 * This interface contains i18n static messages.
 * 
 * @author Yohan Beschi
 */
public interface TextConstants extends Constants {
    /**
     * Translated "Please Login".
     * 
     * @return translated "Please Login"
     */
    @DefaultStringValue("Please Login")
    @Key("login.intro.message")
    String loginIntroMessage();

    /**
     * Translated "Login".
     * 
     * @return translated "Login"
     */
    @DefaultStringValue("Login")
    @Key("login.field.login")
    String loginFieldLogin();

    /**
     * Translated "Password".
     * 
     * @return translated "Password"
     */
    @DefaultStringValue("Password")
    @Key("login.field.password")
    String loginFieldPassword();

    /**
     * Translated "Connect".
     * 
     * @return translated "Connect"
     */
    @DefaultStringValue("Connect")
    @Key("login.field.submit")
    String loginFieldSubmit();
}
