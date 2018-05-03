package com.project.course.controller;


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
@WebServlet("/")
public class StartServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session =req.getSession();
        session.setAttribute("message","helloworld");
        res.sendRedirect(req.getContextPath()+"/pages/index.jsp");
    }
}
