package tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatchUrl {
	/**
	 * 截取页面
	 * 
	 * @param code
	 * @return
	 */
	public static String CatchURL(String strURL, String... scope) {
		URL url = null;
		String scopeType = "BEF";//  BEF AFT ALL
		
		InputStreamReader input = null;
		HttpURLConnection httpConn = null;
		StringBuilder contentBuf = new StringBuilder();
		BufferedReader bufReader = null;
		String line = "";
		System.out.println("截取页面:" + strURL);
		try {
			url = new URL(strURL);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			input = new InputStreamReader(httpConn.getInputStream(), "GBK");
			bufReader = new BufferedReader(input);
			System.out.println("正在读入数据...");
			if (scope.length == 0)
				scopeType = "ALL";
			else if (StringX.isEmpty(scope[1])&& StringX.isEmpty(scope[0]))
				scopeType = "ALL";
			else if (StringX.isEmpty(scope[0])&& scope.length == 2)
				scopeType = "AFT";
			
			Labela : while ((line = bufReader.readLine()) != null) {
				if ("BEF".equals(scopeType) && !line.trim().contains(scope[0]) ){
					continue Labela;
				}else if ("BEF".equals(scopeType))
					scopeType = "AFT";
				contentBuf.append(line);
				// System.out.println(line);
				if ("AFT".equals(scopeType) && line.trim().contains(scope[1]))
					break Labela;
			}
			System.out.println("数据读取完成...");
			input.close();
			bufReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("截取页面失败！！！");
			return "0";
		}
		System.out.println("截取页面完成");
		strURL = null;
		url = null;
		httpConn = null;
		return contentBuf.toString();
	}
	
	public static void main(String[] args) {
		String t = CatchURL("http://doctor.10jqka.com.cn/000002/", "基本面诊股</a>","<strong>历史波段诊断</strong>");
		System.out.println(t);
		String a = CatchURL("http://doctor.10jqka.com.cn/000002/",null,null);
		System.out.println(a);
		String b = CatchURL("http://doctor.10jqka.com.cn/000002/",null,"<strong>历史波段诊断</strong>");
		System.out.println(b);
	}
}
