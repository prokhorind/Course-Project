<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 15.05.2018
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE  html>
<html>
<head>
    <title>Order Information</title>
    <style type="text/css"><%@include file="/css/startpage.css" %></style>
</head>
<body>
    <div class="form">
        <div class="info">
            Info<br>
            <p> Login: <c:out value="${information.login}"></c:out> </p>
            <p> Email: <c:out value="${information.email}"></c:out> </p>
            <c:forEach var="detail" items="${information.detailSet}">
                <div class="detail">
                    <p> Detail id: <c:out value="${detail.detailId}"></c:out> </p>
                    <p> Name:  <c:out value="${detail.name}"></c:out></p>
                    <p> Reason:<c:out value="${detail.reason}"></c:out></p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
