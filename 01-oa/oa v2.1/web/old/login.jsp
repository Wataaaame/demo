<%--
  Created by IntelliJ IDEA.
  User: vv
  Date: 2022/8/7
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Please login first</h1>
<br>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    <table>
        <tr>
            <td>username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="password" name="password"></td>
        </tr>
    </table>
    <input type="checkbox" name="f" value="1">no password in 10 days
    <br>
    <input type="submit" value="login">
    <input type="reset" value="reset">
</form>
</body>
</html>
