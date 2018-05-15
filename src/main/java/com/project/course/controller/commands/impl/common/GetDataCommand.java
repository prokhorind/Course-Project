package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;
import com.project.course.entity.Order;
import com.project.course.entity.Role;
import com.project.course.exception.ServiceException;
import com.project.course.service.LoginService;
import com.project.course.service.OrderService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.OrderStatus;
import com.project.course.util.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by kleba on 10.05.2018.
 */
public class GetDataCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstatice();
        LoginService loginService = serviceFactory.getLoginService();
        OrderService orderService = serviceFactory.getOrderService();
        String login = (String) request.getSession().getAttribute("name");
        String role = (String) request.getSession().getAttribute("role");
        try {
            int numberOfOrdersPerPage = 5 ;
            Set<Long> numberOfOrders ;
            if(role.equalsIgnoreCase(Roles.MEMBER.toString())){
                numberOfOrders = orderService.countUserOrder(login,numberOfOrdersPerPage);
            }else if(role.equalsIgnoreCase(Roles.EMPLOYEE.toString())){
                numberOfOrders =orderService.countOrdersByStatus(OrderStatus.APPROVED,numberOfOrdersPerPage);
            } else {
                numberOfOrders = orderService.countOrdersByStatus(OrderStatus.REG,numberOfOrdersPerPage);
            }
            Set<Order> orderSet= orderService.getOrders(login,role,0,numberOfOrdersPerPage);
            request.getSession().setAttribute("pages",numberOfOrders);
            request.getSession().setAttribute("orders",orderSet);
            response.sendRedirect(request.getContextPath()+loginService.chooseMainPage(role));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
