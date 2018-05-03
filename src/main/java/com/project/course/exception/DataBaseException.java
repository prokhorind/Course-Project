package com.project.course.exception;

public class DataBaseException extends Exception {

    public DataBaseException() {
    }
    public DataBaseException(Throwable e) {
        super(e);
    }

    public DataBaseException(String s) {
        super(s);
    }
}
