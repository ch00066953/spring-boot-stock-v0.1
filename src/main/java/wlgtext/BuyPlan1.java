package wlgtext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyPlan1 {

	public void daynext() {
		
	}
	
	public double buypoint(double sBeginvalue){
		return sBeginvalue * (1-dDown);
	}
	
	public double sellpoint(double sBeginvalue){
		
		return sBeginvalue * (1+dUP);
	}
	
	public int Opening(int add){
	//	return (int) Math.pow(c, add);
		return  add;
	//	return 1;
	}
	static int c = 1;
	
	static String sDate = "2013-02-08,浜�";
	static String sTable = "wlg1";
	static double dUP = 0.3;
	static double dDown = 0.1;
	
	public static void main(String[] args) throws SQLException {
		
		String sql = "select w,l,g from "+ sTable+" where w >= '"+ sDate +"' order by w";
		
		String sBegindate = sDate;
		double sSecondvalue = 0;
		double sBeginvalue = 0;
		double sAddvalue = 0;
		double sBackvalue = 0;
		String sEnddate;
		double sEndvalue = 0;
		double sum = 0;
		List<Double> list = new ArrayList<Double>();
		
		String w = "kong";
		double l;
		double g = 0;
		
		int add = 0;
		int i = 0 ;
		int hand = 1;
		
		Connection conn = JdbcExample.getConn();
		PreparedStatement psSql = null;
		
		BuyPlan1 stock = new BuyPlan1();
		try {
			psSql = conn.prepareStatement(sql);
			ResultSet query = psSql.executeQuery();
			
			while(query.next()){
				w = query.getString("w");
				l = query.getDouble("l");
				g = query.getDouble("g");
				
				if(i==0){
					sum = sSecondvalue = sBeginvalue = g;
					sAddvalue = stock.buypoint(g);
					sEndvalue = stock.sellpoint(g);
					System.out.println("寮�濮媬姣忔鍔犵殑鍊嶆暟"+c);
					System.out.println("寮�濮媬鎬诲叡閲戦:"+sum+"鍔犱粨閲戦:"+sAddvalue+"鍗栧嚭閲戦:"+sEndvalue+" 寮�濮嬫椂闂�"+ w);
					list.add(sum);
				}else {
					if (g > sEndvalue){ 
						sBackvalue = g *hand;
						System.out.println("鈥斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺��");
						System.out.println("褰撳墠閲戦锛�"+g);
						System.out.println("缁撴潫~鍔犱粨娆℃暟:"+add+" 鎬诲叡閲戦:"+sum+"鍔犱粨閲戦:"+sAddvalue+"鍗栧嚭閲戦:"+sEndvalue+"鍥炴敹閲戦:"+sBackvalue+"缁撴潫鏃堕棿"+w);
						System.out.println("鑾峰埄姣斾緥:"+(sBackvalue-sum)/sum * 100 + "% 鑾峰埄閲戦:" +(sBackvalue-sum) * 100);
						System.out.println("鏈�楂樿姳璐归噾棰濓細"+sum*100+"鍏�");
						System.out.println("鑺辫垂鏃堕棿锛�"+i/20+"鏈�");
						System.out.println(list);
						return ;
						}
					if (g < sAddvalue){
						add ++;
						sAddvalue = stock.buypoint(g);
						sEndvalue = stock.sellpoint(g);
						sum += g * stock.Opening(add);
						list.add(g * stock.Opening(add));
						hand += stock.Opening(add);
						sBackvalue =  g *hand*1.20;
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+g);
						System.out.println("鍊嶆暟锛�"+stock.Opening(add));
						System.out.println("褰撳墠閲戦锛�"+g);
						System.out.println("鍔犱粨娆℃暟:"+add+"鎬诲叡閲戦:"+sum+"鍔犱粨閲戦:"+sAddvalue+"鍗栧嚭閲戦:"+sEndvalue+"鍥炴敹閲戦:"+sBackvalue+"鍔犱粨鏃堕棿"+w);

					}
				}
				
				i++;
				System.out.println("娆℃暟"+i+" 褰撳墠閲戦"+g+"鎬诲叡閲戦:"+sum);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(psSql != null)
			psSql.close();
			if(conn != null)
			conn.close();
		}
		sBackvalue = g *hand;
		System.out.println("鈥斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺��");
		System.out.println("褰撳墠閲戦锛�"+g);
		System.out.println("timeout~鍔犱粨娆℃暟:"+add+" 鎬诲叡閲戦:"+sum+"鍔犱粨閲戦:"+sAddvalue+"鍗栧嚭閲戦:"+sEndvalue+"鍥炴敹閲戦:"+sBackvalue+"缁撴潫鏃堕棿"+w);
		System.out.println("鑾峰埄姣斾緥:"+(sBackvalue-sum)/sum * 100 + "% 鑾峰埄閲戦:" +(sBackvalue-sum) * 100);
		System.out.println("鏈�楂樿姳璐归噾棰濓細"+sum*100+"鍏�");
		System.out.println("鑺辫垂鏃堕棿锛�"+i/20+"鏈�");
		System.out.println(list);
	}
	
	
}
