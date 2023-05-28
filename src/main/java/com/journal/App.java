package com.journal;

import com.journal.model.Entry;
import com.journal.model.User;
import com.journal.utils.PasswordUtil;
import com.journal.utils.SessionUtil;
import com.journal.repository.EntryDao;
import com.journal.repository.UserDao;

public class App {

    public static void main( String[] args ) {

        EntryDao entryDao = new EntryDao();
        UserDao userDao = new UserDao();
        SessionUtil session = new SessionUtil(userDao,"secureDude", "password");

//        Entry test = new Entry("testByRamblinMan", session.userCurrentlyLoggedIn());
//
//        entryDao.create(test);

//        entryDao.delete();
    }

}
