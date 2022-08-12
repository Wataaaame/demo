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

@WebServlet("/autoLogin")
public class AutoLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 提前声明用户名和密码，若通过检验，则自动登录
        String username = null;
        String password = null;
        // 获取 Cookie 数组
        Cookie[] cookies = request.getCookies();

        // 若数组不为 null，数组中也可能包含其他 Cookie
        // 循环遍历，判断数组中是否包含 username 和 password
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                } else if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }

        // 若用户名与密码皆存在，则连接数据库判断用户是否存在
        if (username!=null && password!=null) {
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
                    // 登录成功
                    HttpSession session = request.getSession();
//                    session.setAttribute("username", username);
                    // 记录监听器，存储 user 对象到 session
                    User user = new User(username);
                    session.setAttribute("user", user);

                    response.sendRedirect(request.getContextPath()+"/dept/list");
                } else {
                    // 账户密码不一致，登录失败
                    response.sendRedirect(request.getContextPath()+"/error.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn, ps, rs);
            }
        } else {
            // 若不存在则跳转到默认页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
