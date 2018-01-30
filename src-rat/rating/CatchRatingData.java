package rating;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import parameter.Para;

public class CatchRatingData {
	private int ix;
	/**
	 * 代码，SYN，Tech，Finan，Info，Trade，Base
	 * @return
	 */
	private String[] sRating = new String[7];

	public String[] getsRating() {
		return sRating;
	}

	public void setsRating(String[] sRating) {
		this.sRating = sRating;
	}

	/**
	 * 抓取评级
	 * @param code
	 */
	public void catchRating(String code) {

		String buf = CatchURL(code);
		
		if ("0".equals(buf) ) {
			return;
		}
		String sTitle = printTitle(buf);
		
		if(!checkTitle(sTitle,code))
			return;
		
		if (!checkDate(buf)) {
			return;
		}

		sRating[0] = code;

		catchSYN(buf);
		catchTech(buf);
		catchFinan(buf);
		catchInfo(buf);
		catchTrade(buf);
		catchBase(buf);
		printRating();
		checkRating();

		buf = null;
	}

	/**
	 * 截取页面
	 * 
	 * @param code
	 * @return
	 */
	public String CatchURL(String code) {
		String strURL = "http://doctor.10jqka.com.cn/" + code + "/";
		URL url = null;

		InputStreamReader input = null;
		HttpURLConnection httpConn = null;
		StringBuilder contentBuf = new StringBuilder();
		BufferedReader bufReader = null;
		String line = "";
		System.out.println("截取页面:"+strURL);
		try {
			url = new URL(strURL);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			input = new InputStreamReader(httpConn.getInputStream(), "GBK");
			bufReader = new BufferedReader(input);
			System.out.println("正在读入数据...");
			while ((line = bufReader.readLine()) != null) {
				contentBuf.append(line);
//				System.out.println(line);
				if("<strong>历史波段诊断</strong>".equals(line.trim()))
					break;
			}
			System.out.println("数据读取完成...");
			input.close();
			bufReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("截取页面失败！！！");
			return "0";
		}
		System.out.println("截取页面完成");
		strURL = null;
		url = null;
		httpConn = null;
		return contentBuf.toString();
	}

	/**
	 * 检查标题
	 * @param sTitle
	 * @param code
	 * @return
	 */
	private boolean checkTitle(String sTitle, String code) {
		if (sTitle.equals(code)) {
			System.out.println("找不到该股票诊股信息，回到主页！");
			sRating[0] = code;
			return false;
		}
		if (sTitle.indexOf(code) == -1){
			System.out.println("找不到该股票诊股信息，未知页面！");
			return false;
		}
		
		return true;

	}

	/**
	 * 打印标题
	 * @param buf
	 * @return
	 */
	private String printTitle(String buf) {
		String strBegin = "<title>";
		String strEnd = "</title>";
		// System.out.println(buf);
		int beginIx = buf.indexOf(strBegin);
		int endIx = buf.indexOf(strEnd, beginIx);
		String result = buf.substring(beginIx + strBegin.length(), endIx);
		System.out.println("title:"+result);
		return result;

	}

	/**
	 * 检查日期
	 * @param buf
	 * @return
	 */
	private boolean checkDate(String buf) {
		String strBegin = "[诊断日期:";
		String strEnd = "]</span></p>";
		String date = Para.getDate();
		// System.out.println(buf);
		int beginIx = buf.indexOf(strBegin);
		int endIx = buf.indexOf(strEnd, beginIx);
		String result = null;
		try {
			result = buf.substring(beginIx + strBegin.length(), endIx);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("诊断日期失败！！！");
			return false;
		}
		System.out.println(strBegin + result);
		result = result.replace("年", "/").replace("月", "/").substring(0, 10);
		if (date.equals(result)) {
			return true;
		} else {
			System.out.println("诊断日期(" + result + ")与当前日期("
					+ date + ")不符!!!");
			return false;
		}
	}

	/**
	 * 综合抓取
	 * @param buf
	 * @return
	 */
	private String catchSYN(String buf) {
		String strbegin = "综合诊断：";
		int beginIx = buf.indexOf(strbegin, 6000);
		int endIx = buf.indexOf("分", beginIx);
		String result = buf.substring(beginIx + strbegin.length(), endIx);
		ix = endIx;
		System.out.println(strbegin + "\n" + result);
		sRating[1] = result;
		return result;
	}

	/**
	 * 技术抓取
	 * @param buf
	 * @return
	 */
	private String catchTech(String buf) {
		// String strBegin = "<div class=\"label\">";
		// String strEnd = "分";
		// int beginIx = buf.indexOf(strBegin,ix);
		// int endIx = buf.indexOf(strEnd,beginIx);
		// String result = buf.substring(beginIx + strBegin.length(), endIx);
		// ix = endIx;
		// return result;
		System.out.println("技术面：");
		String result = catchOther(buf);
		sRating[2] = result;
		return result;
	}

	/**
	 * 资金抓取
	 * @param buf
	 * @return
	 */
	private String catchFinan(String buf) {
		System.out.println("资金面：");
		String result = catchOther(buf);
		sRating[3] = result;
		return result;
	}

	/**
	 * 消息抓取
	 * @param buf
	 * @return
	 */
	private String catchInfo(String buf) {
		System.out.println("消息面：");
		String result = catchOther(buf);
		sRating[4] = result;
		return result;
	}

	/**
	 * 行业抓取
	 * @param buf
	 * @return
	 */
	private String catchTrade(String buf) {
		System.out.println("行业面：");
		String result = catchOther(buf);
		sRating[5] = result;
		return result;
	}

	/**
	 * 基本抓取
	 * @param buf
	 * @return
	 */
	private String catchBase(String buf) {
		System.out.println("基础面：");
		String result = catchOther(buf);
		sRating[6] = result;
		return result;
	}

	/**
	 * 抓取详情
	 * @param buf
	 * @return
	 */
	private String catchOther(String buf) {
		String strBegin = "<div class=\"label\">";
		String strEnd = "分";
		int beginIx = buf.indexOf(strBegin, ix);
		int endIx = buf.indexOf(strEnd, beginIx);
		String result = buf.substring(beginIx + strBegin.length(), endIx);
		ix = endIx;
		System.out.println(result);
		return result;
	}

	private void printRating() {
		System.out.println(Arrays.toString(sRating));
	}

	private void checkRating() {

		for (int i = 0; sRating.length > i; i++) {
			if (sRating[i].length() > 10) {
				sRating[1] = null;
				sRating[2] = null;
				sRating[3] = null;
				sRating[4] = null;
				sRating[5] = null;
				sRating[6] = null;
				break;
			}
		}
	}

	public static void main(String[] args) {
		CatchRatingData crd = new CatchRatingData();
		try {
			crd.catchRating("000002");
			// System.out.println(Arrays.toString(crd.sRating));
			// System.out.println(crd.checkDate("<span class='date'>[诊断日期:2014年06月18日 16:09]</span></p>"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
