<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 06.05.2018
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css"><%@include file="/css/startpage.css" %></style>
    <title>Employee</title>
</head>
<body>
<div class="form">
    <div id="table">
        <table border="1">
            <caption>Order</caption>
            <form action="${pageContext.request.contextPath}/start" method="post">
                <input type="hidden" name="command" value="doorder"/>
                <tr><th>Date</th><th>Status</th><th>Id</th><th>Select</th></tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td> <c:out value=" ${order.date}"></c:out></td>
                        <td> <c:out value=" ${order.status}"></c:out></td>
                        <td><a href="${pageContext.request.contextPath}/start?command=orderinfo&id=${order.orderId}"><c:out value="${order.orderId}"></c:out></a></td>
                        <td><input type="checkbox" name="select" id="${order.orderId}"  value="${order.orderId}">  </td>
                    </tr>
                </c:forEach>
                <button  name="decision" value="do"> Do</button>
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
