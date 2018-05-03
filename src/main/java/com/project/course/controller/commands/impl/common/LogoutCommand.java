package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kleba on 10.05.2018.
 */
public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = LoggerFactory.getLogger(LogoutCommand.class);
        request.getSession().invalidate();
        try {
            response.sendRedirect( request.getContextPath()+"/start");
        } catch (IOException e) {
            logger.error("wrong redirect page");
        }
    }
}
