package wlgtext.jsoup;

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
public class HtmlTableReader {

	public Map<String, Integer> colNo = new LinkedHashMap<String, Integer>(); //行号映射
	public Map<String, Integer> rowNo = new LinkedHashMap<String, Integer>();//列号映射
	public Map<Integer,String> rowSort = new LinkedHashMap<Integer,String>(); //列号排序映射
	public List<List<String>> tableList = new LinkedList<List<String>>(); //table内容List封装
	Elements eHead ; //<thead> <tr> <th>
	Elements eRowHead ;//<tbody> <tr> <th>
	Elements ebody ;//<tbody>
	Elements tableE ;//<tbody>div
	
	public HtmlTableReader(String url,String id) throws IOException {
		readHtmlTable(url,id);
		readHead();
		readBody();
		readTableVuale();
	}
	/**
	 * <thead> <tr> <th>
	 * <tbody> <tr> <th> <td>
	 * @param doc2
	 * @throws IOException 
	 */
	private void readHtmlTable(String url,String id) throws IOException {
		Document doc = Jsoup.connect(url).get();
		tableE = doc.select("#"+id);
	}
	
	/**
	 * 获取表头
	 */
	private void readHead() {
		int i = 0;
		eHead = tableE.select("thead").select("th");
		List<String> tdList = new LinkedList<String>();
		for (Element eh : eHead){
			colNo.put(eh.text(), i++);
			tdList.add(eh.text());
		}
		tableList.add(tdList);
//		System.out.println(colNo);
	}
	
	/**
	 * 获取表体头
	 */
	private void readBody() {
		int i = 1;
		ebody = tableE.select("tbody");
//		eRowHead = ebody.select("th");
		eRowHead = ebody.select("td.tc.f12");
//		String body = doc.select("tbody").toString();
//		System.out.println(body);
		for (Element eh : eRowHead){
			rowNo.put(eh.text(), i);
			rowSort.put(i++,eh.text() );
		}
//		System.out.println(rowNo);
	}
	
	/**
	 * 获取表的所有内容存入List
	 */
	private void readTableVuale() {
		int i = 1;
		ebody = tableE.select("tbody");
		Elements eRow = ebody.select("tr");
//		String body = doc.select("tbody").toString();
//		System.out.println(body);
		for (Element er : eRow){
			Elements tds = er.select("td");
			List<String> tdList = new LinkedList<String>();
//			tdList.add(er.select("td.tc.f12").first().text());
			int j = 1;
			for (Element td : tds){
				tdList.add(td.text());
				j++;
			}
			tableList.add(tdList);
			i++;
		}
//		System.out.println(tableList.toString());
	}
	
	private void getRow() {
		Elements e = ebody.select("tr:eq(1)");
//		System.out.println("=========================");
//		System.out.println(e);
		// TODO Auto-generated method stub
	}
	
	/**
	 * 直接去HTML文本的值
	 * @param rNo
	 * @param cNo
	 * @return
	 */
	public String getRowCol(int rNo,int cNo) {
		Elements e = ebody.select("tr:eq("+rNo+")").select("td:eq("+cNo+")");
//		System.out.println(e);
		return e.text();
	}
	
	/**
	 * 根据行列名取值
	 * @param rName
	 * @param cName
	 * @return
	 */
	public String getRCByDate(String rName,String cName) {
		int r,c;
		c = colNo.get(cName);
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
		return tableList.get(r).get(c);
	}
	
	/**
	 * 查询匹配的日期
	 * @param date
	 * @return
	 */
	private String getDate(String date) {
		int iRow = 1;
		DateTime d1 = new DateTime(DateUtils.parseDate(date));  
		DateTime d2 ;  
		for(;iRow <= rowSort.size();iRow++){
			Date sortDate = DateUtils.parseDate(rowSort.get(iRow));
			d2 = new DateTime(sortDate);
			
			if(d2.isBefore(d1) || d2.isEqual(d1)){
				return rowSort.get(iRow);
			}
		}
		return rowSort.get(iRow);
	}
	
	/**
	 * 匹配日期的行号
	 * @param date
	 * @return
	 */
	private int getDateRow(String date) {
		return rowNo.get(getDate(date));
	}
	public static void main(String[] args) throws IOException {
		String url = "http://basic.10jqka.com.cn/600887/equity.html#astockchange";
		String id = "astockchange";
		HtmlTableReader htr = new HtmlTableReader(url,id);
		htr.readHead();
		System.out.println("======================");
		htr.readBody();
		System.out.println("======================");
		htr.readTableVuale();
		System.out.println(htr.getDate("2015/5/11"));
		System.out.println(htr.getRCByDate("2015/5/11","变动后A股总股本(万股)"));
		System.out.println(htr.tableList);
//		htr.getRow();
//		htr.getRowCol(1,2);
	}
}
