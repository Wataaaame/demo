<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department Detail</title>
</head>
<body>
    <h1>Department Detail</h1>

    <hr>

    <table>
        <tr>
            <td>Department NO</td>
            <td>${deptDetail.deptno}</td>
        </tr>
        <tr>
            <td>Department Name</td>
            <td>${deptDetail.dname}</td>
        </tr>
        <tr>
            <td>Department Location</td>
            <td>${deptDetail.loc}</td>
        </tr>
    </table>

    <input type="button" value="back" onclick="window.history.back()">
</body>