package tool.fileAnalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import tool.DownLoad;
import tool.fileAnalysis.bean.TableBean;
import download.Path;
import download.PathMap;

/**
 * csv分析器
 * @author lgwang
 *
 */
public class CSVAnalysis extends TableBean{
	private InputStreamReader fr = null;
	private BufferedReader br = null;
	
	public CSVAnalysis(InputStream f) throws IOException {
		fr = new InputStreamReader(f);
		readCSVFile();
	}
	
	public CSVAnalysis(String url) throws Exception {
		
		initCSV(url);
		readCSVFile();
	}
	
	private void initCSV(String s) throws Exception {
		if(s.startsWith("http")){
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(s);
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			fr = new InputStreamReader(entity.getContent());
		}else{
			fr = new InputStreamReader(new FileInputStream(s));
		}

	}

	public void readCSVFile() throws IOException {
		br = new BufferedReader(fr);
		String rec = null;// 一行
		String str;// 一个单元格
		try {
			int iRowNo = 0;
			// 读取一行
			while ((rec = br.readLine()) != null) {
				Pattern pCells = Pattern
						.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
				Matcher mCells = pCells.matcher(rec + ",");
				List<String> cells = new ArrayList<String>();// 每行记录一个list
				// 读取每个单元格
				while (mCells.find()) {
					str = mCells.group();
					str = str
							.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,",
										"$1");
					str = str.replaceAll("(?sm)(\"(\"))", "$2");
					if(iRowNo == 0 ){
						addHead(str);
					}
					add(str);
				}
				iRowNo++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				fr.close();
			}
			if (br != null) {
				br.close();
			}
		}
	}

	public static void main(String[] args) throws Throwable {
//		String strURL = "http://ichart.finance.yahoo.com/table.csv?s=600887.ss";
		PathMap pm = new PathMap();
		Path p = pm.getPath("allmarket_wy");
		DownLoad.downLoanPath(p,"600535");
		CSVAnalysis parser = new CSVAnalysis(p.getM());
		List<List<String>> list = parser.getTabList();
		for (List<String> liststr : list){
			for (String string : liststr) {
				System.out.print(string + "\t");
			}
			System.out.println();
		}
		System.out.println("==================");
		System.out.println(parser.getICol());
		System.out.println("==================");
		System.out.println(parser.getIRow());
	}
}