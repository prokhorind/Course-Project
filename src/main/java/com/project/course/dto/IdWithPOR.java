package com.project.course.dto;

/**
 * Created by kleba on 15.05.2018.
 */
public class IdWithPOR {

    private long id;
    private String por;

    public IdWithPOR(long id, String por) {
        this.id = id;
        this.por = por;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPor() {
        return por;
    }

    public void setPor(String por) {
        this.por = por;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
