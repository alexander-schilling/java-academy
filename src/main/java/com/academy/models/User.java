package com.academy.models;

public class User extends Person {
    private String username;
    private String password; // TODO: Use encrypted password
    private String token;

    public User(int id, String username, String password, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, String firstName, String lastName, String token) {
        super(id, firstName, lastName);
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
