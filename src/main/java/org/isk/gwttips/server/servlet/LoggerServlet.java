package org.isk.gwttips.server.servlet;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * This class translates {@link LogRecord} to slf4j {@link Logger}.
 * 
 * @author Yohan Beschi
 */
public class LoggerServlet extends RemoteServiceServlet implements RemoteLoggingService {
    private static final long serialVersionUID = -1383399516506297702L;

    @Override
    public String logOnServer(LogRecord record) {

        final Level level = record.getLevel();
        final String message = record.getMessage();
        final Throwable thrown = record.getThrown();

        final Logger logger = LoggerFactory.getLogger(record.getLoggerName());

        if (Level.INFO.intValue() == level.intValue()) {
            logger.info(message, thrown);
        } else if (Level.WARNING.intValue() == level.intValue() || Level.CONFIG.intValue() == level.intValue()) {
            logger.warn(message, thrown);
        } else if (Level.SEVERE.intValue() == level.intValue()) {
            logger.error(message, thrown);
        } else {
            // FINE, FINER and FINEST
            logger.debug(message, thrown);
        }

        return null;
    }

}
