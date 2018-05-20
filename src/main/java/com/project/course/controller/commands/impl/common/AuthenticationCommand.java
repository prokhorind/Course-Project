package com.project.course.controller.commands.impl.common;


import com.project.course.controller.commands.Command;
import com.project.course.dto.User;
import com.project.course.exception.ServiceException;
import com.project.course.service.LoginService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Roles;
import com.project.course.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kleba on 06.05.2018.
 */
public class AuthenticationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        Logger logger = LoggerFactory.getLogger(AuthenticationCommand.class);
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstatice();
            LoginService loginService = serviceFactory.getLoginService();
            String username = Validation.injectionProtection(request.getParameter("username"));
            String password = Validation.injectionProtection(request.getParameter("password"));
            try {
                user = loginService.login(username, password);
                if(user==null){
                    request.getSession().setAttribute("name", null);
                    request.getSession().setAttribute("role", null);
                }else {
                    request.getSession().setAttribute("name", user.getLogin());
                    request.getSession().setAttribute("role", user.getRole());
                }
                response.sendRedirect(request.getContextPath() + "?command=getdata");
            } catch (ServiceException e) {
                logger.error("login failed," + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
            }
        }catch (IOException e) {
            logger.error("wrong response link");

        }
    }
}
