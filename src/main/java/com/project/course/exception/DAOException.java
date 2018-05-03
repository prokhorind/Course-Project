package com.project.course.exception;


public class DAOException extends Exception {
    public DAOException(Throwable e) {
        super(e);
    }
    public DAOException(String s){super(s);}
}
