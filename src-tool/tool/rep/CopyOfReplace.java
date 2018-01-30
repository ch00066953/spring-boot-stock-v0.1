package tool.rep;

/**
 * 替换字符单例类
 * @author lgwang
 *
 */
public class CopyOfReplace {

	private String src;
	private String url;
	private volatile static CopyOfReplace replace;
	
	public CopyOfReplace(String src) {
		this.src = src;
		this.url = src;
	}
	
	/**
	 * 替换{$**}
	 * @param dsc
	 * @param per
	 * @return
	 */
	public CopyOfReplace rep(String dsc,String per){
		String rep = "{$"+dsc+"}";
		url = url.replace(rep, per);
//		System.out.println(src);
//		System.out.println(url);
		return this;
	}
	
	/**
	 * 创造参数
	 * @param para
	 * @return
	 */
	public String CreatePara (String para){
		return "{$"+para+"}";
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getSrc() {
		return src;
	}
	
	public static CopyOfReplace repFac(String src) {
		if (replace == null) {  
	        synchronized (CopyOfReplace.class) {  
	        if (replace == null) {  
	        	replace = new CopyOfReplace(src);  
	        }  
	        }  
	    }
		return replace;  
	}
	
	public static void main(String[] args) {
		String s = "{$aaa}~~{$ccc}~~~{$bbb}";
		CopyOfReplace p = repFac(s);
		p.rep("aaa", "w").rep("bbb", "l").rep("ccc", "g") ;
		String w = p.getUrl();
		System.out.println(w);
		System.out.println(s);
		w = repFac(s).rep("aaa", "w").rep("bbb", "l").rep("ccc", "g").getUrl() ;
	}
}
