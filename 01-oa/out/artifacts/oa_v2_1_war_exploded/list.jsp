<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department List</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
    <script>
        function del(deptno) {
            if (window.confirm('delete?')) {
                // 四种方式发送请求
                // window.location
                // window.location.href
                // document.location
                document.location.href = "dept/delete?deptno="+deptno;
            }
        }
    </script>

    <h1>Department List</h1>
    <hr>

    <%-- 欢迎信息 --%>
    Hello, ${username}
    <a href="user/logout">[logout]</a>

    <br>
    <a href="add.jsp">add</a>

    <table border="1px solid black" width="90%" height="150px" align="center">
        <thead>
            <tr>
                <th>NO</th>
                <th>Department NO</th>
                <th>Department Name</th>
                <th>Manufacture</th>
            </tr>
        </thead>
        <tbody align="center">
            <c:forEach items="${deptList}" var="dept" varStatus="deptStatus">
                <tr>
                    <td>${deptStatus.count}</td>
                    <td>${dept.deptno}</td>
                    <td>${dept.dname}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="del(${dept.deptno})">delete</a>
                        <a href="dept/detail?f=edit&deptno=${dept.deptno}">edit</a>
                        <a href="dept/detail?f=detail&deptno=${dept.deptno}">detail</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    online people: ${onlineCount}
</body>
</html>