<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 16.04.2018
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link rel="stylesheet" type="text/css" href="/css/startpage.css" />--%>
<!DOCTYPE  html>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        <%@include file="/css/startpage.css" %>
    </style>
</head>
<body>
    ${message}
    <div class="form">
    <div class="login-page">

            <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                <input type="text" placeholder="username" name="username" id="username"/>
                <input type="password" placeholder="password" name="password" id="password"/>
                <button>login</button>
                <p class="message">Not registered? <a href="${pageContext.request.contextPath}/registration">Create an account</a></p>
            </form>
        </div>
    </div>
</body>
</html>
