package com.project.course.service;

import com.project.course.dao.DaoFactory;
import com.project.course.dao.DetailDao;
import com.project.course.entity.Detail;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by kleba on 10.05.2018.
 */
public class DetailService {
    private final DaoFactory factory = DaoFactory.getInstance();
    private DetailDao detailDao = factory.getDetailDao();
    private Logger logger = LoggerFactory.getLogger(DetailService.class);

    public void addDetail(String[] names, String[] reasons, long orderId) throws ServiceException {
        try {
            try {
                TransactionUtil.beginTransaction();
                for (int i = 0; i < names.length; i++) {
                    Detail detail = new Detail(names[i], reasons[i], orderId);
                    detailDao.addDetail(detail);
                }
                TransactionUtil.commit();
                logger.info("details added");
            } catch (DAOException e) {
                logger.error(" can't add details "+e.getMessage());
                TransactionUtil.rollback();
            } finally {
                    TransactionUtil.endTransaction();
            }
        }catch (DataBaseException e){
            throw new ServiceException(e);
        }
    }

    public Set<Detail> getDetail(long orderId) throws ServiceException {
        Set<Detail> detailSet;
        try {
            try {
                TransactionUtil.beginTransaction();
                detailSet = detailDao.getDetails(orderId);
                TransactionUtil.commit();
                logger.info("got details for order with id:"+ orderId);
            } catch (DAOException e) {
                TransactionUtil.rollback();
                logger.error( "couldn't got details for order with id:"+ orderId);
                throw new ServiceException(e);
            } finally {
                TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return detailSet;
    }
}
