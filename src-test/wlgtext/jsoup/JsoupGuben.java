package wlgtext.jsoup;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author lgwang
 * 股本获取
 * 
 */
public class JsoupGuben {
	static String url = "http://basic.10jqka.com.cn/600031/equity.html#astockchange";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub
		BolgBody();
		// test();
		// Blog();
		/*
		 * Document doc = Jsoup.connect("http://www.oschina.net/")
		 * .data("query", "Java") // 请求参数 .userAgent("I ’ m jsoup") // 设置
		 * User-Agent .cookie("auth", "token") // 设置 cookie .timeout(3000) //
		 * 设置连接超时时间 .post();
		 */// 使用 POST 方法访问 URL

		/*
		 * // 从文件中加载 HTML 文档 File input = new File("D:/test.html"); Document doc
		 * = Jsoup.parse(input,"UTF-8","http://www.oschina.net/");
		 */
	}

	/**
	 * 获取指定HTML 文档指定的body
	 * 
	 * @throws IOException
	 *             http://www.open-open.com/jsoup/selector-syntax.htm
	 */
	private static void BolgBody() throws IOException {
		String astockchange = null;
		String title = null;
		// 从 URL 直接加载 HTML 文档
		Document doc2 = Jsoup.connect(url).get();
		// String title = doc2.getElementById("sh").toString();
		astockchange = doc2.select("#astockchange").toString();
//		System.out.println(astockchange);
		readbody(doc2);
	}

	/**
	 * <thead> <tr> <th>
	 * <tbody> <tr> <th> <td>
	 * @param doc2
	 */
	private void readHtmlTable(Document doc) {
		String head = doc.select("thead").toString();
		System.out.println(head);
	}
	
	private static void readbody(Document doc) {
		String body = doc.select("tbody").toString();
		System.out.println(body);

	}
	/**
	 * 获取博客上的文章标题和链接
	 */
	public static void article() {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
			Elements ListDiv = doc.getElementsByAttributeValue(	"class",
																"postTitle");
			for (Element element : ListDiv) {
				Elements links = element.getElementsByTag("a");
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text().trim();
					System.out.println(linkHref);
					System.out.println(linkText);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取指定博客文章的内容
	 */
	public static void Blog() {
		Document doc;
		try {
			doc = Jsoup
					.connect(	"http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html")
					.get();
			Elements ListDiv = doc.getElementsByAttributeValue(	"class",
																"postBody");
			for (Element element : ListDiv) {
				System.out.println(element.html());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
