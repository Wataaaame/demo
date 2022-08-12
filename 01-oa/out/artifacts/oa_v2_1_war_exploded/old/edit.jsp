<%@ page import="io.github.wataaaame.oa.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Department</title>
</head>
<body>
    <h1>Edit Department</h1>
    <hr>
    <%
        /* 获取请求域传递过来的部门信息 */
        Dept dept = (Dept)request.getAttribute("deptDetail");
    %>
    <form action="<%=request.getContextPath()%>/dept/edit" method="post">
        <table>
            <tr>
                <td>Department NO</td>
                <td>
                    <!-- 部门编号不可修改，sql 语句需要按部门编号编写 where 语句 -->
                    <input type="text" name="deptno" value="<%=dept.getDeptno()%>" readonly>
                </td>
            </tr>
            <tr>
                <td>Department Name</td>
                <td>
                    <input type="text" name="dname" value="<%=dept.getDname()%>">
                </td>
            </tr>
            <tr>
                <td>Department Location</td>
                <td>
                    <input type="text" name="loc" value="<%=dept.getLoc()%>">
                </td>
            </tr>
        </table>
        <input type="submit" value="edit">
        <input type="reset" value="reset">
    </form>
</body>
</html>