package tool;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ch.service.webcrawler.HttpUtil;

public class HttpUtilTest {

	@Test
	public void testGetUrlAsStringString() {
		String a;
		Map<String, String> params = new HashMap<String, String>();
		params.put("s", "399001.SZ");
		params.put("f", "d1");
		try {
			a = HttpUtil.getUrlAsString("http://download.finance.yahoo.com/d/quotes.csv",params);
			
			System.out.printf("a= %s",a);
			
			System.out.printf("data= %s",DateUtils.simplePDate(a));;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUrlAsStringStringMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUrlAsStringStringMapOfStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostUrlAsStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostUrlAsStringStringMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostUrlAsStringStringMapOfStringStringMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void
			testPostUrlAsStringStringMapOfStringStringMapOfStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDownloadFile() throws IOException {
		HttpUtil.downloadFile(
        "http://basic.10jqka.com.cn/new/000002/xls/mainreport.xls",
        "f:\\0000022.xls","f:\\0000023.xls");
	}
	@Test
	public void testDownloadFile2() throws IOException {
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");
		HttpUtil.downloadFile2(
				"http://query.sse.com.cn/security/stock/downloadStockListFile.do?csrcCode=&stockCode=&areaName=&stockType=1",
				headerMap,
				"f:\\0000022.xls","f:\\0000023.xls");
	}
	
	@Test
	public void testIP(){
		HttpUtil HttpUtil = new HttpUtil();
		//HttpUtil.checkIP();
	}

}
