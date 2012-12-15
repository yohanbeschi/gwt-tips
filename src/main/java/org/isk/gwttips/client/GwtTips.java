package org.isk.gwttips.client;

import java.util.logging.Logger;

import org.isk.gwttips.client.logging.CustomLogger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry Point of the application.
 * 
 * @author Yohan Beschi
 */
public class GwtTips implements EntryPoint {

    private final static Logger LOGGER = Logger.getLogger(GwtTips.class.getName());
    private final static Logger HTML_LOGGER = CustomLogger.getHtmlLogger(GwtTips.class.getName());

    @Override
    public void onModuleLoad() {
        LOGGER.fine("Module initializing...");

        RootLayoutPanel.get().add(new HTML("Hello World !!"));

        HTML_LOGGER.fine(RootLayoutPanel.get().toString());
    }
}
