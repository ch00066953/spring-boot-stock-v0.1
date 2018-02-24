package moving.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import connect.JdbcConnect;

import moving.MovingObject;
import parameter.Para;
import tool.SqlTool;

public class RatingMoving {

	private MovingObject mo;
	private List<String> stock;

	public RatingMoving(MovingObject mo) {
		this.mo = mo;
	}

	public void excuse() {
		movingH();
		movingL();
		movingHM();
		movingLM();
	}

	/**
	 * 涓婄Щ鑷抽珮,澶т簬
	 */
	public void movingH() {
		String move = "H";
		String Sql = "select stockno from nx_rating_info nr " + "where nr."
				+ mo.name + " > '" + mo.high + "' and nr.Date = '" + Para.getDate()
				+ "' " + "and exists(select * from nx_rating_info nr1 "
				+ "where nr1." + mo.name + " <= '" + mo.high
				+ "' and nr1.Date = '" + Para.getNearDate()
				+ "' and nr.stockno =nr1.stockno)";

		System.out.println(Sql);
		moving(Sql, move);
	}

	/**
	 * 涓嬬Щ鑷充綆锛屽皬浜�
	 */
	public void movingL() {
		String move = "L";
		String Sql = "select stockno from nx_rating_info nr " + "where nr."
				+ mo.name + " < '" + mo.low + "' and nr.Date = '" + Para.getDate()
				+ "'" + "and exists(select * from nx_rating_info nr1 "
				+ "where nr1." + mo.name + " >= '" + mo.low
				+ "' and nr1.Date = '" + Para.getNearDate()
				+ "' and nr.stockno =nr1.stockno)";

		System.out.println(Sql);
		moving(Sql, move);
	}

	/**
	 * 楂樿嚦涓� 锛岄棴鍖�
	 */
	public void movingHM() {
		String move = "HM";
		String Sql = "select stockno from nx_rating_info nr " + "where nr."
				+ mo.name + " between '" + mo.low + "' and '" + mo.high
				+ "' and nr.Date = '" + Para.getDate() + "'"
				+ "and exists(select * from nx_rating_info nr1 " + "where nr1."
				+ mo.name + " > '" + mo.high + "' and nr1.Date = '"
				+ Para.getNearDate() + "' and nr.stockno =nr1.stockno)";

		System.out.println(Sql);
		moving(Sql, move);
	}

	/**
	 * 浣庤嚦涓紝闂寘
	 */
	public void movingLM() {
		String move = "LM";
		String Sql = "select stockno from nx_rating_info nr " + "where nr."
				+ mo.name + " between '" + mo.low + "' and '" + mo.high
				+ "' and nr.Date = '" + Para.getDate() + "' "
				+ "and exists(select * from nx_rating_info nr1 " + "where nr1."
				+ mo.name + " < '" + mo.low + "' and nr1.Date = '"
				+ Para.getNearDate() + "' and nr.stockno =nr1.stockno)";
		System.out.println(Sql);
		moving(Sql, move);
	}

	/**
	 * 璇勭骇璇ヨ〃鏍囧織
	 * 
	 * @param sql
	 * @param move
	 */
	private void moving(String sql, String move) {
		try {
			stock = SqlTool.getSingleList(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String updateSql = "update nx_rating_info set C" + mo.name + " = '"
				+ move + "' where stockno = ? and Date = '" + Para.getDate() + "'";
		int i = 0;

		System.out.println("杞Щ鏍囧織锛�" + move);
		System.out.println(updateSql);
		Connection conn = null ;
		PreparedStatement pstmt = null; // 寤虹珛涓�涓煡璇㈠璞�
		try {
			conn = JdbcConnect.getConn();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(updateSql);
			for (String stockno : stock) {
				pstmt.setString(1, stockno);
				pstmt.addBatch();
				i++;
				// System.out.println(i);
			}
			System.out.println("鎵ц鎻掑叆鏉℃暟锛�" + i);
			pstmt.executeBatch();
			System.out.println("杞Щ鏉℃暟锛�" + i);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnect.close(conn, pstmt);
		}
	}
}
