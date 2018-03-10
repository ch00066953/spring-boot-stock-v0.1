package download;

import java.util.Map;

public interface Path {

	/**
	 * 下载地址
	 * @return
	 */
	public String getUrl();
	public String getU();
	/**
	 * 本地每日路径
	 * @return
	 */
	public String getDay();
	public String getD();
	/**
	 * 主路径
	 * @return
	 */
	public String getMain();
	public String getM();
	
	public String get(String para);
	public String getDoc(String para);
	
	public void setReMap(String stock);
	public void setReMap(Map m);
	public void setReMap(String p1,String p2);
	
	public boolean isDownLoanToday() ;
	public boolean isDownLoan() ;
	public boolean isEffective() ;
}
