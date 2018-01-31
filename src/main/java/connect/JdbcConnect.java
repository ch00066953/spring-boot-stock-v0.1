package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnect {

	public static Connection conn;

	public static Connection getORAConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.1.17:1521:amarcms";// 设置连接字符串
		String username = "amarcms11";// 用户名
		String password = "amarcms11";// 密码
		Connection conn = null; // 创建数据库连接对象
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	public static Connection getDB2Conn() {
		String driver = "com.ibm.db2.jcc.DB2Driver";
		String url = "jdbc:db2://192.168.1.17:60000/AMARSCF:currentSchema=DB2INST2;";// 设置连接字符串
		String username = "db2inst2";// 用户名
		String password = "db2inst2";// 密码
		Connection conn = null; // 创建数据库连接对象
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public static Connection getMYConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/wedding";// 设置连接字符串
		String username = "root";// 用户名
		String password = "MySQL55";// 密码
		Connection conn = null; // 创建数据库连接对象
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static Connection getStockConn() throws SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/stock";// 设置连接字符串
		String username = "stock";// 用户名
		String password = "stock";// 密码
		Connection conn = null; // 创建数据库连接对象
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// new oracle.jdbc.driver.OracleDriver();
		conn = DriverManager.getConnection(url, username, password);

		return conn;
	}

	public static Connection getSAEConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_stockwin?useUnicode=true&amp;characterEncoding=GBK&amp;rewriteBatchedStatements=true&amp;useOldAliasMetadataBehavior=true";// 设置连接字符串
		String username = "x1nly3jx04";//用户名
		String password = "ylm1y0hmjwh12h2ki12lmyz4wj2mx1zxkxy5wjkx";//密码
		Connection conn = null; //创建数据库连接对象
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
			DriverManager.setLoginTimeout(8888888);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static Connection getConn() throws SQLException {
		if (conn == null || conn.isClosed()) {
			conn = JdbcConnect.getStockConn(); // 统一管理
//			conn = JdbcConnect.getSAEConn(); // 统一管理
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void close(PreparedStatement ps) {
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void close(Connection conn, PreparedStatement ps) {
		if (ps != null && conn != null)
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
