<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 06.05.2018
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pages/i18n.jsp" %>
<!DOCTYPE  html>
<html lang="${language}">
<head>
    <script>
        <%@include file="/js/adddetail.js" %>
    </script>
    <title><fmt:message key="member.title" bundle="${bundle}"/></title>
    <style type="text/css">
        <%@include file="/css/startpage.css" %>
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/start?lang=en&command=changelang&page=member.jsp"><img src="${pageContext.request.contextPath}/pages/picture/united-kingdom_l.png" width="25" height="15"></a>
<a href="${pageContext.request.contextPath}/start?lang=ru&command=changelang&page=member.jsp"><img src="${pageContext.request.contextPath}/pages/picture/russia_l.png" width="25" height="15"></a>
    <div class="form">

            <button  onclick="addFields()"><fmt:message key="member.button2" bundle="${bundle}"/></button>
            <form action="/start">
                <div id="Order">
                    <fmt:message key="member.detail1" bundle="${bundle}"/>
                    <input type="hidden" name="command" value="addorder"/>
                    <input type="text" required name="name" placeholder="<fmt:message key="member.name" bundle="${bundle}"/>" id="name">
                    <input type="text" required name="reason" placeholder="<fmt:message key="member.reason" bundle="${bundle}"/>" id="reason">
                    <button><fmt:message key="member.button1" bundle="${bundle}"/></button>
                </div>
            </form>
        </div>
    <div class="form">
        <div id="table">
            <table border="1">
                <caption>Order</caption>
                <tr>
                    <th><fmt:message key="table.date" bundle="${bundle}"/></th>
                    <th><fmt:message key="table.status" bundle="${bundle}"/></th>
                    <th><fmt:message key="table.id" bundle="${bundle}"/></th>
                </tr>
                <c:forEach var="order" items="${orders}">
                 <tr>
                   <td> <c:out value=" ${order.date}"></c:out></td>
                   <td> <c:out value=" ${order.status}"></c:out></td>
                    <td><a href="${pageContext.request.contextPath}/start?command=orderinfo&id=${order.orderId}"><c:out value="${order.orderId}"></c:out></a></td>
                </tr>
                 </c:forEach>
                </table>
            <div id ="pages">
                <c:forEach var="page" items="${pages}">
                    <a href="${pageContext.request.contextPath}/start?command=changeorder&pagenumber=${page}"> <c:out value=" ${page}"></c:out></a>
                </c:forEach>
            </div>
        </div>
        </div>
    <div class="form">
        <div id="comment">
            <form action="/start">
                <fmt:message key="member.comment" bundle="${bundle}"/>
                <input type="hidden" name="command" value="addcomment" placeholder="comment">
                <input type="text" required name="comment">
                <button><fmt:message key="member.button1" bundle="${bundle}"/></button>
            </form>
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
