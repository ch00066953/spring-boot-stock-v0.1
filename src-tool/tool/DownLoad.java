package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;

import tool.fileAnalysis.HttpUtil;
import tool.rep.Replace;

import download.Path;
import download.PathMap;

/**
 * 下载
 * @author lgwang
 *
 */
public class DownLoad {
	
	public static void downLoanPath(String id,Map<String, String> map) throws IOException {

		PathMap pm = new PathMap();
		Path p = pm.getPath(id);
		p.setReMap(map);
		if(new File(p.getD()).exists()&&new File(p.getM()).exists()){
			System.out.println("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());

	}
	
	public static void downLoanPath(Path p,Map<String, String> map) throws IOException {
		
		p.setReMap(map);
		if(new File(p.getD()).exists()&&new File(p.getM()).exists()){
			System.out.println("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	
	public static void downLoanPath(String id,String stock) throws IOException {
		
		PathMap pm = new PathMap();
		Path p = pm.getPath(id);
		p.setReMap(stock);
		if(new File(p.getD()).exists()&&new File(p.getM()).exists()){
			System.out.println("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	
	public static void downLoanPath(Path p,String stock) throws IOException {
		
		p.setReMap(stock);
		if(new File(p.getD()).exists()&&new File(p.getM()).exists()){
			System.out.println("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	
	public static void downLoanMPath(Path p,String stock) throws IOException {
		
		p.setReMap(stock);
		File f = new File(p.getM());
		if(f.exists() || f.length() > 0){
			System.out.println("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	/**
	 * 根据URL下载
	 * @param ourputFile
	 * @param urlStr
	 * @throws Exception
	 */
	public static void getURLResource(String ourputFile, String urlStr)
			throws Exception {

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
	
	/**
	 * 替换{$**}
	 * @param src
	 * @param dsc
	 * @param per
	 * @return
	 */
	public static String rep(String src,String dsc,String per){
		String rep = "{$"+per+"}";
		String url = src.replace(rep, dsc);
		return url;
	}
	
	/**
	 * 创建路径
	 * @param path
	 */
	public static void createPath(String path) {
		// TODO Auto-generated method stub
		File file =new File(path);    
		//如果文件夹不存在则创建    
		if  (!file .isDirectory())      
		{       
		    System.out.println("//不存在");  
		    file.mkdirs();    
		    System.out.println("//创建+"+file);
		} else   
		{  
		    System.out.println("//目录存在");  
		}  
	}
	
	public static void delete(Path p,Map<String, String> map) {
		p.setReMap(map);
		if(new File(p.getD()).exists()){
			System.out.println("exists");
			DeleteFileUtil.deleteFile(p.getD());
		}
		if(new File(p.getM()).exists()){
			System.out.println("exists");
			DeleteFileUtil.deleteFile(p.getM());
		}

	}
	public static void main(String[] args) {
		tool.DownLoad.createPath("E:\\stock\\20150115\\MAINREPORT");
	}
}
