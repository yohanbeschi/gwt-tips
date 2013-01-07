package org.isk.gwttips.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.isk.gwttips.service.UserService;
import org.isk.gwttips.shared.UserUI;
import org.isk.gwttips.shared.exception.ClientException;
import org.isk.persistence.domain.User;
import org.isk.persistence.exception.UserException;
import org.isk.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("userService")
@Singleton
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Inject
    private UserRepository userRepository;
    
    @Override
    public UserUI connect(String login, String password) throws ClientException {
        try {
            final User user = this.userRepository.connect(login, password);
            return new UserUI(user.getFirstName(), user.getLastName());
        } catch (UserException e) {
            LOGGER.info(e.getMessage());
            throw new ClientException(e.getMessage());
        }
    }
}
