package wlgtext;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class DownLoanTest {
	public static void getURLResource(String ourputFile,String urlStr) throws Exception {
		
		  FileWriter fw = new FileWriter(ourputFile);
		  fw.getEncoding();
		  PrintWriter pw = new PrintWriter(fw);
		  URL resourceUrl = new URL(urlStr);
		  
		  InputStream content = (InputStream) resourceUrl.getContent();
		  
		  BufferedReader in = new BufferedReader(new InputStreamReader(content));
		  String line;
		  while ((line = in.readLine()) != null) {
		   pw.println(line);
		  }
		  pw.close();
		  fw.close();
		 }

		 public static void main(String[] args) {
		  try {
		   System.out.println("begin");
		   getURLResource("f:\\000002.xls","http://basic.10jqka.com.cn/new/000002/xls/mainreport.xls");
		   System.out.println("end");
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
}
