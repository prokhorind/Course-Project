<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 06.05.2018
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/pages/i18n.jsp" %>
<!DOCTYPE  html>
<html lang="${language}">
<head>
    <title>admin</title>
    <style type="text/css"><%@include file="/css/startpage.css" %></style>
</head>
<body>
<a href="${pageContext.request.contextPath}/start?lang=en&command=changelang"><img src="${pageContext.request.contextPath}/pages/picture/united-kingdom_l.png" width="25" height="15"></a>
<a href="${pageContext.request.contextPath}/start?lang=ru&command=changelang"><img src="${pageContext.request.contextPath}/pages/picture/russia_l.png" width="25" height="15"></a>
    <div class="form">
        <div id="table">
            <table border="1">
                <caption>Order</caption>
                <form action="${pageContext.request.contextPath}/start" method="post">
                    <input type="hidden" name="command" value="crudorder"/>
                <tr><th>Date</th><th>Status</th><th>Id</th><th>Select</th><th>Price/Reason</th></tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td> <c:out value=" ${order.date}"></c:out></td>
                        <td> <c:out value=" ${order.status}"></c:out></td>
                        <td><c:out value=" ${order.orderId}"></c:out></td>
                        <td><input type="checkbox" name="select" id="${order.orderId}"  value="${order.orderId}">  </td>
                        <td><input type="text" name="pr" value=""  placeholder="Price/Reason">  </td>
                    </tr>
                </c:forEach>
                    <button value="deny" name="decision"> Deny</button>
                    <button  name="decision" value="approve"> Approve</button>
                    <button  name="decision" value="delete"> Delete</button>
                </form>
            </table>
            <div id ="pages">
                <c:forEach var="page" items="${pages}">
                    <a href="${pageContext.request.contextPath}/start?command=changeorder&pagenumber=${page}"> <c:out value=" ${page}"></c:out></a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="form">
        <div id="exit">
            <form action="/start">
                <input type="hidden" name="command" value="LOGOUT"/>
                <button>logout</button>
            </form>
        </div>
    </div>
</body>
</html>
