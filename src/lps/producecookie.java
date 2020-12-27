package lps;

//package com.cnxunao.esou.action.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import net.sf.json.JSONObject;
















































import javax.imageio.ImageIO;
import javax.lang.model.util.Elements;

import com.cnxunao.crawler.weibo.mvc.model.ProxyIP;
import com.cnxunao.crawler.weibo.mvc.service.ProxyIPService;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImpl;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImplck;
import com.gargoylesoftware.htmlunit.WebClient; 
import com.gargoylesoftware.htmlunit.javascript.host.Document;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.conn.params.ConnConnectionParamBean;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;

/*import com.cnxunao.esou.base.BaseAction;
import com.cnxunao.esou.query.Search;
import com.cnxunao.esou.util.DateUtil;
import com.cnxunao.esou.util.StringUtil;
import com.cnxunao.esou.util.common.Profile;
*/
import org.openqa.selenium.WebElement;

import redis.clients.jedis.Jedis;
import sun.net.www.protocol.http.HttpURLConnection;
//import sun.nio.ch.IOUtil;


public class producecookie {
	
	//ChaoJiYing cjy=new ChaoJiYing();
	// Jedis jedis = new Jedis("172.31.0.7");
	 Jedis jedis = new Jedis("172.31.0.7",6380);
	 ProxyIPService ipService =new ProxyIPServiceImplck(); 

	public void  sina() throws Throwable   {		
	//	System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe");
	//	C:\Program Files (x86)\Google\Chrome\Application
   	 System.setProperty ( "webdriver.chrome.driver" , "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe" );
   	  //WebDriver webDriver = new HtmlUnitDriver(true);
		//设置狐火浏览器代理：
		//FirefoxProfile profile = new FirefoxProfile();
		//network.proxy.type", 1   这里的1代表手工设置
		         //  profile.setPreference("network.proxy.type", 1);
		//network.proxy.http", "hzproxy.xxxx.com"  设置代理地址
		          //  profile.setPreference("network.proxy.http", ip);
		//network.proxy.http", "hzproxy.xxxx.com"  设置代理端口号
		          //  profile.setPreference("network.proxy.http_port", port);
		           // profile.updateUserPrefs(userPrefs);
		            
		           // proxy.setHttpProxy(PROXY)

		/*String PROXY = "localhost:8080";//如果不是本机，localhost替换成IP地址  
		  
		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();  
		proxy.setHttpProxy(PROXY)  
		     .setFtpProxy(PROXY)  
		     .setSslProxy(PROXY);  */
		//DesiredCapabilities cap = new DesiredCapabilities();  
		//cap.setPreference(CapabilityType.PROXY, proxy);  
	//	cap.set
		   FirefoxProfile profile = new FirefoxProfile();   
	      //  Proxy proxy = new Proxy();   
	      //  proxy.setHttpProxy("124.205.86.116:7008");   
	         //profile.setProxyPreferences(proxy);   
		       // profile.setPreference(key, value);
		   
		   
	      // profile.setPreference("network.proxy.type", 2);
	      //  profile.setPreference("network.proxy.autoconfig_url", "http://124.205.86.116:7008"); //自动代理配置

		   
		  

		// Set keyValue = pt.keySet(); 
//int qqq=0;
        // for (Iterator it = keyValue.iterator(); it.hasNext();) { 
           //  String key = (String) it.next(); 
		   ProxyIP proxyIP;
		  while(true)
		  {
				proxyIP = ipService.getProxyIP();
			      
				while (proxyIP==null) {
					try {
						Thread.sleep(10000);
						proxyIP = ipService.getProxyIP();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
          	 String ip="";
          	 String port="80";
          	 
          	 String key=proxyIP.getHost();
          	 port=proxyIP.getPort()+"";
//qqq++;
               ip=key.split(":")[0];
             //  port="";
            
          	 System.out.println("Ip:"+key+"-"+port);
  		   String proxyIpAndPort= ip+":"+port; //set proxy
  		   DesiredCapabilities cap = new DesiredCapabilities();
  		   Proxy proxy=new Proxy();
  		   proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
  		   cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
  		   cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
  		   System.setProperty("http.nonProxyHosts", "localhost");
  		   cap.setCapability(CapabilityType.PROXY, proxy);
  		   WebDriver  webDriver= new ChromeDriver(cap);
  		 WebDriver newWindow=null;

  		 /*String proxyIpAndPort= "localhost:8080";
  		  DesiredCapabilities cap = new DesiredCapabilities();
  		  Proxy proxy=new Proxy();
  		  proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
  		  cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
  		  cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
  		  System.setProperty("http.nonProxyHosts", "localhost");
  		  cap.setCapability(CapabilityType.PROXY, proxy);*/
  		  // System.setd
  		//   webDriver.
  		   
          // webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

  		/* DesiredCapabilities capabilities = DesiredCapabilities.chrome();
  		capabilities.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://"+proxyIpAndPort));*/

  		   
  		   
     	// WebDriver webDriver = new FirefoxDriver();
  	//	System.setProperty("http.proxyHost", ip);  
  	//	System.setProperty("http.proxyPort", port);  
     	
     	// webDriver.
        	 //Properties prop = System.getProperties();        
        	   // 设置http访问要使用的代理服务器的地址        
        	  // prop.setProperty("http.proxyHost", ip);        
        	   // 设置http访问要使用的代理服务器的端口        
        	  // prop.setProperty("http.proxyPort", port);        
         try {
        	// proxyIpAndPort= "124.205.86.36:7008";
        	  // proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
        	  // cap.setCapability(CapabilityType.PROXY, proxy);
        	  //webDriver = new FirefoxDriver(cap);
        	// webDriver.get("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=ip&oq=webdriver%2520%25E4%25BB%25A3%25E7%2590%2586&rsv_pq=c91ac3d900054be3&rsv_t=9711yrCzsuAbYyIpJ%2FMfqDb5i6amE571zsn4CwtoJeP2yty5X1SzycHRX4s&rqlang=cn&rsv_enter=1&inputT=1174&rsv_sug3=18&rsv_sug1=10&rsv_sug7=100&rsv_sug2=0&rsv_sug4=2909");
			//webDriver.get("http://weixin.sogou.com/weixin?type=1&s_from=input&query=ff&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=794&sst0=1490278693281&lkt=0%2C0%2C0");
        	try{
        		webDriver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);

        	 webDriver.get("http://www.dianping.com");
        	
        	 //webDriver.get("http://www.dianping.com/search/category/2/0/r2580");
        	}
        	catch(Exception ee)
        	{
        		System.out.println("发生异常");
        		WebDriverWait wait=new WebDriverWait(webDriver,20,2000);
                try{
                wait.until(new ExpectedCondition<Boolean>(){
                    public Boolean apply(WebDriver driver){
                        System.out.println("sleep");
                        return driver.findElement(By.cssSelector("a.index-title")).isDisplayed();
                    }
                });
                }catch(Exception ff)
                {
                	ipService.changeProxyStatus(proxyIP, 4);
                	webDriver.quit();
            		continue;
                }
        		/*if(webDriver.findElement(By.cssSelector("a[title=下一页]")).isDisplayed()==false)
        				{
        		
        		
        				}*/

        	}
        	
        	
        	
        	String current_handle = webDriver.getWindowHandle();

        	 webDriver.findElement(By.cssSelector("a.index-title")).click();
        	
        	
        	
        	 Set<String> all_handles = webDriver.getWindowHandles();
        	//循环判断，把当前句柄从所有句柄中移除，剩下的就是你想要的新窗口
        	Iterator<String> it = all_handles.iterator();
        	String handle = null;
        	while(it.hasNext()){
        	handle = it.next();
        	if(current_handle==handle) continue;
        	//跳入新窗口,并获得新窗口的driver - newWindow
        	newWindow = webDriver.switchTo().window(handle);
        	}
        	
        	webDriver=newWindow;
        	
        	
        	System.out.println("打开浏览器正常");
        	//Thread.sleep(30000);
        	if (webDriver.getPageSource().indexOf("<h1>403 Forbidden</h1>")!=-1)
        	 {
        		System.out.println("IP封锁了");
        		ipService.changeProxyStatus(proxyIP, 4);
        		webDriver.quit();
        		continue;
        	 }
        	else
        	{
        		System.out.println("IPzc");
        	}
        	     
    		ipService.changeProxyStatus(proxyIP, 1);
    		System.out.println("提取cookie ");
    	 Set<Cookie> cookies = webDriver.manage().getCookies();
             String cookieStr = "";
             for (Cookie cookie : cookies) {
                 cookieStr += cookie.getName() + "=" + cookie.getValue() + "; ";
             }
             if(cookieStr.equals(""))
            	 continue;
           //  Thread.sleep(30000);
             cookieStr+="==="+"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36";
             System.out.println(cookieStr);
             jedis.sadd("cookies", cookieStr);
           
           //  Thread.sleep(20000);
             webDriver.quit();
             
             
        // }
            // System.out.println(cookieStr);
        	 
        	 
        //	 String pString=webDriver.getPageSource();
        ///	 JavascriptExecutor js= (JavascriptExecutor)webDriver;
            // js.executeScript("$('title').text('"+ip+"');");
        	 
        	// if(pString.indexOf("链接已过期")!=-1)
        		// webDriver.quit();
        	// else
        	// {/*
        		
        		
        		 
        		  
        		 
        		 
        		 
        		 
        /*		 
        		 if (webDriver.getPageSource().indexOf("请输入验证码")!=-1) {
                 		try {
                 			
              			
                       //  JavascriptExecutor js= (JavascriptExecutor)webDriver;
                       //  js.executeScript("$('title').text("+i+")");
                       	    //不停的检测，一旦当前页面URL不是登录页面URL，就说明浏览器已经进行了跳转
                 			       
                                   while (true) {
                                	   
                                      
                                     //  System.out.println(webDriver.getCurrentUrl()+webDriver.getTitle());
                                       
                                       if (webDriver.getPageSource().indexOf("请输入验证码")==-1) {
                                    	   System.out.println("pass");
                                    	   webDriver.quit();
                                           break;
                                       }
                                       else {
                                    	   webDriver.get("http://mp.weixin.qq.com/profile?src=3&timestamp=1489058404&ver=1&signature=pXJrT1qDLvNXplBpbOUuKNenzRrk8EGvXO2aRUE3bLw9nOO0eO4CAN6Jkcq1Y1lSY4VsdRFveShVub916CNkyQ==");
                                      	 
                                    	   
                                           if (webDriver.getPageSource().indexOf("请输入验证码")==-1) {
                                        	   System.out.println("pass");
                                        	   webDriver.quit();
                                               break;
                                           }
                                    	   cookies = webDriver.manage().getCookies();
                                            cookieStr = "";
                                           for (Cookie cookie : cookies) {
                                               cookieStr += cookie.getName() + "=" + cookie.getValue() + "; ";
                                           }
                                        //   System.out.println(cookieStr);
                                    	   URL url = new URL("http://mp.weixin.qq.com/mp/verifycode?cert=1490262219969.9756");   
                                  		 HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  
                                  		 httpConn.setRequestProperty("Cookie", cookieStr);  
                           httpConn.setRequestMethod("GET");
                           httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Mobile Safari/537.36");
                           httpConn.setRequestProperty("Referer", "http://mp.weixin.qq.com/profile?src=3&timestamp=1489058404&ver=1&signature=pXJrT1qDLvNXplBpbOUuKNenzRrk8EGvXO2aRUE3bLw9nOO0eO4CAN6Jkcq1Y1lSY4VsdRFveShVub916CNkyQ==");  
                          httpConn.setRequestProperty("Host", "mp.weixin.qq.com");
                          httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*;q=0.8");
                          httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
                          httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
                          httpConn.setRequestProperty("Connection", "keep-alive");
 InputStream inputStream=httpConn.getInputStream();
 String name=System.currentTimeMillis()+".png";
                                  		 OutputStream outputStream=new FileOutputStream(name);
                                  		 IOUtils.copy(inputStream, outputStream);
 String cookieskey = "Set-Cookie";  
                                  		    Map<String, List<String>> maps = httpConn.getHeaderFields();  
                                  		    List<String> coolist = maps.get(cookieskey);  
                                  		    Iterator<String> it2 = coolist.iterator();  
                                  		    StringBuffer sbu = new StringBuffer();  
                                  		    sbu.append("eos_style_cookie=default; ");  
                                  		    while(it2.hasNext()){  
                                  		       
                                  		        String cc=it2.next()+"";
                                  		        sbu.append(cc);  
                                  		        String cc2String=cc.split("; ")[0];
                                  		        Cookie cookie=new Cookie(cc2String.split("=")[0],cc2String.split("=")[1],"/",null);
                                  		        webDriver.manage().deleteCookieNamed("sig");

                                      	        webDriver.manage().addCookie(cookie);
                                      		 break;
                                  		        
                                  		    }  
                                  		 //   System.out.println(sbu.toString());   
                                  		//	org.jsoup.nodes.Document doca=Jsoup.connect("http://esou.me/yunwei/up.jsp").timeout(60000).post();
                                  		  try {
                                  		//  Jsoup.connect("http://syj.yunaodata.com/sc/disposal_xinge.do?ti=code"+qqq+"&sm=code"+System.currentTimeMillis()+ip+" break"+"&url=http://esou.me/yunwei/index.jsp?s="+Math.random()+"&token=0ff926155ff5825f414d8889bdf79e043998d2fe").post();
                                  		} catch (Exception e) {
      										e.printStackTrace();
      										// TODO: handle exception
      									}
                                  		// Thread.sleep(20000L);
                                  		 String cc="7777";
                                  		//String ccjsonString= cjy.PostPic("lhxlhxl", "890708qinyang", "893336", "1901", "0", "0", name, name);
                                  		
                                  	//	System.out.println(ccjsonString);
                                  		//JSONObject joJsonObject=JSONObject.fromObject(ccjsonString);
                                  	//	cc=joJsonObject.getString("pic_str");
                                  	//	String picid=joJsonObject.getString("pic_id");
                                  	 
                                  	   while (cc.equals("7777")) {
                                  		  try {
                                       		 
                                       		org.jsoup.nodes.Document doc=Jsoup.connect("http://esou.me/yunwei/code.jsp").timeout(60000).post();
                                       	  cc=doc.select("span").text();
  									} catch (Exception e) {
  										e.printStackTrace();
  										// TODO: handle exception
  									}
                                  		 Thread.sleep(1000L);
									}
                                  		 
                                  		 
                                  		// cc=
                                  	  webDriver.findElement(By.id("input")).sendKeys(cc);
                                  	webDriver.findElement(By.id("bt")).click();
                                  	   //webDriver.get("http://mp.weixin.qq.com/mp/verifycode?input="+cc);
                                  	// System.out.println("pos"+webDriver.getPageSource());
                                  	 Thread.sleep(10000L);
                                     if (webDriver.getPageSource().indexOf("请输入验证码")==-1) {
                                  	   System.out.println("pass");
                                  	   webDriver.quit();
                                         break;
                                     }
                                     else {
										  	//webDriver.findElement(By.id("bt")).click();
                                  	   //webDriver.get("http://mp.weixin.qq.com/mp/verifycode?input="+cc);
                                  	// System.out.println("pos"+webDriver.getPageSource());
                                  	 //Thread.sleep(20000);
System.out.println("code error");


  // String re= cjy.ReportError("lhxlhxl", "890708qinyang", "893336", picid);

									}
									}
                                   }
                               } catch (InterruptedException e) {
                            	   
                                   e.printStackTrace();
                                   webDriver.quit();
                                   break;
                               }
    				}
        	 }
        	 */
        	 
        	 
        	 
        	 
        	 
        	 
        	// Thread.sleep(30000);
         } catch (Exception e) {
        	   webDriver.quit();
			e.printStackTrace();
			// TODO: handle exception
		}
	}
        }
              
         //proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
         
      
      	 
      	 
      	 
      	 
      	 


//System.out.println(qqString);
   
	
	public  void saveImage(BufferedImage image, String fileName) throws Exception { 
		
		 File f = new File(fileName); 
		 // 通过 ImageIO 将图像写入到文件
		 ImageIO.write(image, "jpg", f); 
		 }
	
	public BufferedImage createElementImage(WebElement webElement,WebDriver web)
			 throws IOException {
			 // 获得webElement的位置和大小。
			 Point location = webElement.getLocation();
			 Dimension size = webElement.getSize();
			 // 创建全屏截图。
			 BufferedImage originalImage =
			  ImageIO.read(new ByteArrayInputStream(takeScreenshot(web)));
			 // 截取webElement所在位置的子图。
			 BufferedImage croppedImage = originalImage.getSubimage(
			  location.getX(),
			  location.getY(),
			  size.getWidth(),
			  size.getHeight());
			 return croppedImage;
			}
	public byte[] takeScreenshot(WebDriver webdriver) throws IOException {
		 TakesScreenshot takesScreenshot = (TakesScreenshot) webdriver;
		 return takesScreenshot.getScreenshotAs(OutputType.BYTES);
		}
	 
	 public static void main(String []args)throws Throwable
	 {
		 producecookie weibocom2=new producecookie();
		// List<String> //=new ArrayList<String>();
		// Properties pt=new Properties();
		 /*try { 
	          FileInputStream  inputFile = new FileInputStream("conf/ipAddress.txt"); 
	            pt.load(inputFile); 
	            inputFile.close(); 
	        } catch (FileNotFoundException ex){ 
	            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在"); 
	            ex.printStackTrace(); 
	        } catch (IOException ex){ 
	            System.out.println("装载文件--->失败!"); 
	            ex.printStackTrace(); 
	        } */
		    weibocom2.sina();
        
	//	 ipsList.add("");
		
	//	weibocom2.sina("124.205.86.36",7008);

		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
