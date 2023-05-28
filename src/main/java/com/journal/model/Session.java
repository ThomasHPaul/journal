package com.journal.model;

import com.journal.repository.UserDao;

public class Session {
    private User userLoggedIn;
    private boolean someoneLoggedIn = false;

    public long userCurrentlyLoggedIn() {
        return userLoggedIn.getIdUser();
    }

    public Session(UserDao userDao, String username, String password) {
        userLoggedIn = userDao.getUserByUsernameAndPassword(username, password);
        someoneLoggedIn = true;
    }
}
