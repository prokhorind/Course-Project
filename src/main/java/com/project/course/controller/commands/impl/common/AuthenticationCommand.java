package com.project.course.controller.commands.impl.common;


import com.project.course.controller.commands.Command;
import com.project.course.exception.ServiceException;
import com.project.course.service.LoginService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Roles;
import com.project.course.util.Validation;

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
        String role = null;
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstatice();
        LoginService loginService = serviceFactory.getLoginService();
        String username= Validation.injectionProtection(request.getParameter("username"));
        String password= Validation.injectionProtection(request.getParameter("password"));
        try {
            role= loginService.login(username,password);
            if(role==null||role.isEmpty()){
                response.sendRedirect(request.getContextPath()+"/pages/index.jsp");
            }else{
            request.getSession().setAttribute("name",username);
            request.getSession().setAttribute("role",role);
            response.sendRedirect(request.getContextPath()+"?command=getdata");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
