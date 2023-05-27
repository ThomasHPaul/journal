package com.journal;

import com.journal.model.Entry;
import com.journal.model.User;
import com.journal.repository.EntryDao;
import com.journal.repository.UserDao;

import java.util.Optional;

public class App 
{
    public static void main( String[] args )
    {
        User userLoggedIn;
        long userId;

        EntryDao entryDao = new EntryDao();
        UserDao userDao = new UserDao();

        userLoggedIn = userDao.getUserByUsernameAndPassword("tpaul", "password");
//        User user = new User("tpaul", "password", "tpaul@tpaul.net", "Thomas", "Paul");
        userId = userLoggedIn.getIdUser();
        Entry test = new Entry("testEntry", userId);

        entryDao.create(test);

//        entryDao.delete();
    }
}
