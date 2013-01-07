package org.isk.persistence.repository;

import java.util.List;

import javax.inject.Inject;

import org.isk.persistence.Constants;
import org.isk.persistence.domain.User;
import org.isk.persistence.exception.UserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:spring/applicationContext-test.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class UserRepositoryTest {
    
    @Inject
    private UserRepository userRepository;
    /**
     * Find all User[s] in the database.
     */
    @Test
    public void findAll() {
        final List<User> users = this.userRepository.find();
        Assert.assertEquals(1, users.size());
    }
    
    /**
     * Connect a user with a given login and password. 
     * @throws UserException
     */
    @Test
    @Rollback
    public void connect() throws UserException {
        final User user = this.userRepository.connect(Constants.USER_ROOT, Constants.ROOT_PASSWORD);
        
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
    }
    
    /**
     * Blank login.
     */
    @Test
    @Rollback
    public void connectKo1() {
        boolean exceptionRaised = false;
        try {
            this.userRepository.connect("", Constants.ROOT_PASSWORD);
        } catch (UserException e) {
            Assert.assertEquals("connection.blank.fields", e.getMessage());
            exceptionRaised = true;
        }
        
        Assert.assertTrue(exceptionRaised);
    }
    
    /**
     * Null password.
     */
    @Test
    @Rollback
    public void connectKo2() {
        boolean exceptionRaised = false;
        try {
            this.userRepository.connect(Constants.USER_ROOT, null);
        } catch (UserException e) {
            Assert.assertEquals("connection.blank.fields", e.getMessage());
            exceptionRaised = true;
        }
        
        Assert.assertTrue(exceptionRaised);
    }
}
