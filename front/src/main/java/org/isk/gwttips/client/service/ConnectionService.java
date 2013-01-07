package org.isk.gwttips.client.service;

import org.isk.gwttips.shared.UserUI;
import org.isk.gwttips.shared.exception.ClientException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("connect")
public interface ConnectionService extends RemoteService {
    /**
     * Try to connect the user.
     * 
     * @param username
     *            is the name of the user.
     * @param password
     *            is the password of the user.
     * @return an {@link UserUI} if he exists, or a {@link ClientException} otherwise.
     * @throws ClientException
     */
    UserUI connect(String username, String password) throws ClientException;
}
