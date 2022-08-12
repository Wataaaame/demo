<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<h1>Please login first</h1>
<br>
<form action="user/login" method="post">
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
