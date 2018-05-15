package com.project.course.dao;

public class DaoFactory {
    private static final DaoFactory factory = new DaoFactory();

    private UserDao userDao =new UserDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();
    private UserRoleDao userRoleDao= new UserRoleDaoImp();
    private OrderDao orderDao = new OrderDaoImpl();
    private DetailDao detailDao = new DetailDaoImpl();
    private CommentDao commentDao = new CommentDaoImpl();
    private ApprovedOrderDao approvedOrderDao = new ApprovedOrderDaoImpl();
    private DisApprovedOrderDao disApprovedOrderDao = new DisApprovedOrderDaoImpl();
    private MailDao mailDao = new MailDaoImpl();
    public static DaoFactory getInstance(){
        return factory;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public ApprovedOrderDao getApprovedOrderDao() {
        return approvedOrderDao;
    }

    public DetailDao getDetailDao() {
        return detailDao;
    }

    public DisApprovedOrderDao getDisApprovedOrderDao() {
        return disApprovedOrderDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public MailDao getMailDao() {
        return mailDao;
    }
}
