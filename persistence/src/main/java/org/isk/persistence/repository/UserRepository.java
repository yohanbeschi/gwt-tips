package org.isk.persistence.repository;

import org.isk.persistence.core.repository.Repository;
import org.isk.persistence.domain.User;
import org.isk.persistence.exception.UserException;

public interface UserRepository extends Repository<User, Long> {

    /**
     * Connect a user with the given login and password.
     * 
     * @param login
     *            is the login of the user.
     * @param password
     *            is the password linked to the login.
     * @return a User matching the login/password pair, throws an exception otherwise.
     * @throws UserException
     *             'connection.blank.fields' if login or password is blank.<br>
     *             'connection.user.unknown' if no matching login/password has been found.
     */
    User connect(String login, String password) throws UserException;

}
