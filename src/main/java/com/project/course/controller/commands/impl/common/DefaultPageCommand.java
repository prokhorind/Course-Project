package com.project.course.controller.commands.impl.common;


import com.project.course.controller.commands.Command;
import com.project.course.dto.Comment;
import com.project.course.exception.ServiceException;
import com.project.course.service.CommentService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by kleba on 07.05.2018.
 */
public class DefaultPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int limit = 5;
        long offset =0;
        ServiceFactory serviceFactory = ServiceFactory.getInstatice();
        CommentService commentService = serviceFactory.getCommentService();
        ResourceBundle rb =  Language.getResourceBundle();
        HttpSession session = request.getSession();
        try {
                Set<Long> numberOfComments = commentService.getNumberOfComments(limit);
                List<Comment> commentList = commentService.getComments(limit,offset);
                session.setAttribute("numberOfComments",numberOfComments);
                session.setAttribute("comments",commentList);
                session.setAttribute("language",rb.getLocale().getLanguage());
                response.sendRedirect(request.getContextPath() + "/pages/index.jsp");

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
