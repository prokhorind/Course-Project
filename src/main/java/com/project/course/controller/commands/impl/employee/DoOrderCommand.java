package com.project.course.controller.commands.impl.employee;

import com.project.course.controller.commands.Command;
import com.project.course.exception.ServiceException;
import com.project.course.service.OrderService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Validation;

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
                response.sendRedirect(request.getContextPath() + "?command=getdata");
            }
        }catch (IOException e){

        }
    }
}
