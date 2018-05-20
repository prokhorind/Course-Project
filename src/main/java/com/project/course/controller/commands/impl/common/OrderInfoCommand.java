package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;
import com.project.course.dto.OrderInformation;
import com.project.course.entity.Detail;
import com.project.course.entity.User;
import com.project.course.exception.ServiceException;
import com.project.course.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;

/**
 * Created by kleba on 15.05.2018.
 */
public class OrderInfoCommand implements Command {
   private ServiceFactory serviceFactory = ServiceFactory.getInstatice();
   private OrderService os = serviceFactory.getOrderService();
   private DetailService ds = serviceFactory.getDetailService();
   private UserService us = serviceFactory.getUserService();
   private Logger logger = LoggerFactory.getLogger(OrderInfoCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long orderId= Long.valueOf(request.getParameter("id"));
        Set<Detail> detailSet ;
        User user;
        OrderInformation information = null;
        long userId;
        try {
            userId = os.getUserIdByOrderId(orderId);
            detailSet = ds.getDetail(orderId);
            user = us.getUserById(userId);
            information = new OrderInformation(user.getLogin(),user.getEmail(),detailSet);

        } catch (ServiceException e) {
            logger.error("failed got order info"+e.getMessage());
        }
        session.setAttribute("information",information);
        try {
            response.sendRedirect(request.getContextPath()+"/pages/orderInformation.jsp");
        } catch (IOException e) {
            logger.error("wrong redirect page");
        }
    }
}
