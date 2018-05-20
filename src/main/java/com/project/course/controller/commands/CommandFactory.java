package com.project.course.controller.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kleba on 06.05.2018.
 */
public class CommandFactory {
    private final static CommandFactory commandFactory = new CommandFactory();

    public Command getCommand(HttpServletRequest request){
        CommandEnum commandEnum=CommandEnum.INVALID;
        String action = request.getParameter("command");
        if (action==null||action.isEmpty()) {
           return  commandEnum.DEFAULT.getCommand();
        }
            commandEnum = CommandEnum.valueOf(action.toUpperCase());
            return commandEnum.getCommand();
    }
    public static CommandFactory getInstatice(){
        return commandFactory;
    }
}
