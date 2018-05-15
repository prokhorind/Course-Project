package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kleba on 10.05.2018.
 */
public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        try {
            response.sendRedirect( request.getContextPath()+"/start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
