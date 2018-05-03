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
 * Created by kleba on 16.04.2018.
 */
@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username= req.getParameter("username");
        String password= req.getParameter("password");


        resp.sendRedirect(req.getContextPath()+"/pages/index.jsp");
    }
}
