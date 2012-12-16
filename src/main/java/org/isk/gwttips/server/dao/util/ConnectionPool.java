package org.isk.gwttips.server.dao.util;

import java.sql.*;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton ConnectionPool.
 * 
 * @author Yohan Beschi
 */
public class ConnectionPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    private DataSource dataSource;

    // Private constructor prevents instantiation from other classes
    private ConnectionPool() {
        try {
            final InitialContext ic = new InitialContext();
            this.dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/gwttips");
        } catch (NamingException e) {
            LOGGER.error("Failed to initialize the datasource", e);
        }
    }

    /**
     * ConnectionPoolHolder is loaded on the first execution of Singleton.getInstance() or the first access to
     * ConnectionPoolHolder.INSTANCE, not before.
     * <p>
     * <a href="http://en.wikipedia.org/wiki/Double-checked_locking">Double-checked locking</a>
     */
    private static class ConnectionPoolHolder {
        public static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }

    /**
     * Get a {@link Connection}.
     * 
     * @return a {@link Connection}.
     */
    public Connection getConnection() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException sqle) {
            LOGGER.error("Failed to get a connection", sqle);
            return null;
        }
    }

}
