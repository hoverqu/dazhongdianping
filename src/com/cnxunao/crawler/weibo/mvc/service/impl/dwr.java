package com.cnxunao.crawler.weibo.mvc.service.impl;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sun.org.omg.SendingContext.CodeBasePackage.URLSeqHelper;

public class dwr {
	 static  HttpClientContext context =HttpClientContext.create();
      static  HttpContext context1=null;
	static Map<String,String> ck=new HashMap<String,String>();
	public static void main(String []args) throws Exception {
		
		 dwr hts=new dwr();
		String url="http://wyq.sina.com/dwr/exec/NewsOperate.shareSingleNews.dwr";
		HttpClient client=new DefaultHttpClient();
		//String urlString="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=Mjc4NjgzMzYwMA==&scene=124&uin=MzA0MjQ4MDc1&key=05d58d453d781a66092114e4b2c2de3ced1c4a0514d42b7fc91c9c4983457c8983a7b7b980e9f2924ab0881db5b06305bd0f44b370d70a456981d99c1342a20dc18d3a090685d48115567f5ac1caee62&devicetype=Windows+UnKnow&version=62040549&lang=zh_CN&a8scene=7&pass_ticket=lWbDHjWxCaaMULiEiU%2F1207E7aQZfxggi8ln4HaUu3b9bCpeGB5ijgHPdgkGup%2B1&winzoom=1";
			//hts.getcookies("MzA0MjQ4MDc1","82a8432f5ab6082cc2ad75b588c489007380b16b5e09085f6784669a3fe84ab5790e196f5d3a69246c857878f57f695d78dd51d568be4409df5504877da95d4a260622df8d40cd91822ad852b1849a1e","MzIxMzEyNDMwMw==");
			
		/*
		 * 
		 * 
		 * 
		 * callCount=1
c0-scriptName=NewsOperate
c0-methodName=shareSingleNews
c0-id=4583_1508840476802
c0-param0=string:317598
c0-param1=string:815088375336844154982426
c0-param2=number:1
c0-param3=string:2017-10-23%2018%3A06%3A34
c0-param4=string:2017-10-24%2018%3A06%3A34
xml=true
		 */
		/* List<NameValuePair> params = new ArrayList<NameValuePair>();
		   params.add(new BasicNameValuePair("callCount", "1"));  
		   params.add(new BasicNameValuePair("c0-scriptName", "NewsOperate"));
		   params.add(new BasicNameValuePair("c0-methodName", "shareSingleNews"));
		   params.add(new BasicNameValuePair("c0-id", "4583_1508840476802"));
		  // params.add(new BasicNameValuePair("batchId", "0"));
		   params.add(new BasicNameValuePair("c0-param0", "string:317598"));   //参数1
		   params.add(new BasicNameValuePair("c0-param1", "string:815088375336844154982426"));   //参数2
		   params.add(new BasicNameValuePair("c0-param2", "number:1"));   //参数2
		   params.add(new BasicNameValuePair("c0-param3", "string:2017-10-23 18:06:34"));   //参数2
		   params.add(new BasicNameValuePair("c0-param4", "string:2017-10-24 18:06:34"));   //参数2

		   //Host:wyq.sina.com
		  // Origin:http://wyq.sina.com
		  // Referer:http://wyq.sina.com/load.shtml
		   params.add(new BasicNameValuePair("xml", "true"));*/
		   
		/*   Header[] headers = {new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)"),  
		             new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"), 
		             new BasicHeader("Content-Type", "text/plain"),  
		             new BasicHeader("Host", "wyq.sina.com"),  
		             new BasicHeader("Origin", "http://wyq.sina.com"),  
		             new BasicHeader("Referer", "http://wyq.sina.com/load.shtml"),  
		             new BasicHeader("Accept-Encoding", "gzip, deflate")
		            // new BasicHeader("Cookie", "UM_distinctid=15f4dd6b34d1c9-0116b6da66913b-c303767-100200-15f4dd6b34e4b3; www=www_31759818511780165_1390; CNZZDATA1255980179=1719450499-1508838699-http%253A%252F%252Fwyq.sina.com%252F%7C1508838699; JSESSIONID=98CDE62A3B29FF87A7ABE4C52461FE98")
		        }; */
		//String reString=hts.textpost(client, url, headers, params, "");
		//httpTest("915088467197256042522326");
		//   getrq();
			// System.out.println(reString);
			
    	//	hts.getrealurl("https://mp.weixin.qq.com/s?src=11&timestamp=1505427596&ver=393&signature=dZVYMFZ2vYJn*X3xMwACoAMKh2MG5Ap4ktU46LGoF-1ysQ5NzE9*qRbGc6x3vz7AVd4E5WYH8tgA2ForC0timKbWX4611*bzLzGz9I2ezumHXE7pzb88ZvOJIDpycVo0&new=1&lps=1");
    	//	String urString="https://mp.weixin.qq.com/s?src=11&timestamp=1505623251&ver=397&signature=to8y3kL-JijH-Mzgtlfqt-1qtRNOWk6rLTps-Bs*Z3z-0I8gDwMFaIRx2Cy81QWtvDoY5Ts7RHD2u*PrusEUKnR3wle2DPUIrNEtHzwzKMMdm12mLJ7DA8HJElgkrkdW&new=1";
    	//urString="https://mp.weixin.qq.com/s?src=11&timestamp=1505366003&ver=391&signature=l2oLRD4kQEXD-wwoIo1VNdEa1KY-stzWUbTVKetV-gNdjSXXl1eTR72eu6G*mf1oT1OvoPmOHV5QwJSV5OY7cQ1j3eH34I5RIepbeNVRBUaKmx8-Whki*DjgoXxf6ek1&new=1";
    		//Document document = Jsoup.
					//connect(urString+"&key=5ba8d035d662453485ca7fad0bad21a56ac6813b45c0f6fb1877d4311ced15dd0e4d0f5464b4310b76b89e7573060ea5656cbaaa2b8fabaa85eda1a504141e501801725a906533786384b9b83e45c19c&ascene=1&uin=MzQyNDg0NDYyNw%3D%3D&devicetype=Windows+7&version=6205051a&pass_ticket=qQxDnQ8US%2BJymLVtyJUs5FXZ7Yi0xA59VotNR2ramkgL6l%2BmuazHgVA0CBhgJnMI&winzoom=1")
					//.get();
	
		//hts.getrealurl("http://mp.weixin.qq.com/s?src=11&timestamp=1508039558&ver=453&signature=PpVoOwGFxxsGmeXAxBYVKc4wl6kLj66Ej2jgwO4eCPGzwqGpCtpQExNczZKBxivXTA0CJN859sztLBsQL*c2rzp4X6WlJ1sgZXwdBdInLKtDELkxxMNjSEwUmiPI1V5e&new=1");
	//	hts.getrealurl("http://mp.weixin.qq.com/s?src=3&timestamp=1502271441&ver=1&signature=kykJlzhwP9A77rhKV0AY*xJNz4mwGQVeswfakVAxREW7F5-7VIsjxSRX-dSaXDau6naY5Hk4qW9fS4gt-kPzaI0Whk2O-kyEX*in6cCUleXtox4yYf948ahsnIpNP2kQi6Esg-w5C50braVuOzFhKpqazJnPFHLntZVim5Mx7Lc=");
	//while(true)
	httpTest("615096127032126944641026","100","124.205.86.46",7008);
	}
public static String textpost(HttpClient client, String url, Header[] headers, List<NameValuePair>  jsonParams,  
            String reqCharset) {  
       /* if (StringUtils.isBlank(url) || client == null) {  
            throw new IllegalArgumentException("url and client can not be null.");  
        }  
        if (StringUtils.isBlank(reqCharset)) {  
            reqCharset = "utf-8";  
        }  */
        try {  
            RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);  
            String ttString="callCount=1\nc0-scriptName=NewsOperate\nc0-methodName=shareSingleNews\nc0-id=4583_1508840476802\nc0-param0=string:317598\nc0-param1=string:815088375336844154982426\nc0-param2=number:1\nc0-param3=string:2017-10-23 18:06:34\nc0-param4=string:2017-10-24 18:06:34\nxml=true";
            if (StringUtils.isNotBlank(ttString)) {  
                StringEntity entity = new StringEntity(ttString);  
              //  entity.setContentEncoding("UTF-8");  
                entity.setContentType("text/plain");  
                requestBuilder.setEntity(entity);  
            }  
         //   HttpEntity httpentity = new UrlEncodedFormEntity(jsonParams,"gbk");             
          // requestBuilder.setEntity(httpentity);  
            HttpUriRequest request = requestBuilder.build();  

            /*if (MapUtils.isNotEmpty(headers)) {  
                for (Map.Entry<String, String> entry : headers.entrySet()) {  
                    request.addHeader(entry.getKey(), entry.getValue());  
                }  
            }  */
            request.setHeaders(headers);
         String content="";
            HttpResponse response = client.execute(request);  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
            		response.getEntity().getContent(),"GBK")); 
            for(String s = reader.readLine(); s != null; s = reader.readLine()){  
                content += s;  
            }  
            System.out.println("ct:"+content);
            return "";
           /* try {  
                String html = EntityUtils.toString(response.getEntity());  
                return html;  
            } catch (IOException e) {  
              //  logger.error("", e);  
            } */ 
        } catch (ParseException e) {  
           // logger.error("", e);  
        } catch (IOException e) {  
           // logger.error("", e);  
        }  
        return null;  
    } 
public  static  String httpTest(String id,String uid,String ip,int port) throws Exception  {
	/*try {
		//Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
//String token = "R5amyr6NyXCtWdScmNiuvVwBCJztfByZDUGaE2V0NwOUheW4XYlvUusYkrViTYt584RgcyXRhjxAJZG3rFlPLg";
String url = "http://wyq.sina.com/dwr/exec/NewsOperate.shareSingleNews.dwr";
RequestConfig config = null;
HttpHost proxy = new HttpHost(ip, port); 
DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);  
System.out.println("ip:"+ip+"  "+port);
config = RequestConfig.custom().setProxy(proxy).build();
//String id="915088467197256042522326";
String json = "callCount=1\nc0-scriptName=NewsOperate\nc0-methodName=shareSingleNews\nc0-id=4583_1508840476802\nc0-param0=string:"+uid+"\nc0-param1=string:"+id+"\nc0-param2=number:1\nc0-param3=string:2017-10-23 18:06:34\nc0-param4=string:2017-10-24 18:06:34\nxml=true";
HttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner).build();
//HttpClient httpClient = new DefaultHttpClient();

HttpPost post = new HttpPost(url);
StringEntity postingString = new StringEntity(json);// json传递
post.setEntity(postingString);
post.setHeader("Content-type", "text/plain");
//post.setConfig(config);


HttpResponse response = httpClient.execute(post);
String content = EntityUtils.toString(response.getEntity());
// Log.i("test",content);
System.out.println(content);
String url2=getjson(content, "var s2=\"(.*)\";s0", 1);
System.out.println("url2:"+url2);
Document document=null;
String  expMessage = "";
String mess="";
if(url2.equals(""))
	return "";
try {
	 document=Jsoup.connect(url2).ignoreContentType(true).post();

} catch (Exception e) {
	e.printStackTrace();
	ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();

	e.printStackTrace(new java.io.PrintWriter(buf, true));
	 expMessage = buf.toString();

	buf.close();

	System.out.println(e.getMessage()+":::::"+id);
	mess=e.getMessage();
	//throws 
	// TODO: handle exception
}
if(document!=null)
{
if(document.location().indexOf("t.qq.com")!=-1)
	return document.location();
String url3=getjson(document.location(), "url=(.*)&domain=", 1);
String url4=java.net.URLDecoder.decode(url3);
System.out.println("url4:-----------"+url4);

System.out.println(url4);

return url4;
}
else if(mess.indexOf("timed")==-1)
{
	System.out.println(expMessage);
	String url5=getjson(expMessage,"URL=(http://weibo.com/[0-9]+/[a-zA-Z0-9]+)",1);
	if(url5.equals(""))
		url5=getjson(expMessage,"URL=(https://weibo.com/[0-9]+/[a-zA-Z0-9]+)",1);
	System.out.println("url5:=============================="+url5);
	return url5;
}
else {
	return "";
}
}
/*     */   public static String getjson(String str,String rega,int gr)
/*     */   {
/* 187 */     String result = "";
/* 188 */     String reg = rega;
/*     */ 
/* 190 */     Pattern pat = Pattern.compile(reg);
/* 191 */     Matcher mat = pat.matcher(str);
/* 192 */     boolean rs = mat.find();
/* 193 */     if (rs) {
/* 194 */       result = mat.group(gr);
//break;
/*     */     }
/* 196 */     return result;
/*     */   }




public static  Map<String, String> str2map(String ck)
{
	String[] cookiess=ck.split("; ");
	//String cck="acw_tc=AQAAALuzuxTbKAUAaYU1bgcO6WHfp7Xh; PHPSESSID=sapi7qcn468n8ojfsrf0ch8321; Hm_lvt_293b2731d4897253b117bb45d9bb7023=1489459247; Hm_lpvt_293b2731d4897253b117bb45d9bb7023=1489459254; bdshare_firstime=1489459254111";
	//String[] cookiess=cck.split("; ");
	//System.out.println(cookiess.length+"----"+ck);
	Map<String, String> cookie=new HashMap<String,String>();
	for(int  j=0;j<cookiess.length;j++)
				{
		try{
			cookie.put(cookiess[j].split("=")[0],cookiess[j].split("=")[1]);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				}
	return cookie;
}
public static String getrq(String url)
{
	//String url="http://weibo.com/6371094623/FrTl1vggN?type=comment#_rnd1508849369233";
	Document document=null;
	String ck="YF-Page-G0=f70469e0b5607cacf38b47457e34254f; SUB=_2AkMus7aqf8NxqwJRmPwczGjka4x-zw3EieKY70dxJRMxHRl-yT83qmYMtRBgZiz21KK9VyK3NPF4tJH4q5VhwA..; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9W5cV_fD2FxTM-bBsAbF4r5P; _s_tentry=-; Apache=8533950484500.75.1508849956024; SINAGLOBAL=8533950484500.75.1508849956024; ULV=1508849956043:1:1:1:8533950484500.75.1508849956024:; YF-Ugrow-G0=169004153682ef91866609488943c77f; YF-V5-G0=69afb7c26160eb8b724e8855d7b705c6; wb_cusLike_3655689037=N";
	Map map=str2map(ck);
	while(document==null)
		/*
		 * 
		 * 
		 * YF-Page-G0=f70469e0b5607cacf38b47457e34254f; SUB=_2AkMus7aqf8NxqwJRmPwczGjka4x-zw3EieKY70dxJRMxHRl-yT83qmYMtRBgZiz21KK9VyK3NPF4tJH4q5VhwA..; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9W5cV_fD2FxTM-bBsAbF4r5P; _s_tentry=-; Apache=8533950484500.75.1508849956024; SINAGLOBAL=8533950484500.75.1508849956024; ULV=1508849956043:1:1:1:8533950484500.75.1508849956024:; YF-Ugrow-G0=169004153682ef91866609488943c77f; YF-V5-G0=69afb7c26160eb8b724e8855d7b705c6; wb_cusLike_3655689037=N
		 */
	
	try {
		document=Jsoup.connect(url).cookies(map).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)").get();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		System.out.println(e.getMessage());
		
	}
	//System.out.println(document.outerHtml());
	String rqString=getjson(document.outerHtml(), "WB_from S_txt2.*?weibotime.*?title=..(.*?).. date", 1);
	System.out.println(rqString);
	return rqString;
	/*Elements eles=document.select("a");
	if(eles.size()==0)
	{
		System.out.println("rq:"+"");
		return  "";
	}
	else {
		String rq=eles.get(0).attr("title");
		System.out.println("rq:"+rq);
		return  rq;
	}*/
	
}






}
