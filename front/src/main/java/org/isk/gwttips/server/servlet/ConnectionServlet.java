package org.isk.gwttips.server.servlet;

import javax.inject.Inject;

import org.isk.gwttips.client.service.ConnectionService;
import org.isk.gwttips.server.common.AutoinjectingRemoteServiceServlet;
import org.isk.gwttips.service.UserService;
import org.isk.gwttips.shared.UserUI;
import org.isk.gwttips.shared.exception.ClientException;

/**
 * 
 * @author Yohan Beschi
 */
public class ConnectionServlet extends AutoinjectingRemoteServiceServlet implements ConnectionService {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserUI connect(final String username, final String password) throws ClientException {
        return this.userService.connect(username, password);
    }

}
