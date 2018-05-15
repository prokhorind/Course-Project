package com.project.course.service;

import com.project.course.dao.DaoFactory;
import com.project.course.dao.DetailDao;
import com.project.course.entity.Detail;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.transaction.TransactionUtil;

/**
 * Created by kleba on 10.05.2018.
 */
public class DetailService {
    private final DaoFactory factory = DaoFactory.getInstance();
    public void addDetail(String[] names,String[] reasons , long userId){

       DetailDao detailDao = factory.getDetailDao();
        try {
            TransactionUtil.beginTransaction();
            for(int i=0;i<names.length;i++) {
                Detail detail = new Detail(names[i], reasons[i], userId);
                detailDao.addDetail(detail);
            }
            TransactionUtil.commit();
        } catch (DataBaseException e) {
            try {
                TransactionUtil.rollback();
            } catch (DataBaseException e1) {
                e1.printStackTrace();
            }
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                TransactionUtil.endTransaction();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }

    }
}
