package com.ch.service.webcrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.ch.mapper.IpProxyMapper;
import com.ch.pojo.IpProxy;
import com.ch.pojo.IpProxyExample;
import com.ch.pojo.IpProxyExample.Criteria;

import download.Path;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;
import tool.DateUtil;
import tool.DownLoad;
import tool.StringX;
import tool.fileAnalysis.CSVUtils;
import wlgtext.jsoup.HtmlTableBReader;

@Slf4j
@Service
public class IPproxyService {

	private static List<String> ipList = new ArrayList<String>() {{
	/*		"202.109.237.35,80,http",
			"202.109.237.36,80,http",
			"115.239.210.42,80,http",
			"61.160.190.146,8090,http",
			"121.58.17.52,80,http",
			"119.27.177.169,80,http",
			"121.58.17.52,80,http",
//			"61.216.36.80,8080",//taiwan
			"140.205.222.3,80,http",//ali
			"47.89.10.103,80,http",//ali
*/			add("");
	}};
	@Autowired
	IpProxyMapper IpProxyMapper ;
	@Autowired
	IpProxyExample IpProxyExample;
	
	public void downLoadIpProxy() throws IOException {
		Path p = PathMap.getPara("ipproxydl");
		DownLoad.downLoanPath(p);
	}
	
	public void initIpProxy() throws Exception {
		Path p = PathMap.getPara("ipproxydl");
		String id = "services";
		String para = "div.span12";
		HtmlTableBReader htrNw = new HtmlTableBReader(p ,id ,para);
		Path p1 = PathMap.getPara("ipproxyinit");
		CSVUtils.createCSVFile(htrNw, p1);
		for (Map<String, String> m : htrNw.getList()){
			if("IP:PORT".equals(m.get("IP:PORT"))||!"电信".equals(m.get("运营商")))
				continue;
			String sip = m.get("IP:PORT").replace(":", ",") + ",";
			sip += m.get("类型") + ",";
			if(testIpProxy(sip))
				ipList.add(sip);
		}
		log.info(ipList.toString());
	}
	
	@Async
	public void insertIpProxy() throws Exception {
		Path p = PathMap.getPara("ipproxydl");
		String id = "services";
		String para = "div.span12";
		HtmlTableBReader htrNw = new HtmlTableBReader(p ,id ,para);
		IpProxy ipp = new IpProxy();
		for (Map<String, String> m : htrNw.getList()){
			
			String sip = m.get("IP:PORT").split(":")[0];
			String sport = m.get("IP:PORT").split(":")[1];
			String type = m.get("类型");
			String anonymity = m.get("匿名度");
			String belong = m.get("IP归属地");
			String operator = m.get("运营商");
			String speed = m.get("响应速度");
			String prooftime = DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss");
			String fristtime = DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss");
			
			if(IpProxyMapper.selectByPrimaryKey(sip) != null)
				continue;
			
			String allip = m.get("IP:PORT").replace(":", ",") + ",";
			allip += m.get("类型") + ",";
			StopWatch sw = new StopWatch();
			sw.start();  
			boolean testip = testIpProxy(allip);
			sw.stop();
			speed = String.valueOf(sw.getTotalTimeMillis());
			log.info(allip + testip + speed);
			if(testip){
				ipp.setIp(sip);
				ipp.setPort(sport);
				ipp.setType(type);
				ipp.setBelong(belong);
				ipp.setAnonymity(anonymity);
				ipp.setOperator(operator);
				ipp.setSpeed(speed);
				ipp.setProoftime(prooftime);
				ipp.setFristtime(fristtime);
				ipp.setStatus("1");
				IpProxyMapper.insert(ipp);
				ipList.add(allip);
			}
		}
		log.info(ipList.toString());
	}
		
	public boolean testIpProxy(String sip) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = null;
		if(StringX.isEmpty(sip)){
			log.info("sip=" + sip+ "本地地址"+"");
     		return true;
		}
		try {
			httpClient=HttpClients.createDefault(); // 创建httpClient实例

//			httpClient.
//	        HttpGet httpGet=new HttpGet("http://www.goubanjia.com/"); // 创建httpget实例
	        HttpGet httpGet=new HttpGet("http://www.cs.ecitic.com/"); // 创建httpget实例
	        String http = sip.split(",")[2];
			http = http.length() <= 4 ? "http" : "https";
	        HttpHost proxy=new HttpHost(sip.split(",")[0], Integer.valueOf(sip.split(",")[1]), http);
	        RequestConfig requestConfig=RequestConfig.custom().
	        		setSocketTimeout(3000).setConnectTimeout(3000).setProxy(proxy).build();
	        httpGet.setConfig(requestConfig);
	        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
	        response=httpClient.execute(httpGet); // 执行http get请求
	        log.info("status=" + response.getStatusLine().getStatusCode());
	        // 如果成功
	     	if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	     		log.info("sip=" + sip+ "可以使用，连接时间"+"");
	     		return true;
	     	}else
	     		log.info("sip=" + sip+ "不可使用使用！");
	     		return false;
		} catch (Exception e) {
			log.info("sip=" + sip+ e);
			return false;
		}finally {
			try {
				if(response!=null)
					response.close(); // response关闭
				if(httpClient!=null)
					httpClient.close(); // httpClient关闭
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 *检查list里面的ip是否可用
	 */
	public void checkIP() {
//		ip.forEach(ip -> testIpProxy(ip));
//		List l = ip.stream().map((ip) ->ip +"@" + testIpProxy(ip) ).forEach(System.out::println);
		List list = ipList.stream().map((ip) ->ip +"@" + testIpProxy(ip) ).collect(Collectors.toList());
		list.forEach(System.out::println);
	}
	/**
	 *检查list里面的ip是否可用
	 */
	public void recheckIP() {
		Criteria criteria = IpProxyExample.createCriteria();
		criteria.andStatusEqualTo("1");
		List<IpProxy> listIP = IpProxyMapper.selectByExample(IpProxyExample);
		String prooftime = DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss");
//		ip.forEach(ip -> testIpProxy(ip));
//		List l = ip.stream().map((ip) ->ip +"@" + testIpProxy(ip) ).forEach(System.out::println);
		for (IpProxy proxy : listIP){
			String status = "1";
			String allip = proxy.getIp()+","+proxy.getPort()+","+proxy.getType();
			StopWatch sw = new StopWatch();
			sw.start();  
			boolean testip = testIpProxy(allip);
			sw.stop();
			String speed = String.valueOf(sw.getTotalTimeMillis());
			log.info(allip + testip + speed);
			if(!testip){
				status = "0";
			}else{
				if(!ipList.contains(allip) ||sw.getTotalTimeMillis() < 5000)
					ipList.add(allip);
			}
			proxy.setSpeed(speed);
			proxy.setProoftime(prooftime);
			proxy.setStatus(status);
			IpProxyMapper.updateByPrimaryKeySelective(proxy);
		}
		ipList.forEach(log::info);
	}

	public synchronized List<String> getIpList() {
		if(ipList.size() < 6)
			recheckIP();
		return ipList;
	}


	
}
