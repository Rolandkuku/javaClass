<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: roland
  Date: 21/03/2017
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Users</h1>
    <h2>Hello <%= session.getAttribute("name") %></h2>
    <ul>
        <% for(String user: (ArrayList<String>)request.getAttribute("userList")) { %>
        <li>
            <%= user %>
        </li>
        <% } %>
    </ul>
    <a href="/logout">Logout</a>
</body>
</html>
