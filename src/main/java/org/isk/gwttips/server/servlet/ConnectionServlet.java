package org.isk.gwttips.server.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.isk.gwttips.client.service.ConnectionService;
import org.isk.gwttips.server.dao.util.ConnectionPool;
import org.isk.gwttips.server.dao.util.DBUtil;
import org.isk.gwttips.shared.UserUI;
import org.isk.gwttips.shared.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Yohan Beschi
 */
public class ConnectionServlet extends RemoteServiceServlet implements ConnectionService {
    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionServlet.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public UserUI connect(final String username, final String password) throws ClientException {

        final UserUI user = getUser(username, password);

        if (user == null) {
            throw new ClientException("user.unknown");
        }

        return user;
    }

    /**
     * Get a {@link UserUI} matching a username and a password.
     * 
     * @param username
     *            is the name of the user.
     * @param password
     *            is the password of the user.
     * 
     * @return a {@link UserUI} if he exists, <code>null</code> otherwise.
     */
    private UserUI getUser(final String username, final String password) {
        final String query = "SELECT * FROM R_USER WHERE USR_LOGIN = ? AND USR_PASSWORD = ?";

        final ConnectionPool connectionPool = ConnectionPool.getInstance();
        final Connection connection = connectionPool.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            UserUI newDatabean = null;
            if (resultSet != null && resultSet.next()) {
                newDatabean = this.buildObject(resultSet);
            }

            return newDatabean;
        } catch (SQLException e) {
            LOGGER.error("Query error", e);
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
        }
    }

    /**
     * Build an {@link UserUI} from a {@link ResultSet}
     * 
     * @param resultSet
     *            is the {@link ResultSet} from which the {@link UserUI} will be built.
     * @return an {@link UserUI}
     * @throws SQLException
     */
    private UserUI buildObject(ResultSet resultSet) throws SQLException {
        return new UserUI(resultSet.getString("USR_FIRST_NAME"), resultSet.getString("USR_LAST_NAME"));
    }

}
