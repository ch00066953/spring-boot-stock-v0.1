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
        String url = "jdbc:oracle:thin:@192.168.1.17:1521:amarcms";// 璁剧疆杩炴帴瀛楃涓�
        String username = "amarcms11";//鐢ㄦ埛鍚�
        String password = "amarcms11";//瀵嗙爜
        Connection conn = null; //鍒涘缓鏁版嵁搴撹繛鎺ュ璞�
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
        PreparedStatement pstmt; // 寤虹珛涓�涓煡璇㈠璞�
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
           // 寤虹珛涓�涓粨鏋滈泦,鐢ㄦ潵淇濆瓨鏌ヨ鍑烘潵鐨勭粨鏋�
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

  //涓绘柟娉曪紝杩涜娴嬭瘯
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