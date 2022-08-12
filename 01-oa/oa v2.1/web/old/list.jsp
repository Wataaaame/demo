<%@ page import="java.util.List" %>
<%@ page import="io.github.wataaaame.oa.bean.Dept" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department List</title>
</head>
<body>
    <script>
        function del(deptno) {
            if (window.confirm('delete?')) {
                // 四种方式发送请求
                // window.location
                // window.location.href
                // document.location
                document.location.href = "<%=request.getContextPath()%>/dept/delete?deptno="+deptno;
            }
        }
    </script>

    <h1>Department List</h1>
    <hr>

    <%-- 欢迎信息 --%>
    Hello, <%=session.getAttribute("username")%>
    <a href="<%=request.getContextPath()%>/user/logout">[logout]</a>

    <br>
    <a href="<%=request.getContextPath()%>/add.jsp">add</a>

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
            <%
                /*从 request 域中取出集合*/
                List<Dept> deptList = (List<Dept>)request.getAttribute("deptList");
                /*定义一个 i 变量用于展示 no*/
                int i = 0;
                /*循环遍历*/
                for (Dept dept : deptList) {
            %>
                    <tr>
                        <td><%=++i%></td>
                        <td><%=dept.getDeptno()%></td>
                        <td><%=dept.getDname()%></td>
                        <td>
                            <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">delete</a>
                            <%--
                                修改的第一步也需要查询
                                可以添加 f 变量，执行查询程序，通过该变量决定最终跳转页面
                            --%>
                            <a href="<%=request.getContextPath()%>/dept/detail?f=edit&deptno=<%=dept.getDeptno()%>">edit</a>
                            <a href="<%=request.getContextPath()%>/dept/detail?f=detail&deptno=<%=dept.getDeptno()%>">detail</a>
                        </td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>