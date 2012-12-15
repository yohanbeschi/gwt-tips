package org.isk.gwttips.client.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helper class to get typed {@link Logger}<br>
 * <p>
 * We sometimes want to log things in different files not depending of a {@link Level}. For this matter this class helps
 * to get purposed {@link Logger}s.<br>
 * <p>
 * But don't forget to add a class name to know from where the logged message is originating.
 * 
 * @author Yohan Beschi
 */
public class CustomLogger {

    /**
     * Get a {@link Logger} to log HTML.
     * 
     * @param name
     *            the Name of the class using the logger.
     * @return a {@link Logger}
     */
    public final static Logger getHtmlLogger(final String name) {
        return Logger.getLogger("html." + name);
    }
}
