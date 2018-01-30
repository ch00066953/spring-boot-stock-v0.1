package wlgtext;

import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ReadHtml {

	public static void main(String[] args) throws ParserException {
		System.out.println(getText("http://basic.10jqka.com.cn/new/300080/equity.html"));
	}

	private static void filter(String url) throws ParserException {
		String currentURL = "http://basic.10jqka.com.cn/new/300080/equity.html";
		Parser parser = new Parser(currentURL);
		AndFilter filter = new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("id", "astockchange"));

		NodeList nodes = parser.parse(filter);

		System.out.println(nodes.elementAt(0).getChildren());
	}
	
	/** 
     * 根据提供的URL，获取此URL对应网页的纯文本信息 
     * @param url 提供的URL链接 
     * @return RL对应网页的纯文本信息 
     * @throws ParserException 
     */  
    public static String getText(String url)throws ParserException{  
        StringBean sb = new StringBean();  
          
        //设置不需要得到页面所包含的链接信息  
        sb.setLinks(false);  
        //设置将不间断空格由正规空格所替代  
        sb.setReplaceNonBreakingSpaces(true);  
        //设置将一序列空格由一个单一空格所代替  
        sb.setCollapse(true);  
        //传入要解析的URL  
        sb.setURL(url);  
        //返回解析后的网页纯文本信息  
        return sb.getStrings();  
    }  
}
