package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;
import com.project.course.entity.User;
import com.project.course.exception.ServiceException;
import com.project.course.service.RegUserService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kleba on 06.05.2018.
 */
public class RegistryCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse response) {
        HttpSession session =req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try {
            if (Validation.isEmailValid(email) && Validation.isLoginValid(login) && Validation.isPasswordValid(password)) {
                User user = new User(email, login, password);
                ServiceFactory factory = ServiceFactory.getInstatice();
                RegUserService regUserService = factory.getRegUserService();
                try {
                    regUserService.regUser(user);
                    session.setAttribute("message", "Enter your login and password");
                    response.sendRedirect(req.getContextPath() + "/pages/index.jsp");
                } catch (ServiceException e) {

                }

            } else {
                session.setAttribute("message", "Wrong data");
                    response.sendRedirect(req.getContextPath() + "/pages/reg.jsp");
            }
        }catch (IOException e){

        }
    }
}
