package com.journal.repository;

import com.journal.model.Entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EntryDao extends AbstractDao implements Dao<Entry> {

    @Override
    public Optional<Entry> findById(long id) {
        Optional<Entry> entry = Optional.empty();
        String sql = "select id, entryText from entry where id = ?";

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
            prepStmt.setLong(1, id);

            try (
                    ResultSet rset = prepStmt.executeQuery();
                    ) {
                Entry resEntry = new Entry();

                if(rset.next()) {
                    resEntry.setId(rset.getLong("id"));
                    resEntry.setWhenCreated(rset.getDate("whenCreated"));
                }

                entry = Optional.of(resEntry);
            }
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return entry;
    }
}
