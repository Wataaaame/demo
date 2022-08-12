package io.github.wataaaame.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC 的工具类
 */
public class DBUtil {
    // 绑定一个配置文件
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    // 需要从配置文件中获取的常量
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    static {
        try {
            // 驱动注册只需一次，放在静态代码块中，在类加载中执行即可
            // driver 路径不能写死，方便以后扩展
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库方法
     * @return 返回一个连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 获取数据库连接
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    /**
     * 释放资源
     * @param conn 连接对象
     * @param stmt 数据库操作对象
     * @param rs 查询结果集对象
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
