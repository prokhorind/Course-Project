package com.project.course.service;

import com.project.course.dao.*;
import com.project.course.dto.IdWithPOR;
import com.project.course.entity.ApprovedOrder;
import com.project.course.entity.DisApprovedOrder;
import com.project.course.entity.Order;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;
import com.project.course.util.OrderStatus;
import com.project.course.util.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
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
    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    public Set<Order> getOrders(long from, long numberOfPages) throws ServiceException {
        Set<Order> orderSet;
        try {
            orderSet = new HashSet<>();
            try {
                TransactionUtil.beginTransaction();
                    orderSet = orderDao.getOrders(from, numberOfPages);
                TransactionUtil.commit();
                logger.info("got"+ orderSet.size()+" orders");
            } catch (DAOException e) {
                TransactionUtil.rollback();
                logger.error(e.getMessage());
                throw new ServiceException(e);
            } catch (DataBaseException e) {
                TransactionUtil.rollback();
                logger.error(e.getMessage());
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
                logger.info("got"+ orderSet.size()+" orders for role:"+role);
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
                logger.error(e.getMessage());
                throw new ServiceException(e);
            } catch (DataBaseException e) {
                TransactionUtil.rollback();
                logger.error(e.getMessage());
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
                logger.info("order with status "+orderStatus.toString() +" = "+orders);
            }catch (DAOException e){
                logger.error(e.getMessage());
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
                logger.info("orders approved");
            } catch (DAOException e) {
                TransactionUtil.rollback();
                logger.error(e.getMessage());
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
                logger.info("orders denied");
            } catch (DAOException e) {
                logger.error(e.getMessage());
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
                logger.info("orders completed");
            } catch (DAOException e) {
                logger.error(e.getMessage());
                TransactionUtil.rollback();
                throw new ServiceException(e);
            }finally {
                TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
    }

    public long getUserIdByOrderId(long orderId)throws ServiceException{
        long userId;
        try {
            userId =0;
            try {
                TransactionUtil.beginTransaction();
                    userId = orderDao.getUserIdByOrderId(orderId);
                TransactionUtil.commit();
                logger.info("got user Id by order Id:"+orderId);
            } catch (DAOException e) {
                logger.error(e.getMessage());
                TransactionUtil.rollback();
                throw new ServiceException(e);
            }finally {
                TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
        return userId;
    }
    public void deleteOrders(List <String> ids) throws ServiceException {
        try {
            try {
                TransactionUtil.beginTransaction();
                for(String id :ids) {
                   orderDao.deleteOrder(Long.valueOf(id));
                }
                TransactionUtil.commit();
                logger.info("orders deleted");
            } catch (DAOException e) {
                logger.error(e.getMessage());
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
                logger.info("user "+name+" orders counted");
            }catch (DAOException e){
                logger.error(e.getMessage());
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
            logger.info("orders counted");
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
            logger.info("order added");
        } catch (DAOException e) {
            logger.error("can't add order"+ e.getMessage());
            throw new ServiceException(e);
        } catch (DataBaseException e) {
            logger.error("can't add order" + e.getMessage());
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

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}