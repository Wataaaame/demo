package io.github.wataaaame.oa.web.filter;

import io.github.wataaaame.oa.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 强制类型转换，转换为 HttpServletRequest 和 HttpServletResponse
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 获取 session，失败返回 null
        HttpSession session = request.getSession(false);
        // 验证登录状态
        /**
         * 不能拦截情形：
         * 1. index 页面
         * 2. 免登录操作
         * 3. 登录操作
         * 4. 退出操作
         */
        // 将不能拦截页面与访问页面做比较
        // 先获取当前路径
        String servletPath = request.getServletPath();
//        if (session!=null && session.getAttribute("username")!=null) {
        // 记录监听器判断 user 对象
        if (session!=null && session.getAttribute("user")!=null) {
            // 验证通过则跳转下一项
            filterChain.doFilter(request, response);
        } else {
            // 否则返回登录页面
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }
}
