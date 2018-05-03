<%--
  Created by IntelliJ IDEA.
  User: kleba
  Date: 17.04.2018
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Show Error Page</title>
</head>
<body>
<h1>Opps...</h1>
<p>An error with code ${pageContext.errorData.statusCode} occurred.</p>
</body>
</html>
