package wlgtext.jsoup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import download.Path;
import tool.DateUtils;
import tool.Encoding;
import tool.StringX;
import tool.fileAnalysis.bean.TableBean;

/**
 * Html table 读取器
 * @author lgwang
 *
 */
public class HtmlTableBReader extends TableBean{

	private int dateRow = 1;
	Elements eHead ; //<thead> <tr> <th>
	Elements eRowHead ;//<tbody> <tr> <th>
	Elements ebody ;//<tbody>
	Elements tableE ;//<tbody>div
	
	public HtmlTableBReader(String url,String id,String ...para) throws IOException {
		readHtmlTable(url,id,para);
		readHead();
		readTableVuale();
	}
	
	public HtmlTableBReader(Path p,String id,String ...para) throws IOException {
		readHtmlTable(p.getM(),id,para);
		readHead();
		readTableVuale();
	}
	
	/**
	 * <thead> <tr> <th>
	 * <tbody> <tr> <th> <td>
	 * @param doc2
	 * @throws IOException 
	 */
	private void readHtmlTable(String url,String id,String ...para) throws IOException {
		Document doc;
		if(url.startsWith("http"))
			doc = Jsoup.connect(url).get();
		else{
			File f = new File(url);
			doc = Jsoup.parse(f, new Encoding().guessFileEncoding(f), "");
		}
		String select = "";
		if(!StringX.isEmpty(id))
			select += "#"+id + " ";
		for(String p : para )
			select += p + " ";
		tableE = doc.select(select);
	}
	
	/**
	 * 获取表头
	 */
	private void readHead() {
		int i = 0;
		eHead = tableE.select("thead").select("th");
		List<String> tdList = new LinkedList<String>();
		for (Element eh : eHead){
//			colNo.put(eh.text(), i++);
//			tdList.add(eh.text());
			addHead(eh.text());
			add(eh.text());
		}
//		tableList.add(tdList);
//		System.out.println(colNo);
	}
	
	/**
	 * 获取表的所有内容存入List
	 */
	private void readTableVuale() {
		ebody = tableE.select("tbody");
		Elements eRow = ebody.select("tr");
//		String body = doc.select("tbody").toString();
//		System.out.println(body);
		for (Element er : eRow){
			er.select("*[style*=none;]").remove();
			Elements tds = er.select("th,td");
			for (Element td : tds){
				add(td.text().replace(" ", "").replace(",", " "));
			}
		}
//		System.out.println(tableList.toString());
	}
	
	/**
	 * 直接去HTML文本的值
	 * @param rNo
	 * @param cNo
	 * @return
	 */
	public String getHtmlRowCol(int rNo,int cNo) {
		Elements e = ebody.select("tr:eq("+rNo+")").select("td:eq("+cNo+")");
//		System.out.println(e);
		return e.text();
	}
	
	
	/**
	 * 直接去HTML文本的值
	 * @param rNo
	 * @param cNo
	 * @return
	 */
	public String getRowCol(String rName,String cName) {
		int r,c;
		c = colno.get(cName);
		r = rowno.get(rName);
		return getString(r,c);
	}
	
	/**
	 * 根据行列名取值
	 * @param rName
	 * @param cName
	 * @return
	 */
	public String getRCByDate(String rName,String cName) {
		int r,c;
		c = colno.get(cName);
		r = getDateRow(rName);
		return getString(r,c);
	}
	
	/**
	 * 从list里面取值
	 * @param r
	 * @param c
	 * @return
	 */
	public String getString(int r,int c){
		return tabList.get(r).get(c);
	}
	
	/**
	 * 查询匹配的日期
	 * @param date
	 * @return
	 */
	private String getDate(String date) {
		DateTime d1 = new DateTime(DateUtils.parseDate(date));  
		DateTime d2 ;  
		for(;dateRow < rowhead.size();dateRow++){
			Date sortDate = DateUtils.parseDate(rowhead.get(dateRow));
			d2 = new DateTime(sortDate);
			
			if(d2.isBefore(d1) || d2.isEqual(d1)){
				return rowhead.get(dateRow);
			}
		}
		return rowhead.get(dateRow);
	}
	
	/**
	 * 匹配日期的行号
	 * @param date
	 * @return
	 */
	private int getDateRow(String date) {
		return rowno.get(getDate(date));
	}
	public static void main(String[] args) throws IOException {
		String url = "http://basic.10jqka.com.cn/600887/equity.html#astockchange";
		String id = "astockchange";
		HtmlTableBReader htr = new HtmlTableBReader(url,id);
		System.out.println("======================");
		System.out.println("======================");
		System.out.println(htr.getDate("2015/5/11"));
		System.out.println(htr.getRCByDate("2015/5/11","变动后A股总股本(股)"));
		System.out.println(htr.tabList);
//		htr.getRow();
//		htr.getRowCol(1,2);
	}
}
