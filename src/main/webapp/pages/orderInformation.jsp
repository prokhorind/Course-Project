<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 15.05.2018
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/pages/i18n.jsp" %>
<!DOCTYPE  html>
<html lang="${language}">
<head>
    <title><fmt:message key="oinf.title" bundle="${bundle}"/></title>
    <style type="text/css"><%@include file="/css/startpage.css" %></style>
</head>
<body>
<a href="${pageContext.request.contextPath}/start?lang=en&command=changelang&page=orderInformation.jsp"><img src="${pageContext.request.contextPath}/pages/picture/united-kingdom_l.png" width="25" height="15"></a>
<a href="${pageContext.request.contextPath}/start?lang=ru&command=changelang&page=orderInformation.jsp"><img src="${pageContext.request.contextPath}/pages/picture/russia_l.png" width="25" height="15"></a>
    <div class="form">
        <div class="info">
            <fmt:message key="oinf.info" bundle="${bundle}"/><br>
            <p>  <fmt:message key="oinf.login" bundle="${bundle}"/><br> <c:out value="${information.login}"></c:out> </p>
            <p>  <fmt:message key="oinf.email" bundle="${bundle}"/><br> <c:out value="${information.email}"></c:out> </p>
            <c:forEach var="detail" items="${information.detailSet}">
                <div class="detail">
                    <p>  <fmt:message key="oinf.detailId" bundle="${bundle}"/><br><c:out value="${detail.detailId}"></c:out> </p>
                    <p>  <fmt:message key="oinf.detailName" bundle="${bundle}"/><br> <c:out value="${detail.name}"></c:out></p>
                    <p>  <fmt:message key="oinf.detailReason" bundle="${bundle}"/><br><c:out value="${detail.reason}"></c:out></p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
