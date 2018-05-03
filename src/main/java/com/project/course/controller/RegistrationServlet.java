package com.project.course.controller;

import com.project.course.entity.User;
import com.project.course.exception.ServiceException;
import com.project.course.service.RegUserService;
import com.project.course.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kleba on 02.05.2018.
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+"/pages/reg.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login= req.getParameter("login");
        String password= req.getParameter("password");
        String email= req.getParameter("email");
        System.out.println(email);
        User user= new User(email,login,password);

        ServiceFactory factory =ServiceFactory.getInstatice();
        RegUserService regUserService = factory.getRegUserService();
        try {
            regUserService.regUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpSession session =req.getSession();
        session.setAttribute("message","Enter your login and password");
        resp.sendRedirect(req.getContextPath()+"/pages/index.jsp");
    }
}

