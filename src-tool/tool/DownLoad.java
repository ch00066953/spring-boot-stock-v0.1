package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;

import com.ch.service.webcrawler.HttpUtil;

import download.Path;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 下载
 * @author lgwang
 *
 */
@Slf4j
public class DownLoad {
	
	private static boolean IsEmpty(String pathname) {
		File f = new File(pathname);
		if(f.exists()){
			if(f.length()>0)
				return true;
			else
				DeleteFileUtil.deleteFile(pathname);
		}
		
		return false;

	}
	
	public static void downLoanPath(String id,Map<String, String> map) throws IOException {

		PathMap pm = new PathMap();
		Path p = pm.getPath(id);
		p.setReMap(map);
		if(IsEmpty(p.getD())&&IsEmpty(p.getM())){
			log.info("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());

	}
	
	public static void downLoanPath(Path p,Map<String, String> map) throws IOException {
		
		p.setReMap(map);
		if(IsEmpty(p.getD())&&IsEmpty(p.getM())){
			log.info("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	
	public static void downLoanPath(Path p) throws IOException {
		
		if(IsEmpty(p.getD())&&IsEmpty(p.getM())){
			log.info("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	
	public static void downLoanPath(String id,String stock) throws IOException {
		
		PathMap pm = new PathMap();
		Path p = pm.getPath(id);
		p.setReMap(stock);
		if(IsEmpty(p.getD())&&IsEmpty(p.getM())){
			log.info("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	
	public static void downLoanPathByHead(String id,Map<String,String> headerMap,String stock) throws IOException {
		
		PathMap pm = new PathMap();
		Path p = pm.getPath(id);
		p.setReMap(stock);
		if(IsEmpty(p.getD())&&IsEmpty(p.getM())){
			log.info("exists");
			return;
		}
		HttpUtil.downloadFile2(p.getU(),headerMap,p.getM(),p.getD());
		
	}
	
	public static void downLoanPath(Path p,String stock) throws IOException {
		
		p.setReMap(stock);
		if(IsEmpty(p.getD())&&IsEmpty(p.getM())){
			log.info("exists");
			return;
		}
		HttpUtil.downloadFile(p.getU(),p.getM(),p.getD());
		
	}
	
	public static void downLoanMPath(Path p,String stock) throws IOException {
		
		p.setReMap(stock);
		File f = new File(p.getM());
		if(f.exists() || f.length() > 0){
			log.info("exists");
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
		    log.info("//不存在");  
		    file.mkdirs();    
		    log.info("//创建+"+file);
		} else   
		{  
		    log.info("//目录存在");  
		}  
	}
	
	public static void delete(Path p,Map<String, String> map) {
		p.setReMap(map);
		if(IsEmpty(p.getD())){
			log.info("exists");
			DeleteFileUtil.deleteFile(p.getD());
		}
		if(IsEmpty(p.getM())){
			log.info("exists");
			DeleteFileUtil.deleteFile(p.getM());
		}

	}
	public static void main(String[] args) {
		
		log.info(String.valueOf(tool.DownLoad.IsEmpty("E:\\stock\\equity\\main\\000061_equity.html")));
	}
}
