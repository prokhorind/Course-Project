package com.project.course.service;

public class ServiceFactory {
    private final static ServiceFactory serviceFactory = new ServiceFactory();
    private final RegUserService regUserService =new RegUserService();

    public static ServiceFactory getInstatice(){
        return serviceFactory;
    }

    public RegUserService getRegUserService(){
        return regUserService;
    }


}
