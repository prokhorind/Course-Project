<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 02.05.2018
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pages/i18n.jsp" %>
<!DOCTYPE  html>
<html lang="${language}">
<head>
    <title><fmt:message key="reg.title" bundle="${bundle}"/></title>
    <style type="text/css">
        <%@include file="/css/startpage.css" %>
        </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/start?lang=en&command=changelang&page=reg.jsp"><img src="${pageContext.request.contextPath}/pages/picture/united-kingdom_l.png" width="25" height="15"></a>
<a href="${pageContext.request.contextPath}/start?lang=ru&command=changelang&page=reg.jsp"><img src="${pageContext.request.contextPath}/pages/picture/russia_l.png" width="25" height="15"></a>
<div class="form">
    <form class="register-form" action="${pageContext.request.contextPath}/start" method="post">
        <input type="text" placeholder="<fmt:message key="reg.login" bundle="${bundle}"/>" name="login" id="login"/>
        <input type="password" placeholder="<fmt:message key="reg.pass" bundle="${bundle}"/>" name="password" id="password"/>
        <input type="text" placeholder="<fmt:message key="reg.email" bundle="${bundle}"/>" name="email" id="email"/>
        <input type="hidden" name="command" value="reg"/>
        <button><fmt:message key="reg.button" bundle="${bundle}"/></button>
        <p class="message"><fmt:message key="reg.message1" bundle="${bundle}"/>" <a href="${pageContext.request.contextPath}/start"><fmt:message key="reg.message2" bundle="${bundle}"/></a></p>
    </form>
    </div>
</body>
</html>
