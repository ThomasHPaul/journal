package com.journal;

import com.journal.repository.utils.SessionUtil;
import com.journal.repository.utils.LoggerUtil;
import com.journal.repository.EntryDao;
import com.journal.repository.UserDao;

public class App {

    public static void main( String[] args ) {

        LoggerUtil.initLogManager();

        EntryDao entryDao = new EntryDao();
        UserDao userDao = new UserDao();
        SessionUtil session = new SessionUtil(userDao,"secureDude", "password");

        session.logout();

//        Entry test = new Entry("testByRamblinMan", session.userCurrentlyLoggedIn());
//
//        entryDao.create(test);

//        entryDao.delete();
    }

}
