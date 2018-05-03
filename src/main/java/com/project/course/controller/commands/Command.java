package com.project.course.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kleba on 06.05.2018.
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
