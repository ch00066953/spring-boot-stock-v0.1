package tool.rep;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import tool.DateUtil;
import tool.StringFunction;
import tool.StringX;

/**
 * 替换字符
 * 
 * @author lgwang
 * 
 */
public class Replace {

	private String src;
	private String url;
	private Map<String, String> map = new HashMap<String, String>();

	public Replace(String src) {
		init(src);
	}

	public Replace(String src, Map<String, String> map) {
		init(src, map);
	}

	public void init(String src, Map<String, String> map) {
		this.src = src;
		this.url = src;
		
		this.map.put("today", DateUtil.getToday());
		if (map != null)
			this.map.putAll(map);
		rep();
	}

	public void init(String src) {
		init(src, null);
	}

	/**
	 * 替换{$**}
	 * 
	 * @param dsc
	 * @param per
	 * @return
	 */
	public Replace rep(String dsc, String per) {
		String rep = "{$" + dsc + "}";
		url = url.replace(rep, per);
		// System.out.println(src);
		// System.out.println(url);
		return this;
	}

	/**
	 * 替换{$stock}
	 * 
	 * @param per
	 * @return
	 */
	public Replace rStock(String per) {
		String rep = "{$stock}";
		url = url.replace(rep, per);
		rep = "{$stockall}";
		if (per.startsWith("6"))
			url = url.replace(rep, per + ".ss");
		else
			url = url.replace(rep, per + ".sz");
		return this;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public Replace rep() {
		String rep;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			rep = "{$" + entry.getKey() + "}";
			url = url.replace(rep, entry.getValue());
		}
		// System.out.println(src);
		// System.out.println(url);
		return this;
	}

	/**
	 * 创造参数
	 * 
	 * @param para
	 * @return
	 */
	public static String CreatePara(String para) {
		return "{$" + para + "}";
	}

	public String getUrl() {
		return url;
	}

	public String getSrc() {
		return src;
	}

	/**
	 * 直接替换
	 * @param src
	 * @param map
	 * @return
	 */
	public static String replace(String src, Map<String, String> map){
		String rep;
		Map<String, String> m = initRMap(map.get("stock"));
		m.putAll(map);
		for (Map.Entry<String, String> entry : m.entrySet()) {
			rep = "{$" + entry.getKey() + "}";
			src = src.replace(rep, entry.getValue());
		}
		
		return src;
	}
	
	public static Map<String, String> initRMap(String ...stock) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("today", DateUtil.getToday());
		map.put("time", DateUtil.getNowTime("HH_mm_ss"));
		if(stock != null && stock.length != 0 && !StringX.isEmpty(stock[0])){
			if (stock[0].startsWith("6"))
				map.put("stockall", stock[0]+".ss");
			else
				map.put("stockall", stock[0]+".sz");
		}
		return map;

	}
	
	public static void main(String[] args) {
		String s = "{$aaa}~~{$ccc}~~~{$bbb}";
		Replace p = new Replace(s);
		p.rep("aaa", "w").rep("bbb", "l").rep("ccc", "g");
		String w = p.getUrl();
		System.out.println(w);
		System.out.println(s);
		w = new Replace(s).rep("aaa", "w").rep("bbb", "l").rep("ccc", "g")
				.getUrl();
	}
}
