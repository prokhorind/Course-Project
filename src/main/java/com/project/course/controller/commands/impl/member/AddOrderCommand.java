package com.project.course.controller.commands.impl.member;

import com.project.course.controller.commands.Command;
import com.project.course.exception.ServiceException;
import com.project.course.service.DetailService;
import com.project.course.service.OrderService;
import com.project.course.service.ServiceFactory;
import com.project.course.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kleba on 10.05.2018.
 */
public class AddOrderCommand implements Command {

    private Logger logger = LoggerFactory.getLogger(AddOrderCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstatice();
        OrderService os  = serviceFactory.getOrderService();
        DetailService ds = serviceFactory.getDetailService();
        String name = Validation.injectionProtection((String) request.getSession().getAttribute("name"));
        String[] names =  Validation.injectionProtection(request.getParameterValues("name"));
        String[] reasons =  Validation.injectionProtection(request.getParameterValues("reason"));
        try {
            long orderId= os.addOrder(name,names,reasons);
            ds.addDetail(names,reasons,orderId);
            response.sendRedirect(request.getContextPath()+"?command=getdata");
        } catch (IOException e) {
            logger.error("wrong redirect path"+e.getMessage());
        } catch (ServiceException e) {
            logger.error("can't add order"+e.getMessage());
        }
    }
}
