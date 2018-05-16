package com.project.course.controller.commands.impl.member;

import com.project.course.controller.commands.Command;
import com.project.course.exception.ServiceException;
import com.project.course.service.CommentService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kleba on 11.05.2018.
 */
public class AddCommentCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory sf =ServiceFactory.getInstatice();
        CommentService cs = sf.getCommentService();
        String name = Validation.injectionProtection((String) request.getSession().getAttribute("name"));
        String comment = Validation.injectionProtection( request.getParameter("comment"));
        try {
            cs.addComment(name,comment);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect(request.getContextPath()+"?command=getdata");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
