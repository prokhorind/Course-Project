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
    <script>
        <%@include file="/js/adddetail.js" %>
    </script>
    <title>Member Page</title>
    <style type="text/css">
        <%@include file="/css/startpage.css" %>
    </style>
</head>
<body>
    <div class="form">
            <button  onclick="addFields()">add</button>
            <form action="/start">
                <div id="Order">
                    Detail 1
                    <input type="hidden" name="command" value="addorder"/>
                    <input type="text" placeholder="name" name="name" id="name">
                    <input type="text" placeholder="reason" name="reason" id="reason">
                    <button>submit</button>
                </div>
            </form>
        </div>
    <div class="form">
        <div id="table">
            <table border="1">
                <caption>Order</caption>
                <tr><th>Date</th><th>Status</th><th>Id</th></tr>
                <c:forEach var="order" items="${orders}">
                 <tr>
                   <td> <c:out value=" ${order.date}"></c:out></td>
                   <td> <c:out value=" ${order.status}"></c:out></td>
                    <td><c:out value=" ${order.orderId}"></c:out></td>
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
                Comment
                <input type="hidden" name="command" value="addcomment" placeholder="comment">
                <input type="text" name="comment">
                <button>submit</button>
            </form>
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
