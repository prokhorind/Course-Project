
<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 16.04.2018
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--<link rel="stylesheet" type="text/css" href="/css/startpage.css" />--%>
<%@include file="/pages/i18n.jsp" %>
<!DOCTYPE  html>
<html lang="${language}">
<head>

 <title><fmt:message key="login.title" bundle="${bundle}"/></title>
    <style type="text/css">
        <%@include file="/css/startpage.css" %>
    </style>
    <style type="text/css">
        <%@include file="/css/comment.css" %>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<a href="${pageContext.request.contextPath}/start?lang=en&command=changelang&page=index.jsp"><img src="${pageContext.request.contextPath}/pages/picture/united-kingdom_l.png" width="25" height="15"></a>
<a href="${pageContext.request.contextPath}/start?lang=ru&command=changelang&page=index.jsp"><img src="${pageContext.request.contextPath}/pages/picture/russia_l.png" width="25" height="15"></a>
    <div class="form">
        <div class="login-page">
                <form class="login-form" action="${pageContext.request.contextPath}/start" method="post">
                    <input type="text" placeholder="<fmt:message key="login.login" bundle="${bundle}"/>" name="username" id="username"/>
                    <input type="password" placeholder="<fmt:message key="login.password" bundle="${bundle}"/>" name="password" id="password"/>
                    <input type="hidden" name="command" value="login"/>
                    <button><fmt:message key="login.button" bundle="${bundle}"/></button>
                    <p class="message"><fmt:message key="login.regmessage1" bundle="${bundle}"/> <a href="${pageContext.request.contextPath}/pages/reg.jsp"> <fmt:message key="login.regmessage2" bundle="${bundle}"/></a></p>
                </form>
            </div>

    </div>
    <div class="form">
        <div class="comments">
            Comments<br>
            <c:forEach var="comment" items="${comments}">
                <div class="message-orange">
                    <p class="message-content">From: ${comment.username}</p>
                    <p class="message-content">${comment.comment}!</p>
                    <div class="message-timestamp-right">${comment.date}</div>
                </div>
            </c:forEach>
            <c:forEach var="number" items="${numberOfComments}">
            <a href="${pageContext.request.contextPath}/start?command=changecomments&pagenumber=${number}">${number}</a>

            </c:forEach>
        </div>
    </div>
</body>
</html>
