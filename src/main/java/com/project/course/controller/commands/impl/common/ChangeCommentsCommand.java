package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;
import com.project.course.dto.Comment;
import com.project.course.exception.ServiceException;
import com.project.course.service.CommentService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by kleba on 11.05.2018.
 */
public class ChangeCommentsCommand implements Command {
    private  ServiceFactory sf =ServiceFactory.getInstatice();
    private  CommentService cs = sf.getCommentService();
    Logger logger = LoggerFactory.getLogger(ChangeCommentsCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int numberOfContent=5;
        long from;
        HttpSession session = request.getSession();
        try {
        long numberOfpage = Long.parseLong(request.getParameter("pagenumber"));
            if(numberOfpage==1){
                from=0;
            }else{
                from = numberOfContent * numberOfpage - numberOfContent;
            }
        Set<Long> numberOfComments =  cs.getNumberOfComments(numberOfContent);
        List<Comment> commentList =cs.getComments(numberOfContent,from);
        session.setAttribute("numberOfComments",numberOfComments);
        session.setAttribute("comments",commentList);
        response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
        }  catch (IOException e) {
            logger.error("wrong path to jsp file");
        } catch (ServiceException e) {
            logger.error("Can't change comments,"+e.getMessage());
        }
    }
}
