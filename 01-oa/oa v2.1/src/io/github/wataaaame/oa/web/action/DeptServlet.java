package io.github.wataaaame.oa.web.action;

import io.github.wataaaame.oa.bean.Dept;
import io.github.wataaaame.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理部门表相关 Serlet
 */
// 利用注解编写配置文件
@WebServlet({"/dept/list", "/dept/detail", "/dept/delete", "/dept/add", "/dept/edit"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 已使用 Filter，此处无需判断
        // 获取 session，失败返回 null
//        HttpSession session = request.getSession(false);
//        if (session!=null && session.getAttribute("username")!=null) {
//            // session 不为空且包含了用户信息则进入系统
//
//        } else {
//            // 否则返回登录页面
//            response.sendRedirect(request.getContextPath()+"/autoLogin");
//        }

        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/dept/add".equals(servletPath)) {
            doAdd(request, response);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(request, response);
        }
    }

    /**
     * 连接数据库，查询所有部门信息，将信息收集好，然后跳转到 JSP 做展示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 准备一个集合，专门存放部门
        List<Dept> depts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno, dname from jdept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");

                // 将以上零散的数据封装一个 Java 对象
                Dept dept = new Dept(deptno, dname, null);
                // 将对象放到 depts 集合中
                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        // 将集合放到 request 请求域中
        request.setAttribute("deptList", depts);
        // 转发到 list.jsp 页面
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    /**
     * 查询部门详情数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 需要查询的 deptno
        String deptno = request.getParameter("deptno");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select dname, loc from jdept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                Dept dept = new Dept(deptno, dname, loc);
                request.setAttribute("deptDetail", dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        // 查询完毕后
        // f=detail 转发给 detail.jsp
        // f=edit 转发给 edit.jsp
        request.getRequestDispatcher("/"+request.getParameter("f")+".jsp").forward(request, response);
    }

    /**
     * 删除选中的部门
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取删除的部门号
        String deptno = request.getParameter("deptno");

        // 连接数据库执行删除操作
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "delete from jdept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        String contextPath = request.getContextPath();
        if (count == 1) {
            response.sendRedirect(contextPath+"/dept/list");
        } else {
            response.sendRedirect(contextPath+"/error.jsp");
        }
    }

    /**
     * 新增部门
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置字符集防止乱码
        request.setCharacterEncoding("UTF-8");
        // 获取填写的部门信息
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库新增部门
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "insert into jdept values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        String contextPath = request.getContextPath();
        if (count == 1) {
            response.sendRedirect(contextPath+"/dept/list");
        } else {
            response.sendRedirect(contextPath+"error.jsp");
        }
    }

    /**
     * 修改选中的部门
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取修改后的部门信息
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库完成修改
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "update jdept set dname=?, loc=? where deptno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        String contextPath = request.getContextPath();
        if (count == 1) {
            response.sendRedirect(contextPath+"/dept/list");
        } else {
            response.sendRedirect(contextPath+"/error.jsp");
        }
    }
}
