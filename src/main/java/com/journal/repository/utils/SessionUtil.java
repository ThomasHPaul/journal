package com.journal.repository.utils;

import com.journal.model.User;
import com.journal.repository.UserDao;

import java.net.ConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionUtil {

    private static final Logger LOGGER = Logger.getLogger(SessionUtil.class.getName());

    private User userLoggedIn = null;
    private boolean someoneLoggedIn = false;

    public long userCurrentlyLoggedIn() {
        return userLoggedIn.getIdUser();
    }

    public SessionUtil(UserDao userDao, String username, String password) {

        userLoggedIn = userDao.getUserByUsernameAndPassword(username, password);

        if(userLoggedIn != null) {
            someoneLoggedIn = true;

            LOGGER.log(Level.INFO, "User: " + userLoggedIn.getUsername() + " successfully logged in");
        } else {
            LOGGER.log(Level.INFO, "Failed login with Username: " + username + " Password: " + password);
        }

    }

    public void logout() {
        if(userLoggedIn != null) {
            someoneLoggedIn = false;
            LOGGER.log(Level.INFO, "User: " + userLoggedIn.getUsername() + " successfully logged out");
            userLoggedIn = null;
        }
    }
}
