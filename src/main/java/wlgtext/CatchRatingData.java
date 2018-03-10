package wlgtext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class CatchRatingData {
	private int ix;
	private String[] sRating = new String[6];
	
	public void CatchRating(String code) throws Exception {  
	    String strURL = "http://doctor.10jqka.com.cn/" + code + "/";  
	    System.out.println(strURL);  
	    URL url = new URL(strURL);  
	    
	    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  
	    InputStreamReader input = new InputStreamReader(httpConn  
	            .getInputStream(), "GBK");  
	    System.out.println("1"); 
	    BufferedReader bufReader = new BufferedReader(input);  
	    System.out.println("2"); 
	    String line = "";  
	    StringBuilder contentBuf = new StringBuilder();  
	    while ((line = bufReader.readLine()) != null) {  
	        contentBuf.append(line);  
	    }  
	    System.out.println("3"); 
	    String buf = contentBuf.toString();  
//	    int beginIx = buf.indexOf(strbegin,6000);  
//	    int endIx = buf.indexOf("分",beginIx);  
//	    int beginIx1 = buf.indexOf(strbegin1,endIx);
//	    int endIx1 = buf.indexOf("分",beginIx1);  
//	    System.out.println(buf);
//	    System.out.println(beginIx +","+ endIx +","+ strbegin.length());  
//	    String result = buf.substring(beginIx + strbegin.length(), endIx);  
//	    String result1 = buf.substring(beginIx1 + strbegin1.length(), endIx1);  
//	    System.out.println(strbegin+"\n" + result); 
//	    System.out.println("技术面：\n" + result1); 
	    CatchSYN(buf);
	    CatchTech(buf);
	    CatchFinan(buf);
	    CatchInfo(buf);
	    CatchTrade(buf);
	    CatchBase(buf);
	    PrintRating();
	    input.close();
	    bufReader.close();
	}  
	
	private String CatchSYN(String buf){
		String strbegin = "综合诊断：";
		int beginIx = buf.indexOf(strbegin,6000);  
	    int endIx = buf.indexOf("分",beginIx); 
	    String result = buf.substring(beginIx + strbegin.length(), endIx);  
	    ix = endIx;
	    System.out.println(strbegin+"\n" + result); 
	    sRating[0] = result;
		return result;
	}
	
	private String CatchTech(String buf){
//		String strBegin = "<div class=\"label\">";
//		String strEnd = "分";
//		int beginIx = buf.indexOf(strBegin,ix);  
//	    int endIx = buf.indexOf(strEnd,beginIx); 
//		String result = buf.substring(beginIx + strBegin.length(), endIx);
//		ix = endIx;
//		return result;
		System.out.println("技术面："); 
		String result = CatchOther(buf);
		sRating[1] = result;
		return result;
	}
	
	private String CatchFinan(String buf){
		System.out.println("资金面："); 
		String result = CatchOther(buf);
		sRating[2] = result;
		return result;
	}
	
	private String CatchInfo(String buf){
		System.out.println("消息面："); 
		String result = CatchOther(buf);
		sRating[3] = result;
		return result;
	}
	
	private String CatchTrade(String buf){
		System.out.println("行业面："); 
		String result = CatchOther(buf);
		sRating[4] = result;
		return result;
	}
	
	private String CatchBase(String buf){
		System.out.println("基础面："); 
		String result = CatchOther(buf);
		sRating[5] = result;
		return result;
	}
	
	private String CatchOther(String buf){
		String strBegin = "<div class=\"label\">";
		String strEnd = "分";
		int beginIx = buf.indexOf(strBegin,ix);  
	    int endIx = buf.indexOf(strEnd,beginIx); 
		String result = buf.substring(beginIx + strBegin.length(), endIx);
		ix = endIx;
		System.out.println(result); 
		return result;
	}
	
	private void PrintRating() {
		System.out.println(Arrays.toString(sRating));
	}
	
	public static void main(String[] args) {
		CatchRatingData crd = new CatchRatingData();
		try {
			crd.CatchRating("002415");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
