package com.journal.utils;

import com.journal.model.User;
import com.journal.repository.UserDao;

public class SessionUtil {
    private User userLoggedIn;
    private boolean someoneLoggedIn = false;

    public long userCurrentlyLoggedIn() {
        return userLoggedIn.getIdUser();
    }

    public SessionUtil(UserDao userDao, String username, String password) {
        userLoggedIn = userDao.getUserByUsernameAndPassword(username, password);
        someoneLoggedIn = true;
    }
}
