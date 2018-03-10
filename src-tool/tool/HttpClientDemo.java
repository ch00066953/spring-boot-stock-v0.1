package tool;
import java.io.FileOutputStream; 

import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.CookieStore; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.impl.conn.PoolingClientConnectionManager; 
import org.apache.http.util.EntityUtils; 


/** 
 * TODO(用一句话描述该文件的作用) 
 * 
 * @title: HttpClientDemo.java 
 * @author zhangjinshan-ghq 
 * @date 2014-6-11 14:59:04 
 */


public class HttpClientDemo 
{ 
  
  /** 
   * The main method. 
   * 
   * @param args the arguments 
   * @throws Exception the exception 
   */
  public static void main(String[] args) throws Exception 
  { 
    getResoucesByLoginCookies(); 
  } 
  
  /** 
   * 根据登录Cookie获取资源 
   * 一切异常均未处理，需要酌情检查异常 
   * 
   * @throws Exception 
   */
  private static void getResoucesByLoginCookies() throws Exception 
  { 
    HttpClientDemo demo = new HttpClientDemo(); 
    String username = "......";// 登录用户 
    String password = "......";// 登录密码 
  
    // 需要提交登录的信息 
    String urlLogin = "http://www.sse.com.cn/assortment/stock/list/share/"; 
  
    // 登录成功后想要访问的页面 可以是下载资源 需要替换成自己的iteye Blog地址 
    String urlAfter = "http://query.sse.com.cn/security/stock/downloadStockListFile.do?csrcCode=&stockCode=&areaName=&stockType=1";
  
    DefaultHttpClient client = new DefaultHttpClient(new PoolingClientConnectionManager()); 
    HttpResponse response;
    HttpEntity entity;
    /** 
     * 第一次请求登录页面 获得cookie 
     * 相当于在登录页面点击登录，此处在URL中 构造参数， 
     * 如果参数列表相当多的话可以使用HttpClient的方式构造参数 
     * 此处不赘述 
     */
//    HttpGet post = new HttpGet(urlLogin); 
//    HttpResponse response = client.execute(post); 
//    HttpEntity entity = response.getEntity(); 
//    CookieStore cookieStore = client.getCookieStore(); 
//    client.setCookieStore(cookieStore); 
//  
    /** 
     * 带着登录过的cookie请求下一个页面，可以是需要登录才能下载的url 
     * 此处使用的是iteye的博客首页，如果登录成功，那么首页会显示【欢迎XXXX】 
     * 
     */
    HttpGet get = new HttpGet(urlAfter); 
//    get.setHeader("Host", "query.sse.com.cn");    
//    get.setHeader("Connection", "keep-alive");    
//    get.setHeader("Upgrade-Insecure-Requests", "1");    
//    get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");  
//    get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");    
    get.setHeader("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");    
//    get.setHeader("Accept-Encoding", "gzip, deflate");    
//    get.setHeader("Accept-Language", "zh-CN,zh;q=0.9");    
//    get.setHeader("Cookie", "yfx_c_g_u_id_10000042=_ck18012514120519019191430319032; yfx_mr_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_key_10000042=; VISITED_COMPANY_CODE=%5B%22600028%22%5D; seecookie=%5B600028%5D%3A%u4E2D%u56FD%u77F3%u5316; yfx_mr_f_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; VISITED_MENU=%5B%228529%22%2C%228530%22%2C%228532%22%2C%229062%22%2C%228536%22%2C%228537%22%2C%228535%22%2C%228525%22%2C%228464%22%2C%228523%22%2C%228528%22%5D; VISITED_STOCK_CODE=%5B%5D; yfx_f_l_v_t_10000042=f_t_1516860725896__r_t_1519784923727__v_t_1519804991137__r_c_3");    
    response = client.execute(get); 
    entity = response.getEntity(); 
  
    System.out.println(response.getStatusLine().getStatusCode());
    /** 
     * 将请求结果放到文件系统中保存为 myindex.html,便于使用浏览器在本地打开 查看结果 
     */
  
//    String pathName = "d:\\index.html"; 
//    writeHTMLtoFile(entity, pathName); 
  } 
  
  /** 
   * Write htmL to file. 
   * 将请求结果以二进制形式放到文件系统中保存为.html文件,便于使用浏览器在本地打开 查看结果 
   * 
   * @param entity the entity 
   * @param pathName the path name 
   * @throws Exception the exception 
   */
  public static void writeHTMLtoFile(HttpEntity entity, String pathName) throws Exception 
  { 
  
    byte[] bytes = new byte[(int) entity.getContentLength()]; 
  
    FileOutputStream fos = new FileOutputStream(pathName); 
  
    bytes = EntityUtils.toByteArray(entity); 
  
    fos.write(bytes);
	fos.flush();

	fos.close();
	
  } 
  
} 