package com.project.course.controller.commands.impl.admin;

import com.project.course.controller.commands.Command;
import com.project.course.dto.IdWithPOR;
import com.project.course.exception.ServiceException;
import com.project.course.service.OrderService;
import com.project.course.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * Created by kleba on 14.05.2018.
 */
public class OrderCommand implements Command {
  private  ServiceFactory serviceFactory = ServiceFactory.getInstatice();
  private  OrderService orderService = serviceFactory.getOrderService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String decision = request.getParameter("decision");
        String[] ids = request.getParameterValues("select");
        String [] pr = request.getParameterValues("pr");
        List<String> listPR = new ArrayList(Arrays.asList(pr));
        listPR.removeAll(Arrays.asList("", null));
        List<String> id = new ArrayList(Arrays.asList(ids));
        List<IdWithPOR> valueList = new ArrayList<>();
        Iterator itrId = id.iterator();
        Iterator itrPr = listPR.iterator();
        while(itrId.hasNext()&&itrPr.hasNext()){
            IdWithPOR idWithPoR = new IdWithPOR(Long.valueOf((String) itrId.next()), (String) itrPr.next());
            valueList.add(idWithPoR);
        }
        try {
       switch (decision){
           case "approve":
               orderService.approveOrder(valueList);
               break;
           case "deny":
               orderService.denyOrder(valueList);
               break;
           case "delete":
               orderService.deleteOrders(id);
               break;
       }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        try {
            request.getSession().setAttribute("name",request.getSession().getAttribute("name"));
            request.getSession().setAttribute("role",request.getSession().getAttribute("role"));
            response.sendRedirect(request.getContextPath()+"?command=getdata");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
