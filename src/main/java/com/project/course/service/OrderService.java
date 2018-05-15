package com.project.course.service;

import com.project.course.dao.*;
import com.project.course.dto.IdWithPOR;
import com.project.course.entity.ApprovedOrder;
import com.project.course.entity.Detail;
import com.project.course.entity.DisApprovedOrder;
import com.project.course.entity.Order;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;
import com.project.course.util.OrderStatus;
import com.project.course.util.Roles;

import java.math.BigDecimal;
import java.security.Provider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kleba on 10.05.2018.
 */
public class OrderService {
    private final DaoFactory factory = DaoFactory.getInstance();
    private UserDao userDao = factory.getUserDao();
    private OrderDao orderDao = factory.getOrderDao();
    private ApprovedOrderDao approvedOrderDao = factory.getApprovedOrderDao();
    private DisApprovedOrderDao disApprovedOrderDao = factory.getDisApprovedOrderDao();
    public Set<Order> getOrders(long from, long numberOfPages) throws ServiceException {
        Set<Order> orderSet;
        try {
            orderSet = new HashSet<>();
            try {
                TransactionUtil.beginTransaction();
                    orderSet = orderDao.getOrders(from, numberOfPages);
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            } catch (DataBaseException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            } finally {
                TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw  new ServiceException(e);
        }
        return orderSet;
    }

    public Set<Order> getOrders(String name, String role, int from, int numberOfOrders) throws ServiceException {
        Set<Order> orderSet =  new HashSet<>();
        try {
            try {
                TransactionUtil.beginTransaction();
                if (role.equalsIgnoreCase(Roles.EMPLOYEE.toString())) {
                    orderSet = orderDao.getOrders(from, numberOfOrders, OrderStatus.APPROVED);
                } else if (role.equalsIgnoreCase(Roles.ADMIN.toString())) {
                    orderSet = orderDao.getOrders(from, numberOfOrders, OrderStatus.REG);
                } else if (role.equalsIgnoreCase(Roles.MEMBER.toString())) {
                    long id = userDao.getUserId(name);
                    long orders = orderDao.countOrders(id);
                    orderSet = orderDao.getOrders(from, numberOfOrders, id);
                }
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            } catch (DataBaseException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            } finally {
                    TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw  new ServiceException(e);
        }
        return orderSet;
    }

    public Set<Long> countOrdersByStatus(OrderStatus orderStatus,long numberOfOrders) throws ServiceException {
        Set<Long> numbers = new HashSet<>();
        try{
            long orders=0;
            long number=0;
            try {
                TransactionUtil.beginTransaction();
                orders =  orderDao.countOrders(orderStatus);
                number = (long) Math.ceil((double) orders / numberOfOrders);
                TransactionUtil.commit();
            }catch (DAOException e){
                TransactionUtil.rollback();
            }finally {
                TransactionUtil.endTransaction();

            }
            for(long i=1;i<=number;i++){
                numbers.add(i);
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
        return numbers;
    }

    public void approveOrder(List<IdWithPOR> idWithPORList) throws ServiceException {
        try {
            try {
                TransactionUtil.beginTransaction();
                for(IdWithPOR por:idWithPORList) {
                    orderDao.updateOrderStatus(por.getId(),OrderStatus.APPROVED.toString());
                    ApprovedOrder ao = new ApprovedOrder(por.getId(),BigDecimal.valueOf(Double.parseDouble(por.getPor())));
                    approvedOrderDao.addApprovedOrder(ao);
                }
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            }finally {
                TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
    }


    public void denyOrder(List<IdWithPOR> idWithPORList) throws ServiceException {
        try {
            try {
                TransactionUtil.beginTransaction();
                for(IdWithPOR por:idWithPORList) {
                    orderDao.updateOrderStatus(por.getId(),OrderStatus.DENIED.toString());
                    DisApprovedOrder dao = new DisApprovedOrder(por.getId(),por.getPor());
                    disApprovedOrderDao.addDisApprovedOrder(dao);
                }
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            }finally {
                TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
    }

    public void doOrders(List<String> ids) throws ServiceException {
        try {
            try {
                TransactionUtil.beginTransaction();
                for(String id :ids) {
                    orderDao.updateOrderStatus(Long.valueOf(id),OrderStatus.CLOSED.toString());
                }
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            }finally {
                TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
    }

    public void deleteOrders(List <String> ids) throws ServiceException {
        try {
            try {
                TransactionUtil.beginTransaction();
                for(String id :ids) {
                   orderDao.deleteOrder(Long.valueOf(id));
                }
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
                throw new ServiceException(e);
            }finally {
                TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
    }

    public Set<Long> countUserOrder(String name, long numberOfOrders) throws ServiceException {
        Set<Long> numbers = new HashSet<>();
        try{
            long orders=0;
            long number=0;
            try {
                TransactionUtil.beginTransaction();
                long userId = userDao.getUserId(name);
                orders =  orderDao.countOrders(userId);
                number = (long) Math.ceil((double) orders / numberOfOrders);
                TransactionUtil.commit();
            }catch (DAOException e){
                TransactionUtil.rollback();
            }finally {
                TransactionUtil.endTransaction();

            }
            for(long i=1;i<=number;i++){
                numbers.add(i);
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
        return numbers;
    }

    public Set<Long> countOrders(int numberOfOrders) throws ServiceException {
        Set<Long> numbers = new HashSet<>();
        try{
            long orders=0;
            long number=0;
        try {
            TransactionUtil.beginTransaction();
         orders =  orderDao.countOrders();
            number = (long) Math.ceil((double) orders / numberOfOrders);
            TransactionUtil.commit();
        }catch (DAOException e){
            TransactionUtil.rollback();
        }finally {
            TransactionUtil.endTransaction();

        }
            for(long i=1;i<=number;i++){
                numbers.add(i);
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
        return numbers;
    }

    public long addOrder(String login, String[] name, String[] reason) throws ServiceException {

        long orderId = -1;
        try {
            TransactionUtil.beginTransaction();
            long userId = userDao.getUserId(login);
            Order order = new Order(userId);
            orderDao.addOrder(order);
           orderId = orderDao.getLastOrder();
            TransactionUtil.commit();
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }finally {
            try {
                TransactionUtil.endTransaction();
            } catch (DataBaseException e) {
                throw new ServiceException(e);
            }
        }
        return  orderId;
    }
}