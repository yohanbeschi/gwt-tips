package org.isk.gwttips.service;

import org.isk.gwttips.shared.UserUI;
import org.isk.gwttips.shared.exception.ClientException;

public interface UserService {
    UserUI connect(String login, String password) throws ClientException;
}
