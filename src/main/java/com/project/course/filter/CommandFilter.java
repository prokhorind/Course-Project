package com.project.course.filter;

import com.project.course.controller.commands.CommandEnum;
import org.apache.logging.log4j.util.Strings;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by kleba on 13.05.2018.
 */
@WebFilter("/start")
public class CommandFilter  implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command  = request.getParameter("command");
        String login = (String) request.getSession().getAttribute("name");

        if(Objects.nonNull(command)&&Objects.isNull(login)&&(
                command.equalsIgnoreCase(CommandEnum.GETDATA.toString())||
                command.equalsIgnoreCase(CommandEnum.ADDCOMMENT.toString())||
                command.equalsIgnoreCase(CommandEnum.ADDORDER.toString())||
                command.equalsIgnoreCase(CommandEnum.LOGOUT.toString())||
                command.equalsIgnoreCase(CommandEnum.ADDORDER.toString())||
                command.equalsIgnoreCase(CommandEnum.DOORDER.toString())||
                command.equalsIgnoreCase(CommandEnum.ORDERINFO.toString())||
                command.equalsIgnoreCase(CommandEnum.CHANGEORDER.toString()))){
            ((HttpServletResponse)servletResponse).sendRedirect(request.getContextPath()+"/start");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
