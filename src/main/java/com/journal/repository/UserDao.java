package com.journal.repository;

import com.journal.model.User;
import com.journal.repository.utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends AbstractDao implements Dao<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());

    @Override
    public Optional<User> findById(long id) {
        String sql = "select idUser, username, password, email, firstName, lastName from user where idUser = ?";
        Optional<User> user = Optional.empty();

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
            prepStmt.setLong(1, id);

            try (
                    ResultSet rset = prepStmt.executeQuery();
                    ) {

                if(rset.next()) {
                    User resUser = new User(

                        rset.getLong("idUser"),
                        rset.getString("username"),
                        rset.getString("password"), // TODO: should I store password at all on the object for security?
                        rset.getString("email"),
                        rset.getString("firstName"),
                        rset.getString("lastName"));

                    user = Optional.of(resUser);
                }
            }
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }
        LOGGER.log(Level.INFO, "User: " + user + " created.");
        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = new ArrayList<>();

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {

            try (
                    ResultSet rset = prepStmt.executeQuery();
                    ) {

                while (rset.next()) {
                    User resUser = new User(
                        rset.getLong("idUser"),
                        rset.getString("username"),
                        rset.getString("password"),
                        rset.getString("email"),
                        rset.getString("firstName"),
                        rset.getString("lastName"));

                    users.add(resUser);
                }
            }
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return users;
    }

    @Override
    public User update(User user) {
        String sql = "update user set username = ?, password = ?, email = ?, firstName = ?, lastName = ? where idUser = ?";

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
            prepStmt.setString(1, user.getUsername());
            prepStmt.setString(2, user.getPassword());
            prepStmt.setString(3, user.getEmail());
            prepStmt.setString(4, user.getFirstName());
            prepStmt.setString(5, user.getLastName());
            prepStmt.setLong(6, user.getIdUser());

            prepStmt.executeUpdate();
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return user;
    }

    @Override
    public User create(User user) {
        String sql = "insert into User (username, password, email, firstName, lastName, salt) values (?, ?, ?, ?, ?, ?)";

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            String salt = PasswordUtil.generateSalt(512).get();

            prepStmt.setString(1, user.getUsername());
            prepStmt.setString(2, PasswordUtil.hashPassword(user.getPassword(), salt).get());
            prepStmt.setString(3, user.getEmail());
            prepStmt.setString(4, user.getFirstName());
            prepStmt.setString(5, user.getLastName());
            prepStmt.setString(6, salt);

            prepStmt.executeUpdate();

            try (
                    ResultSet genKey = prepStmt.getGeneratedKeys();
                    ) {
                if(genKey.next()) {
                    user.setIdUser(genKey.getLong(1));
                }
            }
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }
        return user;
    }

    @Override
    public int delete(User user) {
        String sql = "delete from user where id = ?";
        int rowsAffected = 0;

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
            prepStmt.setLong(1, user.getIdUser());

            rowsAffected = prepStmt.executeUpdate();
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
        }
        return rowsAffected;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "select idUser, username, password, email, firstName, lastName from user where username = ? and password = ?";

        User user = new User();
        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ) {
                prepStmt.setString(1, username);
                prepStmt.setString(2, getUserPassword(username, password));

            try (
                    ResultSet rset = prepStmt.executeQuery();
            ) {
                if(rset.next()) {
                    User resUser = new User(
                            rset.getLong("idUser"),
                            rset.getString("username"),
                            rset.getString("password"),
                            rset.getString("email"),
                            rset.getString("firstName"),
                            rset.getString("lastName")
                    );
                    user = resUser;
                }
            }
        }

        catch(SQLException sqe) {
            LOGGER.log(Level.SEVERE, sqe.getMessage());
            sqe.printStackTrace();
        }
        return user;
    }

    private String getUserPassword(String username, String password) {
        String saltSql = "select salt from user where username = ?";
        String userSalt = null;

        try (
                Connection con = getConnection();
                PreparedStatement saltStmt = con.prepareStatement(saltSql);
        ) {
            saltStmt.setString(1, username);

            try (
                    ResultSet saltSet = saltStmt.executeQuery();
            ) {
                if (saltSet.next()) {
                    userSalt = saltSet.getString("salt");
                }
            }
        } catch(SQLException sqe) {
            sqe.printStackTrace();
        }

        return PasswordUtil.hashPassword(password, userSalt).get();
    }
}
