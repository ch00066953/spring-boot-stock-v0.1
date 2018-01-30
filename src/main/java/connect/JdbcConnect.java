package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnect {

	public static Connection conn;

	public static Connection getORAConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.1.17:1521:amarcms";// ���������ַ���
		String username = "amarcms11";// �û���
		String password = "amarcms11";// ����
		Connection conn = null; // �������ݿ����Ӷ���
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
		String url = "jdbc:db2://192.168.1.17:60000/AMARSCF:currentSchema=DB2INST2;";// ���������ַ���
		String username = "db2inst2";// �û���
		String password = "db2inst2";// ����
		Connection conn = null; // �������ݿ����Ӷ���
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
		String url = "jdbc:mysql://localhost:3306/wedding";// ���������ַ���
		String username = "root";// �û���
		String password = "MySQL55";// ����
		Connection conn = null; // �������ݿ����Ӷ���
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
		String url = "jdbc:mysql://localhost:3306/stock";// ���������ַ���
		String username = "stock";// �û���
		String password = "stock";// ����
		Connection conn = null; // �������ݿ����Ӷ���
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
		String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_stockwin?useUnicode=true&amp;characterEncoding=GBK&amp;rewriteBatchedStatements=true&amp;useOldAliasMetadataBehavior=true";// ���������ַ���
		String username = "x1nly3jx04";//�û���
		String password = "ylm1y0hmjwh12h2ki12lmyz4wj2mx1zxkxy5wjkx";//����
		Connection conn = null; //�������ݿ����Ӷ���
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
			conn = JdbcConnect.getStockConn(); // ͳһ����
//			conn = JdbcConnect.getSAEConn(); // ͳһ����
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
