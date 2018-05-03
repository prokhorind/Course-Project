package com.project.course.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Created by kleba on 30.04.2018.
 */
public class Comment {
    private long commentId;
    private String message;
    private Date date;
    private long userId;

    public Comment(String message, Date date, long userId) {
        this.message = message;
        this.date = date;
        this.userId = userId;
    }

    public Comment(long userId, String message){
        this.userId = userId;
        this.date = new Date(new java.util.Date().getTime());
        this.message = message;
    }

    public Comment(long commentId, String message, Date date, long userId) {
        this.commentId = commentId;
        this.message = message;
        this.date = date;
        this.userId = userId;
    }

    public Comment() {
    }

    ;

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCommentId() {
        return commentId;
    }

    public long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                userId == comment.userId &&
                Objects.equals(message, comment.message) &&
                Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, message, date, userId);
    }
}

