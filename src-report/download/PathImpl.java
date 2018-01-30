package download;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tool.rep.Replace;

public class PathImpl implements Path {

	private String url;
	private String day;
	private String main;
	private Map<String,String> docMap = new HashMap<String,String>();
	public Map<String,String> reMap = new HashMap<String,String>();
	
	public String getUrl() {
		return url;
	}

	/**
	 * 转换后的URL
	 * @return
	 */
	public String getU() {
		return Replace.replace(url, reMap);
	}
	
	public String getDay() {
		return day;
	}

	/**
	 * 转换后的DAY
	 * @return
	 */
	public String getD() {
		return Replace.replace(day, reMap);
	}
	
	public String getMain() {
		return main;
	}

	/**
	 * 转换后的xml参数
	 * @return
	 */
	public String get(String para) {
		return Replace.replace(docMap.get(para), reMap);
	}
	/**
	 * 转换后的MAIN
	 * @return
	 */
	public String getM() {
		return Replace.replace(main, reMap);
	}
	
	public Map<String, String> getDocMap() {
		return docMap;
	}
	
	public void setReMap(String stock) {
		reMap.put("stock", stock);
	}

	public void setReMap(String p1,String p2){
		reMap.put(p1, p2);
	}
	
	public void setReMap(Map m){
		reMap = m;
	}
	
	public PathImpl(Elements dPath) {
		Elements ePath = dPath.select("path > *");
		for(Element e : ePath){
			docMap.put(e.tagName(), e.text());
		}
		init();
		
	}
	
	private void init() {
		url = docMap.get("url");
		day = docMap.get("day");
		main = docMap.get("main");
	}
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir")); ;
		File in = new File(System.getProperty("user.dir")+"\\WebContent\\WEB-INF\\etc\\dlconfig.xml");
		Document doc = Jsoup.parse(in, "UTF-8");
		
		PathImpl p = new PathImpl(doc.select("#mainreport"));
		System.out.println(p.getDocMap());
		System.out.println(p.getUrl());
		System.out.println(p.getDay());
		System.out.println(p.getMain());
		
	}
}
