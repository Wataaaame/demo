<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OA System</title>
</head>
<body>
    <h1>Welcome to OA System</h1>
    <hr>

    <h2>Click for list page</h2>
    <%-- 前端发送请求路径时，如果是绝对路径，需要以 / 开头，加项目名 --%>
    <%-- 动态获取项目名 --%>
<%--    <a href="/oa/dept/list">Department List</a>--%>
    <%-- 查询数据库，需要访问一个 Servlet --%>
<%--    <a href="<%=request.getContextPath()%>/list.jsp">Department List</a>--%>
    <a href="<%=request.getContextPath()%>/old/login.jsp.jsp">Department List</a>
</body>
</html>