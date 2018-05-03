<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 06.05.2018
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/pages/i18n.jsp" %>
<!DOCTYPE  html>
<html lang="${language}">
<head>
    <title><fmt:message key="admin.title" bundle="${bundle}"/></title>
    <style type="text/css"><%@include file="/css/startpage.css" %></style>
</head>
<body>
<a href="${pageContext.request.contextPath}/start?lang=en&command=changelang&page=admin.jsp"><img src="${pageContext.request.contextPath}/pages/picture/united-kingdom_l.png" width="25" height="15"></a>
<a href="${pageContext.request.contextPath}/start?lang=ru&command=changelang&page=admin.jsp"><img src="${pageContext.request.contextPath}/pages/picture/russia_l.png" width="25" height="15"></a>
    <div class="form">
        <div id="table">
            <table border="1">
                <caption><fmt:message key="admin.order" bundle="${bundle}"/></caption>
                <form action="${pageContext.request.contextPath}/start" method="post">
                    <input type="hidden" name="command" value="crudorder"/>
                <tr>
                    <th><fmt:message key="table.date" bundle="${bundle}"/></th>
                    <th><fmt:message key="table.status" bundle="${bundle}"/></th>
                    <th><fmt:message key="table.id" bundle="${bundle}"/></th>
                    <th><fmt:message key="table.select" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.pricereason" bundle="${bundle}"/></th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td> <c:out value=" ${order.date}"></c:out></td>
                        <td> <c:out value=" ${order.status}"></c:out></td>
                        <td><a href="${pageContext.request.contextPath}/start?command=orderinfo&id=${order.orderId}"><c:out value="${order.orderId}"></c:out></a></td>
                        <td><input type="checkbox" name="select" id="${order.orderId}"  value="${order.orderId}">  </td>
                        <td><input type="text" name="pr" value=""  placeholder="<fmt:message key="admin.pricereason" bundle="${bundle}"/>">  </td>
                    </tr>
                </c:forEach>
                    <button value="deny" name="decision"> <fmt:message key="admin.deny" bundle="${bundle}"/></button>
                    <button  name="decision" value="approve"><fmt:message key="admin.approve" bundle="${bundle}"/></button>
                    <button  name="decision" value="delete"> <fmt:message key="admin.delete" bundle="${bundle}"/></button>
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
                <button><fmt:message key="logoutbutton" bundle="${bundle}"/></button>
            </form>
        </div>
    </div>
</body>
</html>
