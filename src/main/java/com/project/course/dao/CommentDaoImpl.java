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
                throw new DataBaseException(e);
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
    public List<Comment> getComments(long from, long to) throws DAOException,DataBaseException {
        List<Comment> commentList = new ArrayList<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select * from Comment LIMIT ? OFFSET ?";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,to);
            preparedStatement.setLong(2,from);
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
}
