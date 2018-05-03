package com.project.course.dto;

import com.project.course.entity.Detail;

import java.util.Set;

/**
 * Created by kleba on 15.05.2018.
 */
public class OrderInformation {

    private String login;
    private String email;
    private Set<Detail> detailSet;

    public OrderInformation(String login, String email, Set<Detail> detailSet) {
        this.login = login;
        this.email = email;
        this.detailSet = detailSet;
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

    public void setDetailSet(Set<Detail> detailSet) {
        this.detailSet = detailSet;
    }

    public Set<Detail> getDetailSet() {
        return detailSet;
    }
}
