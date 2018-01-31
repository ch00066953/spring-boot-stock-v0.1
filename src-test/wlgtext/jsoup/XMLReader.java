package wlgtext.jsoup;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tool.DateUtils;

/**
 * Html table 读取器
 * @author lgwang
 *
 */
public class XMLReader {

	Document xml;
	
	public XMLReader(String path) throws IOException {
		File in = new File(path);
		xml = Jsoup.parse(in, "UTF-8");
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir")); ;
		File in = new File(System.getProperty("user.dir")+"\\WebContent\\WEB-INF\\etc\\dlconfig.xml");
		Document doc = Jsoup.parse(in, "UTF-8");
		System.out.println(doc);
		System.out.println("=====================");
		System.out.println(doc.select("#mainreport").select("remark").toString());
		
//		htr.getRow();
//		htr.getRowCol(1,2);
	}
}
