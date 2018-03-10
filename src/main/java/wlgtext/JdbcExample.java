package wlgtext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcExample {


    public static Connection getConn() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.1.17:1521:amarcms";// 设置连接字符串
        String username = "amarcms11";//用户名
        String password = "amarcms11";//密码
        Connection conn = null; //创建数据库连接对象
        try {
            Class.forName(driver);
            // new oracle.jdbc.driver.OracleDriver();
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    private static int insert(String username, String password) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into users (username,password) values(?,?)";
        PreparedStatement pstmt; // 建立一个查询对象
        try {
            pstmt = conn.prepareStatement(sql);
            // Statement stat = conn.createStatement();
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    private static void query() {
        Connection conn = getConn();
        String sql = "select * from users";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
           // 建立一个结果集,用来保存查询出来的结果
 ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("name: " + rs.getString("username")
                        + " \tpassword: " + rs.getString("password"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int update(String oldName, String newPass) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update users set password='" + newPass
                + "' where username='" + oldName + "'";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);

            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);

            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    private static int delete(String username) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete users where username='" + username + "'";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);

            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);

            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

  //主方法，进行测试
//    public static void main(String[] args) {
//        insert("test", "123");
//        insert("admin", "456");
//        query();
//        update("admin", "456");
//        query();
//        delete("admin");
//        query();
//    }
}