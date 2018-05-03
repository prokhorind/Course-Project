package com.project.course.controller;


import com.project.course.controller.commands.Command;
import com.project.course.controller.commands.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Created by kleba on 16.04.2018.
 */
@WebServlet("/start")
public class DispatcherServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }
    public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse res) {
        CommandFactory cf =CommandFactory.getInstatice();
        Command command = cf.getCommand(req);
        command.execute(req,res);
    }
}
