package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;
import com.project.course.entity.Order;
import com.project.course.exception.ServiceException;
import com.project.course.service.LoginService;
import com.project.course.service.OrderService;
import com.project.course.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Created by kleba on 14.05.2018.
 */
public class SwitchOrdersCommand implements Command {
    private ServiceFactory sf =ServiceFactory.getInstatice();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LoginService loginService = sf.getLoginService();
        OrderService os = sf.getOrderService();
        int numberOfContent=5;
        long from;
        HttpSession session = request.getSession();
        String role = (String) request.getSession().getAttribute("role");
        String login = (String) request.getSession().getAttribute("name");
        try {
            long numberOfpage = Long.parseLong(request.getParameter("pagenumber"));
            if(numberOfpage==1){
                from=0;
            }else{
                from = numberOfContent * numberOfpage-numberOfContent;
            }
            Set<Long> numberOfOrders = os.countOrders(numberOfContent);
            Set<Order> orderSet= os.getOrders(login,role, (int) from,numberOfContent);
            session.setAttribute("orders",orderSet);
            session.setAttribute("numbers",numberOfOrders);
            response.sendRedirect(request.getContextPath()+loginService.chooseMainPage(role));
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
