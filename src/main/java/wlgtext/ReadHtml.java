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
     * 鏍规嵁鎻愪緵鐨刄RL锛岃幏鍙栨URL瀵瑰簲缃戦〉鐨勭函鏂囨湰淇℃伅 
     * @param url 鎻愪緵鐨刄RL閾炬帴 
     * @return RL瀵瑰簲缃戦〉鐨勭函鏂囨湰淇℃伅 
     * @throws ParserException 
     */  
    public static String getText(String url)throws ParserException{  
        StringBean sb = new StringBean();  
          
        //璁剧疆涓嶉渶瑕佸緱鍒伴〉闈㈡墍鍖呭惈鐨勯摼鎺ヤ俊鎭�  
        sb.setLinks(false);  
        //璁剧疆灏嗕笉闂存柇绌烘牸鐢辨瑙勭┖鏍兼墍鏇夸唬  
        sb.setReplaceNonBreakingSpaces(true);  
        //璁剧疆灏嗕竴搴忓垪绌烘牸鐢变竴涓崟涓�绌烘牸鎵�浠ｆ浛  
        sb.setCollapse(true);  
        //浼犲叆瑕佽В鏋愮殑URL  
        sb.setURL(url);  
        //杩斿洖瑙ｆ瀽鍚庣殑缃戦〉绾枃鏈俊鎭�  
        return sb.getStrings();  
    }  
}
