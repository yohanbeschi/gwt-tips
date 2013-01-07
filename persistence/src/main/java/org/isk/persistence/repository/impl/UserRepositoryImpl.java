package org.isk.persistence.repository.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.isk.persistence.core.dao.GenericDao;
import org.isk.persistence.core.repository.RepositoryImpl;
import org.isk.persistence.dao.UserDAO;
import org.isk.persistence.domain.User;
import org.isk.persistence.exception.UserException;
import org.isk.persistence.repository.UserRepository;

@Named("userRepository")
@Singleton
public class UserRepositoryImpl extends RepositoryImpl<User, Long> implements UserRepository {

    @Inject
    private UserDAO userDAO;

    @SuppressWarnings("unchecked")
    @Override
    public GenericDao<User, Long> getDao() {
        return (GenericDao<User, Long>) this.userDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getNew() {
        return new User();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User connect(String login, String password) throws UserException {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            throw new UserException("connection.blank.fields");
        }
        
        final User model = new User();
        model.setLogin(login);
        model.setPassword(password);
        
        final User user = this.findUniqueOrNone(model);
        if (user == null) {
            throw new UserException("connection.user.unknown");
        }
        
        return user;
    }
}
