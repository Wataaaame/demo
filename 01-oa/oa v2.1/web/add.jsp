<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Department</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<h1>Add a new department</h1>
<hr>
<form action="dept/add" method="post">
    <table>
        <tr>
            <td>Department NO</td>
            <td>
                <input type="text" name="deptno">
            </td>
        </tr>
        <tr>
            <td>Department Name</td>
            <td>
                <input type="text" name="dname">
            </td>
        </tr>
        <tr>
            <td>Department Location</td>
            <td>
                <input type="text" name="loc">
            </td>
        </tr>
    </table>
    <input type="submit" value="add">
    <input type="reset" value="reset">
</form>
</body>
</html>