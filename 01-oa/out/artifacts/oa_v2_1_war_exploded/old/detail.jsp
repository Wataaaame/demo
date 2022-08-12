<%@ page import="io.github.wataaaame.oa.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department Detail</title>
</head>
<body>
    <h1>Department Detail</h1>

    <hr>

    <%--获取请求域元素--%>
    <%
        Dept dept = (Dept)request.getAttribute("deptDetail");
    %>
    <table>
        <tr>
            <td>Department NO</td>
            <td><%=dept.getDeptno()%></td>
        </tr>
        <tr>
            <td>Department Name</td>
            <td><%=dept.getDname()%></td>
        </tr>
        <tr>
            <td>Department Location</td>
            <td><%=dept.getLoc()%></td>
        </tr>
    </table>

    <input type="button" value="back" onclick="window.history.back()">
</body>