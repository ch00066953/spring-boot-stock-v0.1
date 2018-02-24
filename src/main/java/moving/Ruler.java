package moving;

/**
 * 迟标，前后不闭
 * 小于LOW低
 * 大于HIGH高
 * @author lgwang
 *
 */
public class Ruler {

	/*
	 * 综合标尺
	 */
	public final static String SVN_HIGH = "6.9"; 
	public final static String SVN_LOW = "4.8"; 
	
	/*
	 * 技术标尺
	 */
	public final static String TECH_HIGH = "7.8"; 
	public final static String TECH_LOW = "6.8"; 
	
	/*
	 * 资金标尺
	 */
	public final static String FINAN_HIGH = "6.6"; 
	public final static String FINAN_LOW = "2.6"; 
	
	/*
	 * 消息标尺
	 */
	public final static String INFO_HIGH = "6.6"; 
	public final static String INFO_LOW = "4.8"; 
	
	/*
	 * 行业标尺
	 */
	public final static String TRADE_HIGH = "7.7"; 
	public final static String TRADE_LOW = "6.4"; 
	
	/*
	 * 基本面标尺
	 */
	public final static String BASE_HIGH = "7.2"; 
	public final static String BASE_LOW = "4.7"; 
	
	/*
	 * 编号标志
	 */
	public final static String CHANGE_HIGH = "H"; //至高
	public final static String CHANGE_LOW_MEDIUM = "LM"; //低至中 
	public final static String CHANGE_HIGH_MEDIUM = "HM"; //高至中
	public final static String CHANGE_LOW = "L"; //至低
}
