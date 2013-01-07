package org.isk.persistence.dao.impl;

import javax.inject.Named;
import javax.inject.Singleton;

import org.isk.persistence.core.dao.GenericDao;
import org.isk.persistence.dao.UserDAO;
import org.isk.persistence.domain.User;

@Named("userDao")
@Singleton
public class UserDAOImpl extends GenericDao<User, Long> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }
}
