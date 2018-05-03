package com.project.course.dao;

import com.project.course.entity.Comment;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

import java.util.List;

/**
 * Created by kleba on 03.05.2018.
 */
public interface CommentDao {
    void addComment(Comment comment) throws DAOException, DataBaseException;
    void deleteComment(long id) throws DAOException, DataBaseException;
    void updateCommentMessage(long id,String message)throws DAOException, DataBaseException;
    List<Comment> getComments(long from,long to) throws DataBaseException, DAOException;
}
