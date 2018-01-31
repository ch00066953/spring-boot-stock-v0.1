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
	
	static String sDate = "2013-02-08,五";
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
					System.out.println("开始~每次加的倍数"+c);
					System.out.println("开始~总共金额:"+sum+"加仓金额:"+sAddvalue+"卖出金额:"+sEndvalue+" 开始时间"+ w);
					list.add(sum);
				}else {
					if (g > sEndvalue){ 
						sBackvalue = g *hand;
						System.out.println("——————————————————————————————————————————————————————————————————————————");
						System.out.println("当前金额："+g);
						System.out.println("结束~加仓次数:"+add+" 总共金额:"+sum+"加仓金额:"+sAddvalue+"卖出金额:"+sEndvalue+"回收金额:"+sBackvalue+"结束时间"+w);
						System.out.println("获利比例:"+(sBackvalue-sum)/sum * 100 + "% 获利金额:" +(sBackvalue-sum) * 100);
						System.out.println("最高花费金额："+sum*100+"元");
						System.out.println("花费时间："+i/20+"月");
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
						System.out.println("倍数："+stock.Opening(add));
						System.out.println("当前金额："+g);
						System.out.println("加仓次数:"+add+"总共金额:"+sum+"加仓金额:"+sAddvalue+"卖出金额:"+sEndvalue+"回收金额:"+sBackvalue+"加仓时间"+w);

					}
				}
				
				i++;
				System.out.println("次数"+i+" 当前金额"+g+"总共金额:"+sum);
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
		System.out.println("——————————————————————————————————————————————————————————————————————————");
		System.out.println("当前金额："+g);
		System.out.println("timeout~加仓次数:"+add+" 总共金额:"+sum+"加仓金额:"+sAddvalue+"卖出金额:"+sEndvalue+"回收金额:"+sBackvalue+"结束时间"+w);
		System.out.println("获利比例:"+(sBackvalue-sum)/sum * 100 + "% 获利金额:" +(sBackvalue-sum) * 100);
		System.out.println("最高花费金额："+sum*100+"元");
		System.out.println("花费时间："+i/20+"月");
		System.out.println(list);
	}
	
	
}
