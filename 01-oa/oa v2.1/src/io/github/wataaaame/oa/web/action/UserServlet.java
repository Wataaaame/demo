package io.github.wataaaame.oa.web.action;

import io.github.wataaaame.oa.bean.User;
import io.github.wataaaame.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理用户登录的 Servlet
 */
@WebServlet({"/user/login", "/user/logout"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/logout".equals(servletPath)) {
            doLogout(request, response);
        }
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取用户输入的用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 连接数据库判断合法性
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select id from juser where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 验证通过则使用 Session 存储用户信息
                HttpSession session = request.getSession();
//                session.setAttribute("username", username);
                // 记录监听器，存储 user 对象到 session
                User user = new User(username);
                session.setAttribute("user", user);

                // 10 天免登录
                String f = request.getParameter("f");
                if ("1".equals(f)) {
                    // 创建 Cookie 对象
                    // 存储用户名
                    Cookie cookieUnm = new Cookie("username", username);
                    // 存储密码
                    // 真实情况下应该加密后存储
                    Cookie cookiePwd = new Cookie("password", password);

                    // 设置 Cookie 的有效时间
                    cookieUnm.setMaxAge(60 * 60 * 24 * 10);
                    cookiePwd.setMaxAge(60 * 60 * 24 * 10);

                    // 设置 path（只要访问应用，浏览器就一定要携带这两个 Cookie）
                    cookieUnm.setPath(request.getContextPath());
                    cookiePwd.setPath(request.getContextPath());

                    // 响应 Cookie 到浏览器
                    response.addCookie(cookieUnm);
                    response.addCookie(cookiePwd);
                }

                // 跳转到部门列表
                response.sendRedirect(request.getContextPath()+"/dept/list");
            } else {
                // 验证失败
                response.sendRedirect(request.getContextPath()+"/error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 销毁 Session
//            session.invalidate();
            // 记录监听器，删除 user 对象
            session.removeAttribute("user");
        }

        // 获取浏览器传递的 Cookie
        Cookie[] cookies = request.getCookies();
        // 遍历寻找用户名与密码 Cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName()) || "password".equals(cookie.getName())) {
                    // 令 Cookie 失效
                    cookie.setMaxAge(0);
                    // 设置相同路径
                    cookie.setPath(request.getContextPath());
                    // 告知浏览器
                    response.addCookie(cookie);
                }
            }
        }

        // 跳转回登录页
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
