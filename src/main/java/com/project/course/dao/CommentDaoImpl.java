package com.project.course.dao;

import com.project.course.entity.Comment;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.transaction.ConnectionWrapper;
import com.project.course.transaction.TransactionUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kleba on 03.05.2018.
 */
public class CommentDaoImpl implements CommentDao {
    @Override
    public void addComment(Comment comment) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Insert into comment(message,date,userId) values(?,?,?)";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setString(1,comment.getMessage());
            ps.setDate(2, comment.getDate());
            ps.setLong(3,comment.getUserId());
            ps.execute();
        } catch (SQLException e) {
            throw  new DAOException(e);
        }finally{
            try {
                con.close();
            }catch(SQLException e){
                throw new   DAOException(e);
            }
        }
    }

    @Override
    public void deleteComment(long id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="DELETE FROM Comment where commentId=?";
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
    }

    @Override
    public void updateCommentMessage(long id, String message) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Update  Comment set message=? where commentId=?";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setString(1,message);
            ps.setLong(2,id);
            ps.execute();
        } catch (SQLException e) {
            throw  new DAOException(e);
        }finally{
            try {
                con.close();
            }catch(SQLException e){
                throw new DataBaseException(e);
            }
        }
    }

    @Override
    public List<Comment> getComments(long limit, long offset) throws DAOException,DataBaseException {
        List<Comment> commentList = new ArrayList<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select * from Comment LIMIT ? OFFSET ?";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,limit);
            preparedStatement.setLong(2,offset);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                Comment comment = new Comment(rs.getLong(1),rs.getString(2),rs.getDate(3),rs.getLong(4));
                commentList.add(comment);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return commentList;
    }

    @Override
    public List<com.project.course.dto.Comment> getDTOComments(long limit, long offset) throws DAOException, DataBaseException {
        List<com.project.course.dto.Comment> commentList = new ArrayList<>();
        ConnectionWrapper con = TransactionUtil.getConnection();

        String sql ="Select comment.message,comment.date ,user.login from Comment,User where comment.userId = user.userId  LIMIT ? OFFSET ?";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,limit);
            preparedStatement.setLong(2,offset);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                com.project.course.dto.Comment comment = new com.project.course.dto.Comment(rs.getString(3)
                        ,rs.getDate(2),rs.getString(1));
                commentList.add(comment);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return commentList;
    }

    @Override
    public long getComments() throws DataBaseException, DAOException {
        long number;
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT COUNT(commentId)FROM Comment";
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = con.createPreparedStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()){
                number=rs.getLong(1);
            }else{
                throw new  SQLException();
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return number;
    }
}
