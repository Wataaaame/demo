package io.github.wataaaame.oa.bean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * User 监听器对象只存储用户的登录相关信息
 */
public class User implements HttpSessionBindingListener {
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 用户登录
        // 获取 ServletContext
        ServletContext application = event.getSession().getServletContext();
        // 获取在线人数变量
        Integer onlineCount = (Integer)application.getAttribute("onlineCount");
        // 第一个人登录时，onlineCount 为空，需手动指定为1
        if (onlineCount == null) {
            application.setAttribute("onlineCount", 1);
        } else {
            // 否则在线人数加一，并存储到域中
            application.setAttribute("onlineCount", ++onlineCount);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 用户退出
        // 获取 application 域
        ServletContext application = event.getSession().getServletContext();
        // 获取在线用户人数
        Integer onlineCount = (Integer)application.getAttribute("onlineCount");
        // 在线人数减1后存储到 application 域
        application.setAttribute("onlineCount", --onlineCount);
    }
}
