package org.isk.gwttips.server.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Support class used to close a bunch of things used to query a database.
 * 
 * @author Yohan Beschi
 */
public class DBUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);

    /**
     * Close a {@link Connection}.
     * 
     * @param statement
     *            is the {@link Connection} to close.
     */
    public void freeConnection(final Connection connection) {
        try {
            connection.close();
        } catch (SQLException sqle) {
            LOGGER.error("Failed to close Connection", sqle);
        }
    }

    /**
     * Close a {@link Statement}.
     * 
     * @param statement
     *            is the {@link Statement} to close.
     */
    public static void closeStatement(final Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to close Statement", e);
        }
    }

    /**
     * Close a {@link PreparedStatement}.
     * 
     * @param statement
     *            is the {@link PreparedStatement} to close.
     */
    public static void closePreparedStatement(final PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to close PreparedStatement", e);
        }
    }

    /**
     * Close a {@link ResultSet}.
     * 
     * @param statement
     *            is the {@link ResultSet} to close.
     */
    public static void closeResultSet(final ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to close ResultSet", e);
        }
    }
}
