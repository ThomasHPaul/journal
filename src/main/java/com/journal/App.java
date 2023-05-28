package com.journal;

import com.journal.model.Entry;
import com.journal.model.Session;
import com.journal.repository.EntryDao;
import com.journal.repository.UserDao;

public class App {

    public static void main( String[] args ) {

        EntryDao entryDao = new EntryDao();
        UserDao userDao = new UserDao();

        Session session = new Session(userDao,"test", "password");
//        User user = new User("tpaul", "password", "tpaul@tpaul.net", "Thomas", "Paul");
        Entry test = new Entry("test2", session.userCurrentlyLoggedIn());

        entryDao.create(test);

//        entryDao.delete();
    }

}
