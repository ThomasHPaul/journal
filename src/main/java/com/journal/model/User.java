package com.journal.model;

public class User {
    private long idUser;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public User() {}

    public User(long idUser, String username, String password, String email, String firstName, String lastName) {
        this.idUser = idUser;
        this.username = username;
        setPassword(password);
        setEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        setPassword(password);
        setEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getIdUser() { return this.idUser; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getEmail() { return this.email; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }

    public void setIdUser(long id) { idUser = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; } // TODO: need password complexity checker
    public void setEmail(String email) { this.email = email; } // TODO: need email pattern validation
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
