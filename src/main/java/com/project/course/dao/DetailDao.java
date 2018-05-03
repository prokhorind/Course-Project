package com.project.course.dao;

import com.project.course.entity.Detail;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

import java.util.Set;

/**
 * Created by kleba on 03.05.2018.
 */
public interface DetailDao {
    void addDetail(Detail detail) throws DAOException, DataBaseException;
    void deleteDetail(long id) throws DAOException, DataBaseException;
    Set<Detail> getDetails(long from, long to) throws DataBaseException, DAOException;
    Set<Detail> getDetails(long orderId)throws DataBaseException, DAOException;
}
