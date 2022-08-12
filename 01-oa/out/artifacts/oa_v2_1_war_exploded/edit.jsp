<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Department</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
    <h1>Edit Department</h1>
    <hr>
    <form action="dept/edit" method="post">
        <table>
            <tr>
                <td>Department NO</td>
                <td>
                    <!-- 部门编号不可修改，sql 语句需要按部门编号编写 where 语句 -->
                    <input type="text" name="deptno" value="${deptDetail.deptno}" readonly>
                </td>
            </tr>
            <tr>
                <td>Department Name</td>
                <td>
                    <input type="text" name="dname" value="${deptDetail.dname}">
                </td>
            </tr>
            <tr>
                <td>Department Location</td>
                <td>
                    <input type="text" name="loc" value="${deptDetail.loc}">
                </td>
            </tr>
        </table>
        <input type="submit" value="edit">
        <input type="reset" value="reset">
    </form>
</body>
</html>