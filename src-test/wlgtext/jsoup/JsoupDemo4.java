package wlgtext.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author lgwang
 *Selector选择器概述
tagname: 通过标签查找元素，比如：a
ns|tag: 通过标签在命名空间查找元素，比如：可以用 fb|name 语法来查找 <fb:name> 元素
#id: 通过ID查找元素，比如：#logo
.class: 通过class名称查找元素，比如：.masthead
[attribute]: 利用属性查找元素，比如：[href]
[^attr]: 利用属性名前缀来查找元素，比如：可以用[^data-] 来查找带有HTML5 Dataset属性的元素
[attr=value]: 利用属性值来查找元素，比如：[width=500]
[attr^=value], [attr$=value], [attr*=value]: 利用匹配属性值开头、结尾或包含属性值来查找元素，比如：[href*=/path/]
[attr~=regex]: 利用属性值匹配正则表达式来查找元素，比如： img[src~=(?i)\.(png|jpe?g)]
*: 这个符号将匹配所有元素
 */
public class JsoupDemo4 {
	static String url="http://basic.10jqka.com.cn";
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        // TODO Auto-generated method stub
        BolgBody();
        //test();
        //Blog();
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
     * @throws IOException
     * http://www.open-open.com/jsoup/selector-syntax.htm
     */
    private static void BolgBody() throws IOException {
        String subUrl = null;
        String title = null;
        File f = new File(url);
        // 从 URL 直接加载 HTML 文档
        Document doc2 = Jsoup.parse(f, "");
        //String title = doc2.getElementById("sh").toString(); 
        Elements select = doc2.select("a[href^=/ths/");
        for(Element s:select){
//        	System.out.println(s.attr("href"));
//        	System.out.println(s.attr("title"));
        	subUrl = s.attr("abs:href");
        	title = s.attr("title");
        	Document doc = Jsoup.connect(subUrl).get();
        	Elements es = doc.select("div.c_content.clearfix > a");
        	for (Element e:es){
        		System.out.print(e.attr("href"));
        		System.out.println(e.text());
        	}
        	
        }
    }

    /**
     * 获取博客上的文章标题和链接
     */
    public static void article() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postTitle");
            for (Element element :ListDiv) {
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
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postBody");
            for (Element element :ListDiv) {
                System.out.println(element.html());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
