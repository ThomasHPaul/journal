package com.journal.repository;

import com.journal.model.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntryDao extends AbstractDao implements Dao<Entry> {

    @Override
    public Optional<Entry> findById(long id) {
        Optional<Entry> entry = Optional.empty();
        String sql = "select id, entryText, idUser from entry where id = ?";

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
                    resEntry.setWhenCreated(rset.getTimestamp("whenCreated"));
                    resEntry.setEntryText(rset.getString("entryText"));
                    resEntry.setIdUser(rset.getLong("idUser"));
                }

                entry = Optional.of(resEntry);
            }
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return entry;
    }

    @Override
    public List<Entry> findAll() {
        String sql = "select * from entry";
        List<Entry> entries = new ArrayList<>();

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
            try (
                    ResultSet rset = prepStmt.executeQuery();
                    ) {
                while(rset.next()) {
                    Entry resEntry = new Entry();
                    resEntry.setId(rset.getLong("id"));
                    resEntry.setWhenCreated(rset.getTimestamp("whenCreated"));
                    resEntry.setEntryText(rset.getString("entryText"));

                    entries.add(resEntry);
                }
            }
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return entries;
    }

    @Override
    public Entry update(Entry entry) {
        String sql = "update entry set entryText = ? where id = ?";

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
            prepStmt.setString(1, entry.getEntryText());
            prepStmt.setLong(2, entry.getId());
            prepStmt.execute();
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }
        return entry;
    }

    @Override
    public Entry create(Entry entry) {
        String sql = "insert into entry (whenCreated, entryText, idUser) values (?, ?, ?)";

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            prepStmt.setTimestamp(1, new Timestamp(entry.getWhenCreated()));
            prepStmt.setString(2, entry.getEntryText());
            prepStmt.setLong(3, entry.getIdUser());

            prepStmt.executeUpdate();

            try (
                    ResultSet genKeys = prepStmt.getGeneratedKeys();
                    ) {
                if(genKeys.next()) {
                    entry.setId(genKeys.getLong(1));
                }
            }
        }
        catch(SQLIntegrityConstraintViolationException sic) {
            System.out.println("Username password combination not present on the database. Please correct or make a new user.");
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }
    return entry;
    }

    @Override
    public int delete(Entry entry) {
        String sql = "delete from entry where id = ?";
        int rowsAffected = 0;

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql)
                ) {
            prepStmt.setLong(1, entry.getId());

            rowsAffected = prepStmt.executeUpdate();
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return rowsAffected;
    }

    public int delete(int entryId) {
        String sql = "delete from entry where id = ?";
        int rowsAffected = 0;

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql)
        ) {
            prepStmt.setLong(1, entryId);

            rowsAffected = prepStmt.executeUpdate();
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return rowsAffected;
    }
}
