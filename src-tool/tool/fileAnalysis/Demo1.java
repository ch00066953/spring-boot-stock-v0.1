package tool.fileAnalysis;
 
 
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
 
public class Demo1 {
	
    public static void main(String[] args)throws Exception {
    	// 支持http
    	/*System.setProperty("http.proxySet", "true");
    	System.getProperties().put("http.proxyHost", "61.160.212.181");
    	System.getProperties().put("http.proxyPort", 808);*/
    	String ip = "47.89.10.103:80";
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpGet=new HttpGet("http://www.goubanjia.com/"); // 创建httpget实例
        HttpHost proxy=new HttpHost(ip.split(":")[0], Integer.valueOf(ip.split(":")[1]) ,"http");
        RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
        Header[] hr = response.getAllHeaders();
        System.out.println("status：" + response.getStatusLine().getStatusCode());
        for (int i = 0; i < hr.length; i++) {
            Header header1 = hr[i];
            System.out.println("头部内容：" + header1);
        }
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
        
        //httpGet("https://www.taobao.com/","");
    }
    /**
    *
    * @测试点: 发送get请求，并且设置cookies
    * @验证点: TODO(这里用一句话描述这个方法的作用)
    * @param @param
    *            url
    * @param @param
    *            cookies @备注： void
    * @author zhangjun
    * @date 2017年11月7日
    * @修改说明
    */
   public static String httpGet(String url, String cookies) {

       String result = null;
        
       HttpHost proxy = new HttpHost("61.160.212.181", 888, "http"); //添加代理，IP为本地IP 8888就是fillder的端口
       CloseableHttpClient httpClient = HttpClients.createDefault();//添加代理
       try {
           // 创建httpGet
           HttpGet httpGet = new HttpGet(url);
           System.out.println("获取的url为:"+url);
           httpGet.setHeader("Connection", "keep-alive");
           httpGet.addHeader(new BasicHeader("Cookie", cookies));

           //代理
           RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
           httpGet.setConfig(config);
            
           System.out.println("executing request:" + httpGet.getURI());
           // 执行get请求
           CloseableHttpResponse response = httpClient.execute(httpGet);
           try {
               int code = response.getStatusLine().getStatusCode();
               System.out.println("返回的状态码:" + code);
               // 获取响应实体
               HttpEntity entity = response.getEntity();
               // 打印响应状态
               System.out.println(response.getStatusLine());
               if (entity != null) {
                   System.out.println("Response content length" + entity.getContentLength());
                   // 打印响应内容
                   result = EntityUtils.toString(entity);
                   // 打印响应头
                   System.out.println("Response content" + entity.getContent());
                   System.out.println("Response Contentype" + entity.getContentType());
                   System.out.println("Response ContenEncoding" + entity.getContentEncoding());
               }
               System.out.println("--------------");
               Header[] hr = response.getAllHeaders();
               for (int i = 0; i < hr.length; i++) {
                   Header header1 = hr[i];
                   System.out.println("头部内容：" + header1);
               }

           } finally {
               response.close();
           }
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           // 关闭连接，释放资源
           try {
               httpClient.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       return result;
   }
}