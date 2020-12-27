package lps;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lps.entities.Canting;
import lps.entities.DazhongCaterFirm;
import lps.entities.ElmCaterFirm;
import lps.service.CantingService;
import lps.service.DishService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;

import com.cnxunao.crawler.weibo.mvc.model.Keyword;
import com.cnxunao.crawler.weibo.mvc.model.ProxyIP;
import com.cnxunao.crawler.weibo.mvc.service.KeywordService;
import com.cnxunao.crawler.weibo.mvc.service.ProxyIPService;
import com.cnxunao.crawler.weibo.mvc.service.impl.KeywordServiceImpl;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImpl;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImpl3;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImplckpool;




public class getkuaican implements Runnable {

	ProxyIPService ipService =null; 

	KeywordService keywordService =null; 
	Set<String>  tt=null;
	CantingService cts =null;
	Jedis queueManger = new Jedis("172.31.0.5",6379);
	 ProxyIPService ckpool =new ProxyIPServiceImplckpool(); 

	public getkuaican(ProxyIPService ips,KeywordService keys,Set<String>  tts) {
		ipService=ips;
		keywordService=keys;
		tt=tts;
		ApplicationContext context=ApplicationContextUtil.getContext();  
		cts = (CantingService) context.getBean("cantingService");

	}
	ProxyIP proxyIP;

	public  void run() {


int biaojiindex=0;

proxyIP = ipService.getProxyIP();
		while(true)

		{
			//int k=0;

			/*try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			//System.out.println("wu ip");
			//continue;

			Keyword keyword = null;

			keyword=keywordService.getKw();;
			if(keyword == null) {
				break;

			}
			biaojiindex+=1;
			for (int z = 1; z< 51; z++) {  

				//proxyIP = ipService.getProxyIP();
			/*	while (proxyIP==null) {
					try {
						Thread.sleep(10000);
						proxyIP = ipSeice.getProxyIP();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/

				String urlString=keyword.getWord()+"p"+z;



				String  renshuString="0";
				Document doc=null;


				Response response=null;
				String ck="_lxsdk_cuid=16087ae33e1c8-0806e87cdbffc1-5f19331c-15f900-16087ae33e2c8; _lxsdk=16087ae33e1c8-0806e87cdbffc1-5f19331c-15f900-16087ae33e2c8; _hc.v=e9ab8493-cab5-f645-bc98-f023cc66c56e.1514104829; s_ViewType=10; cy=2; cye=beijing; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_s=160972d2438-c92-a2b-185%7C%7C31";

				
				ProxyIP cks=ckpool.getProxyIP();
				
				ck=cks.getHost();
				

				String agent=ck.split("===")[1];
				ck=ck.split("===")[0];
				System.out.println("["+ck+"]");
				System.out.println("["+agent+"]");
				
				try {

					///System.out.println(urlString);
					
					/*while (proxyIP==null) {
						try {
							Thread.sleep(10000);
							proxyIP = ipService.getProxyIP();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}*/
					//System.out.println("成果获取IP:"+proxyIP.getHost());.proxy(proxyIP.getHost(), proxyIP.getPort())
					doc = Jsoup.connect(urlString).cookies(str2map(ck)).proxy(proxyIP.getHost(), proxyIP.getPort()).timeout(10000).header("Referer", "http://www.sojson.com").header("Host", "www.dianping.com").header("User-Agent", agent).get();


				} catch (IOException e) {
					z--;
					System.out.println(" fail   kw:"+keyword.getWord()+"  （线程扫描板块数）"+biaojiindex+"/(总店铺数)"+tt.size()+"  p:"+z+"/50"+e.getMessage() +" "+proxyIP.getHost()+" "+proxyIP.getPort());
					  int tag=0;
						
						 if(e.getMessage().indexOf("error")!=-1)
							
							 {
							 
							 
							  try {
									Jsoup.connect("http://www.dianping.com/").proxy(proxyIP.getHost(), proxyIP.getPort()).timeout(10000).header("User-Agent", agent).header("Host", "www.dianping.com").cookies(str2map(ck)).header("Referer", "http://www.dianping.com/shop/76882941/review_all").get();
												}
												catch(Exception ab)
												{
													if(ab.getMessage().indexOf("error")!=-1)
													tag=1;
													else
														tag=2;
												}
							 
							 
							              if(tag==1)
							              {
							            		 ckpool.changeProxyStatus(cks, 1);
							    				 ipService.changeProxyStatus(proxyIP, 5);
							    					proxyIP = ipService.getProxyIP();
							              }
							              else if(tag==2)
							              {
							            	  ckpool.changeProxyStatus(cks, 1);
							    				 ipService.changeProxyStatus(proxyIP, 4);
							    					proxyIP = ipService.getProxyIP();
							              }
							              else
							              {
							            	  ckpool.changeProxyStatus(cks,4);
							    				// ipService.changeProxyStatus(proxyIP, 1);
							              }
							 
							 
							 
							 
						
							 }
						
						 else
						 {
							
							 ckpool.changeProxyStatus(cks, 1);
							 ipService.changeProxyStatus(proxyIP, 4);
							 
								proxyIP = ipService.getProxyIP();
							 
							 
						 }
							
					//e.printStackTrace();
					continue;
				}
						ckpool.changeProxyStatus(cks, 1);
//ipService.changeProxyStatus(proxyIP, 1);

				//System.out.println(proxyIP.getHost()+" "+proxyIP.getPort());

			//	ipService.changeProxyStatus(proxyIP, 1);
				//String reString=response.body();

				//System.out.println("条数:"+doc.outerHtml());
				Elements ja=doc.select("div.shop-list.J_shop-list.shop-all-list ul li");

				System.out.println(" ok   kw:"+keyword.getWord()+"  "+ja.size()+"（本次抓取数）/"+tt.size()+"（总店铺数）  p:"+z+"/50"+"    "+biaojiindex+"（扫描板块数） "+proxyIP.getHost()+" "+proxyIP.getPort());

				if(ja.size()==0)
				{
					break;
				}
				List<DazhongCaterFirm> cantings=new ArrayList<DazhongCaterFirm>();

				for(int j=0;j<ja.size();j++)
				{

					String data=ja.get(j).select("div.txt div.tit a").attr("title");
					String id=ja.get(j).select("div.txt div.tit a").get(0).attr("href");
					String dizhi=ja.get(j).select("addr").text();
					
					String kouwei="0";
					String huanjing="0";
					String fuwu="0";
					try{
					 kouwei=ja.get(j).select("span.comment-list span b").get(0).text();
					 huanjing=ja.get(j).select("span.comment-list span b").get(1).text();
					 fuwu=ja.get(j).select("span.comment-list span b").get(2).text();
					}catch(Exception e){
						
						
						
					}
					
					String type=ja.get(j).select("a span.tag").get(0).text();
					String city=ja.get(j).select("a.city J-city span").text();
					String addr=ja.get(j).select("a span.addr").text();
					
					
					
					Elements cateid=ja.get(j).select(".comment-list span");	   
					String fenshu="";
					for(int i=0;i<cateid.size();i++)
					{
						fenshu+=cateid.get(i).text();	
					}



					String pinglunshu =ja.get(j).select("a.review-num b").text();
					String renjunxiaofei =ja.get(j).select("a.mean-price b").text();
					//mean-price
					String zonghekouwei="";
					String huangjing="";
					DazhongCaterFirm  ct=new DazhongCaterFirm ();

					if(ja.get(j).select("a.comment-list b").size()>0){ 
						zonghekouwei =ja.get(j).select("a.comment-list b").get(0).text();

						huangjing =ja.get(j).select("a.comment-list b").get(1).text();
						ct.setCoEnvironment(Float.parseFloat(huangjing));
						ct.setCoTasty(Float.parseFloat(zonghekouwei));
					}
					String xingji =ja.get(j).select(".sml-rank-stars").get(0).attr("class");
					xingji=xingji.substring(xingji.indexOf("sml-str")+7);
					String tese =ja.get(j).select("a.tag").text();



ct.setCatertype(type);
ct.setCity(city);
ct.setCaterAddress(addr);
				//	ct.setCreateTime(new Timestamp(System.currentTimeMillis()));
					ct.setCreateTime(Integer.parseInt((System.currentTimeMillis()/1000)+""));
					ct.setCaterAddress(dizhi);
					ct.setCaterName(data);
					ct.setKouwei(Float.parseFloat(kouwei));
					ct.setHuanjing(Float.parseFloat(huanjing));
					ct.setFuwu(Float.parseFloat(fuwu));
					if(renjunxiaofei.equals(""))
						renjunxiaofei="0";
					ct.setMeanCost(Integer.parseInt(renjunxiaofei.replace("￥", "")));
					//	ct.setDissScore(fenshu);
					if(pinglunshu.equals(""))
						pinglunshu="0";
					ct.setDissCount(Integer.parseInt(pinglunshu));
					ct.setStarLevel(Float.parseFloat(xingji));
					id=id.substring(id.lastIndexOf("/")+1);
					ct.setUuid(id);
					//System.out.println("条数:"+(k++));

					//else
					//{
					//cts.saveOrUpdate(ct);

					//}
						if(tt.add(ct.getUuid()))

					{
							
							
							List<DazhongCaterFirm> ecm=	cts.query("from DazhongCaterFirm where uuid =?", id);
		                    if(ecm.size()>0)
		                    	//continue;
		                    {  ct.setId(ecm.get(0).getId());
							  //cantings.add(ct);
		                   
		               //     cts.update(ct);
							}
		                   // else
		                   // {
		                   // //	cts.save(ct);
							//}
							
							
							//ct.setId(Integer.parseInt(id));
							cantings.add(ct);
					}


					

					///cantings.add(ct);
					//	System.out.println(nameString);
				}
				cts.saveOrUpdate(cantings);
				
				//System.out.println();
				//


			}
			
			keywordService.updateKw(keyword, 1);
		//	break;
		}

		  queueManger.set("dzdpdp", "0");


	}
	/*public static Map<String, String> str2map(String ck)
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
	}*/
	public static String getrenshu(String id)
	{











		String urlString="https://www.ele.me/restapi/ugc/v1/restaurant/"+id+"/rating_categories";

		String  renshuString="0";
		Response response;
		try {
			response = Jsoup.connect(urlString).ignoreContentType(true).execute();
			String reString=response.body();
			JSONArray ja=new JSONArray();

			ja=JSONArray.fromObject(reString);
			JSONObject jo=ja.getJSONObject(0);
			renshuString=jo.getString("amount");


		} catch (IOException e) {
			renshuString="890708";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return renshuString;

	}





	public static String getcaidan(String id)
	{

		String urlString="https://www.ele.me/restapi/shopping/v2/menu?restaurant_id="+id;

		String  renshuString="0";
		Response response;
		try {
			response = Jsoup.connect(urlString).ignoreContentType(true).execute();
			String reString=response.body();
			JSONArray ja=new JSONArray();

			ja=JSONArray.fromObject(reString);
			JSONObject jo=ja.getJSONObject(0);
			renshuString=jo.getString("amount");


		} catch (IOException e) {
			renshuString="890708";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return renshuString;

	}


	//ProxyIPService ipService =new ProxyIPServiceImpl(); 

	//static Jedis queueManger = new Jedis("172.31.0.7");

	public static void main(String[] args) throws Exception {
		int nThreads =10;

		//ScheduledExecutorService  service=Executors.newSingleThreadScheduledExecutor();    

		KeywordService keywordService =new KeywordServiceImpl(); 



		for (int j = 0; j >-1; j++) {
			
			Calendar cd=Calendar.getInstance();
			int  ho=cd.get(Calendar.HOUR_OF_DAY);
		//	if(ho!=15)
				
			//{
				
			//	continue;
			//}
			
			Set<String> set = new HashSet<String>(); // 非同步，非线程安全的Set
			Set<String> syncSet = Collections.synchronizedSet(set); // 返回了一个线程安全的Set


			ExecutorService pool = Executors
					.newFixedThreadPool(nThreads + 1);
			for (int i = 0; i < nThreads; i++) {
				ProxyIPServiceImpl ipService =new ProxyIPServiceImpl(); 

				pool.execute(new getkuaican(ipService,keywordService,syncSet));
				System.out.println("ok"+i);
			}
			//	

			System.out.println("================="+j);
			pool.shutdown();
			pool.awaitTermination(200, TimeUnit.HOURS);
			// rm.finish();
			
			//break;
			System.out.println(".. 程序休息..SLEEPING...120S...."+new Date());
			Thread.sleep(60000);
		}
	}

	public static  Map<String,String> cookie=new HashMap<String, String>();
	public static Map<String, String> str2map(String ck)
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
}
