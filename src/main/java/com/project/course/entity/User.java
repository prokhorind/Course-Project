package com.project.course.entity;

import java.util.Objects;

/**
 * Created by kleba on 30.04.2018.
 */
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String pass;

    public User(){};

    public User(String email, String login, String pass) {
        this.email = email;
        this.login = login;
        this.pass = pass;
    }

    public User(String name, String surname, String email, String login, String pass) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) &&
                Objects.equals(pass, user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, login, pass);
    }
}
