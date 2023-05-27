package com.journal;

import com.journal.model.Entry;
import com.journal.repository.EntryDao;

public class App 
{
    public static void main( String[] args )
    {
        EntryDao entryDao = new EntryDao();

        Entry test = new Entry("testEntry");

        entryDao.create(test);

//        entryDao.delete();
    }
}
