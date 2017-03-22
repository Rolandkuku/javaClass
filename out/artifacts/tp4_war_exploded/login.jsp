<%--
  Created by IntelliJ IDEA.
  User: roland
  Date: 21/03/2017
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Hello <%= session.getAttribute("name") %></h2>
<h4>Login</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label>
        login
        <input name="name" type="text">
    </label>
    <label>
        pwd
        <input type="text" name="pwd">
    </label>
    <button type="submit">login</button>
</form>
<h4>Or subscribe</h4>
<form action="${pageContext.request.contextPath}/subscribe" method="post">
    <label>
        login
        <input name="name" type="text">
    </label>
    <label>
        pwd
        <input type="text" name="pwd">
    </label>
    <button type="submit">subscribe</button>
</form>
<a href="${pageContext.request.contextPath}/logout">logout</a>
</body>
</html>
