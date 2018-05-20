package com.project.course.dto;

/**
 * Created by kleba on 19.05.2018.
 */
public class User {
    private String login;
    private String email;
    private String role;
    private long userId;

    public User(String login, String email, String role,long userId) {
        this.login = login;
        this.email = email;
        this.role = role;
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
