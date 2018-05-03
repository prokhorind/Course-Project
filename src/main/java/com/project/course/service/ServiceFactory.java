package com.project.course.service;

public class ServiceFactory {
    private final static ServiceFactory serviceFactory = new ServiceFactory();
    private final RegUserService regUserService =new RegUserService();
    private final LoginService  loginService = new LoginService();
    private final OrderService orderService = new OrderService();
    private final DetailService detailService = new DetailService();
    private final CommentService commentService = new CommentService();
    private final UserService userService = new UserService();
    public static ServiceFactory getInstatice(){
        return serviceFactory;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public RegUserService getRegUserService(){
        return regUserService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public DetailService getDetailService() {
        return detailService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public UserService getUserService() {return userService;}
}
