package com.project.course.controller.commands.impl.employee;

import com.project.course.controller.commands.Command;
import com.project.course.controller.commands.impl.member.AddOrderCommand;
import com.project.course.exception.ServiceException;
import com.project.course.service.OrderService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by kleba on 15.05.2018.
 */
public class DoOrderCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstatice();
    private OrderService orderService = serviceFactory.getOrderService();
    private Logger logger = LoggerFactory.getLogger(DoOrderCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] ids = Validation.injectionProtection(request.getParameterValues("select"));
            try {
                request.getSession().setAttribute("name", request.getSession().getAttribute("name"));
                request.getSession().setAttribute("role", request.getSession().getAttribute("role"));
                orderService.doOrders(Arrays.asList(ids));
                response.sendRedirect(request.getContextPath() + "?command=getdata");
            } catch (ServiceException e) {
                logger.error("can't do order"+e.getMessage());
                response.sendRedirect(request.getContextPath() + "?command=getdata");
            }
        }catch (IOException e){
            logger.error("wrong redirect path:"+e.getMessage());
        }
    }
}
