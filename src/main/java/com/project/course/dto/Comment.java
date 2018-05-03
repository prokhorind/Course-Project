package com.project.course.dto;

import java.sql.Date;

/**
 * Created by kleba on 11.05.2018.
 */
public class Comment {
   private String username;
   private Date date;
   private String comment;

    public Comment(String username, Date date, String comment) {
        this.username = username;
        this.date = date;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "username='" + username + '\'' +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }
}
