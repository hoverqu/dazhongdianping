package com.cnxunao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;





//import com.sun.j3d.utils.universe.ConfigContainer;

import sun.security.krb5.Config;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class analysisquestion {

	//config cfg=new config();
	static int count=0;
	public static String getvideolist(String url,String ip,int port,String cookie)
	{
		/*
		
Accept-Encoding:gzip, deflate
Accept-Language:zh-CN,zh;q=0.8
Cookie:UM_distinctid=15d77731528df-020398d1bd6273-8383667-100200-15d77731529271; bdshare_firstime=1500947617344; PHPSESSID=meiuc116k448458uqihl1jqcj4; CNZZDATA764844=cnzz_eid%3D523158825-1500944520-null%26ntime%3D1501209370
Host:www.flvcd.com
Proxy-Authorization:Basic ZnRwdXNlcjp4dW5hb3Jvb3Q=
Proxy-Connection:keep-alive
Upgrade-Insecure-Requests:1
User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36

		 
		 */
		//从硕鼠获取video url list
		Document videoDocument=null;
		int aa=0;
		while(videoDocument==null)
		{
			System.out.println("getting video..");

			aa++;
			if(aa>=3)
				return "fre";
			try {
				//.proxy(ip, port)
				//System.out.println(config.cookie);
				videoDocument=Jsoup.connect("http://www.flvcd.com/parse.php?format=")
						.header("Accept-Encoding", "gzip, deflate")
						.header("Accept-Language", "zh-CN,zh;q=0.8")
						.header("Host", "www.flvcd.com")
						.proxy(ip, port)
						.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36")
						.cookies(config.cookie).data("kw",url).timeout(20000).get();
				System.out.println("成功解析文章数:"+count);
				count++;
				
			} catch (IOException e) {
				System.out.println("成功解析文章数:"+count);
				//e.printStackTrace();
				try {
					System.out.println(Thread.currentThread().getId()+"_"+ip+"_-------"+"硕鼠服务器异常，稍后重试。。。"+e.getMessage()+url);
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		/*try {
			videoDocument=Jsoup.connect("http://www.flvcd.com/parse.php?format=").data("kw",url).timeout(0).get();
		} catch (IOException e) {
			e.printStackTrace();
			if(e.getMessage().indexOf("timed")!=-1)
				{
				try {
				
					videoDocument=Jsoup.connect("http://www.flvcd.com/parse.php?format=").data("kw",url).timeout(0).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return "";
				}
		          }
		}*/
	
			// TODO Auto-generated catch block
		
			
		
		//System.out.println(videoDocument.outerHtml());
		String videolist="";
		 Elements elements=videoDocument.select("tbody tr:eq(0) td a");
		 for(int i=0;i<elements.size();i++)
		 {
			 if(i!=elements.size()-1)
				 videolist+=elements.get(i).attr("href")+",";
			 else {
				videolist+=elements.get(i).attr("href");
			}
		 }
		//System.out.println(videolist);
		return videolist;
		
		
	}
	public static String getvideolist2(String url,String ip,int port)
	{
		/*
		
Accept-Encoding:gzip, deflate
Accept-Language:zh-CN,zh;q=0.8
Cookie:UM_distinctid=15d77731528df-020398d1bd6273-8383667-100200-15d77731529271; bdshare_firstime=1500947617344; PHPSESSID=meiuc116k448458uqihl1jqcj4; CNZZDATA764844=cnzz_eid%3D523158825-1500944520-null%26ntime%3D1501209370
Host:www.flvcd.com
Proxy-Authorization:Basic ZnRwdXNlcjp4dW5hb3Jvb3Q=
Proxy-Connection:keep-alive
Upgrade-Insecure-Requests:1
User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36

		 
		 */
		//从硕鼠获取video url list
		Document videoDocument=null;
		int aa=0;
		while(videoDocument==null)
		{
			//System.out.println("getting video..");

			aa++;
			if(aa>=4)
				return "fre";
			try {
				//.proxy(ip, port)
			//	System.out.println(config.cookie);
				if(!ip.equals("localhost"))
				videoDocument=Jsoup.connect("http://api.v2.flvurl.cn/parse/?appid=6170b6db0a881c18389f47d6d994340e&type=vod").proxy(ip, port).data("url",url).timeout(20000).ignoreContentType(true).get();
				else {
					videoDocument=Jsoup.connect("http://api.v2.flvurl.cn/parse/?appid=6170b6db0a881c18389f47d6d994340e&type=vod").data("url",url).timeout(20000).ignoreContentType(true).get();

				}
			} catch (IOException e) {
				e.printStackTrace();
				try {
					System.out.println(Thread.currentThread().getId()+"_"+ip+"_-------"+"点量视频服务器异常，15s后重试。。。"+e.getMessage()+url);
					Thread.sleep(15000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		String videolist="";
		String reString=videoDocument.body().text();
		JSONArray elements=JSONObject.fromObject(reString).getJSONObject("data").getJSONArray("streams").getJSONObject(1).getJSONArray("segs");
		 for(int i=0;i<elements.size();i++)
		 {
			 if(i!=elements.size()-1)
				 videolist+=elements.getJSONObject(i).getString("url")+",";
			 else {
				videolist+=elements.getJSONObject(i).getString("url");
			}
		 }
		//System.out.println(videolist);
		return videolist;
		
		
	}
	public JSONArray analysisjson(Document doc,String td,String topic){
		
		// String url = doc.select((String) config.weixinhaomoban.getJSONArray("url").get(0)).attr((String) config.weixinhaomoban.getJSONArray("url").get(1));
		 JSONArray ja=new JSONArray();
				String data=doc.body().text();
				JSONObject joJsonObject=JSONObject.fromObject(data);
				JSONArray items=joJsonObject.getJSONArray("data");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd");

				String ee="160101 000000";
				String ee2="";
				try{
					//dt=new Date(System.currentTimeMillis());
					Date dt2=new Date();
				 sdf=new SimpleDateFormat("yyMMdd HHmmss");
					ee2=sdf.format(dt2);
				}
				catch(Exception e)
						{}
				      for(int i=0;i<items.size();i++)
				      {
				    	  JSONObject reply=new JSONObject();
				    	  
				    	  String au2=items.getJSONObject(i).getJSONObject("author").getString("name");
				    	  String auid=items.getJSONObject(i).getJSONObject("author").getString("url_token");

				    	  String title=items.getJSONObject(i).getJSONObject("question").getString("title");
				    	  String count=items.getJSONObject(i).getString("thanks_count");

				    	  String rq2=items.getJSONObject(i).getString("updated_time")+"000";
				    	  Long rq3=Long.parseLong(rq2);
				    	  long yestorday=System.currentTimeMillis()-3600*24*1000l;
				    	  if(rq3<yestorday)
				    		  break;
				    	  //知乎问题无时间，只能如此处理。否则无法排重
				    	  String ht2="";
				    	  if(items.getJSONObject(i).containsKey("content") )
				    	  {
				    		ht2=items.getJSONObject(i).getString("content");
				    	  }
				    	  else {
				    		  if(items.getJSONObject(i).containsKey("excerpt") )
				    		  ht2=items.getJSONObject(i).getString("excerpt");
				    		
						}
				    	  String href="https://www.zhihu.com/question/"+td+"/answer/"+items.getJSONObject(i).getString("id");

				    	  reply.put("author", au2);
				    	  reply.put("authorid", auid);

				    	  try{
								//dt=new Date(System.currentTimeMillis());
								Date dt3=new Date(rq3);
							// sdf=new SimpleDateFormat("yyMMdd HHmmss");
								ee=sdf.format(dt3);
							}
							catch(Exception e)
									{}
				    	  reply.put("date", ee);
				    	  reply.put("title", title);
				    	  reply.put("date2", ee2);
				    	  reply.put("mt", "RP");
				    	  reply.put("content", ht2);
				    	  reply.put("url",href);
				    	  reply.put("td",td);
				    	  reply.put("readcount","");
				          reply.put("dianzan",count);
		                   
				    	  ja.add(reply);
				      }
				   //   System.out.println(ja.toString());
				      return ja;
				
			}
	
	
	public static void main(String[] args) throws Exception {
		
	//	Document doc=Jsoup.connect("https://www.zhihu.com/api/v4/questions/50075869/answers?offset=0&limit=20&sort_by=created").header("authorization","oauth c3cef7c66a1843f8b3a9e6a1e3160e20" ).ignoreContentType(true).get();
		//System.out.println(doc.body().text());
		analysisquestion analysisquestion=new analysisquestion();
		Document doc=Jsoup.connect("https://www.zhihu.com/question/24658190").get();
		//analysisquestion.getvideolist("http://tv.cctv.com/2017/07/25/VIDE8dvNXJCNwYKLPdf7b4Aq170725.shtml","ip",33);
	}	
	
	
	
	
	
	
}
