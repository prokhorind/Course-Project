<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 02.05.2018
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form</title>
    <style type="text/css">
        <%@include file="/css/startpage.css" %>
        </style>
</head>
<body>
<div class="form">
    <form class="register-form" action="${pageContext.request.contextPath}/registration" method="post">
        <input type="text" placeholder="login" name="login" id="login"/>
        <input type="password" placeholder="password" name="password" id="password"/>
        <input type="text" placeholder="email address" name="email" id="email"/>
        <button>create</button>
        <p class="message">Already registered? <a href="${pageContext.request.contextPath}/">Sign In</a></p>
    </form>
    </div>
</body>
</html>
