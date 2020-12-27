package lps.summery2;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lps.ApplicationContextUtil;
import lps.entities.DazhongCaterDissSummary;
import lps.entities.DazhongCaterDisscus;
import lps.entities.DazhongCaterFirm;
import lps.entities.DazhongCaterPromotion;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterDisscus;
import lps.entities.ElmCaterFirm;
import lps.service.CantingService;
import lps.service.DishService;
import lps.service.DishpinglunService;
import lps.service.DishsummeryService;
import lps.service.DisscussService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;

import redis.clients.jedis.Jedis;

import com.cnxunao.crawler.weibo.mvc.model.ProxyIP;
import com.cnxunao.crawler.weibo.mvc.service.ProxyIPService;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImpl;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImpl3;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImplckpool;
import com.cnxunao.crawler.weibo.util.luanma;

public class getcaidan implements Runnable {

	// ProxyIPServiceImpl ipService =null; 
	 Logger log = Logger.getLogger(getcaidan.class);

	ProxyIPService ipService =null; 
	List<String> dianpuids=new ArrayList<String>();
	List<Long> dianpuids2=new ArrayList<Long>();

	ApplicationContext context=ApplicationContextUtil.getContext();  
	 Jedis queueManger = new Jedis("172.31.0.5",6379);

	CantingService cts = (CantingService) context.getBean("cantingService");
	DishsummeryService summer = (DishsummeryService) context.getBean("dishsummeryService");

	DishpinglunService cxs = (DishpinglunService) context.getBean("dishpinglunService");

	 //ProxyIPService ckpool =new ProxyIPServiceImplckpool(); 

	
	public getcaidan(ProxyIPService ips,List<DazhongCaterFirm> dzs) {
		ipService=ips;



 for(int j=0;j<dzs.size();j++)
 {
	 dianpuids.add(dzs.get(j).getUuid());
	 dianpuids2.add(dzs.get(j).getId());

 }


	}
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

	public  void run()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
   	 ProxyIP proxyIP;

		ApplicationContext context=ApplicationContextUtil.getContext();  
	//	DisscussService ds = (DisscussService) context.getBean("disscussService");
	//	ds.delete("from DazhongCaterFirm where uuid=?",id);

		
		for(int z=0;z<dianpuids.size();z++)
		{
			//int page=1;
		//while(true)
		//{
			//System.out.println("page:"+page);
		String urlString="http://www.dianping.com/ajax/json/shopDynamic/promoInfo?shopId="+dianpuids.get(z)+"&cityId=2&shopName=%E8%8A%A6%E6%9C%88%E8%BD%A9%E7%BE%8A%E8%9D%8E%E5%AD%90&power=5&mainCategoryId=1805&shopType=10&_token=die2122gryuehugehfyhdsfgdsfhyufsfefeuehuedufiue";
		//http://www.dianping.com/ajax/json/shopDynamic/promoInfo?shopId="+dianpuids.get(z)+"&cityId=2&shopName=%E8%8A%A6%E6%9C%88%E8%BD%A9%E7%BE%8A%E8%9D%8E%E5%AD%90&power=5&mainCategoryId=1805&shopType=10&_token=die2122gryuehugehfyhdsfgdsfhyufsfefeuehued
		//"http://www.dianping.com/ajax/json/shopDynamic/promoInfo?shopId="+dianpuids.get(z)+"6&cityId=2&shopName=%E6%B6%A6%E7%A6%A7%E9%BA%BB%E8%BE%A3%E9%A6%99%E9%94%85&power=5&mainCategoryId=4469&shopType=10&_token=eJx1kFtPwkAQhf%2FLJj7RsLOzl7YkxihKhFAMglzjQykFqpSWtlK2xv%2FuNuKDD2YmOd%2FMnIeT%2BSRZd01aDAAEs8gpzEiLsCY0FbFIkZuLZEKgI4Tk3LFI8HcnmLLIKpvck9ZSSduylfNaL57NvGQKwHIBXq0fdJRBFKZrT9dYyK4o0halZVk215F%2FSKPDthkkMc13SUqlo1yb1UH%2BtYV%2BFuxo4BfhNsk0RQo0QwbuWgKkeONH62u0hUQE5wrb0ojNXUMuuigZ8BplXSgNIkcXHGaIo0IOipig8dgENfp%2BUf%2Bixe%2FsmY%2BZjHm0PRgKe%2BfxKBf5cfPs5ePJSAP3qsfBU%2F9hP6i0024vonnciY%2BdcDRLz6tFfEwmi8ZwdutNvH5Q5tWQD8qOpufB%2BQVxs9q%2FsU1xqvZZASc3Yo2PaRVrHRfzfjzUSe9lCt59b3g36%2BmZJl%2FfUg6EpA%3D%3D&uuid=833c1f8a-0b94-169a-4207-80e12e11d520.1514428430&platform=1&partner=150&originUrl=http%3A%2F%2Fwww.dianping.com%2Fshop%"+dianpuids.get(z)
		String  renshuString="0";
		Document doc=null;
		Response response;
	//	proxyIP = ipService.getProxyIP();
      
	/*	while (proxyIP==null) {
			try {
				Thread.sleep(10000);
				proxyIP = ipService.getProxyIP();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		//System.out.println("成果获取IP:"+proxyIP.getHost());
		
	//String ck="cy=94; _lxsdk_cuid=160a09dd93ec8-0fb469115af973-454f032b-15f900-160a09dd93ec8;_lxsdk=160a09dd93ec8-0fb469115af973-454f032b-15f900-160a09dd93ec8; _hc.v=b4b27788-412e-b347-f510-9eb67eb03af4.1514523188; _lxsdk_s=160a09dd941-710-356-1a2%7C%7C8; cye=nantong; ";

		
		//ProxyIP cks=ckpool.getProxyIP();
		
		//ck=cks.getHost();
		

		//String agent=ck.split("===")[1];
		//ck=ck.split("===")[0];
		//System.out.println("["+ck+"]");
		//System.out.println("["+agent+"]");
		
		try{
			
			//.proxy(proxyIP.getHost(), proxyIP.getPort())
		//	Thread.sleep(10000);
			 response=	Jsoup.connect(urlString).header("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").header("Host", "www.dianping.com").header("Referer", "http://www.dianping.com/shop/94306392/review_all").ignoreContentType(true).execute();
               // Thread.sleep(5000);
			//doc = Jsoup.connect(urlString).proxy(proxyIP.getHost(), proxyIP.getPort()).timeout(10000).header("Referer", "http://www.sojson.com").header("Host", "www.dianping.com").header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36").get();
		}catch(Exception ee)
		{
			
			//ee.printStackTrace();
			System.out.println("url:"+urlString+";"+ee.getMessage());
			// System.out.println("ip	"+proxyIP.getHost()+":"+proxyIP.getPort()+"	FAIL	"+sdf.format(new Date())+" "+ee.getMessage() +" page:"+" index:"+z+"/"+dianpuids.size());
			  log.info("ip	"+"	FAIL	"+sdf.format(new Date())+" "+ee.getMessage() +" page:"+" index:"+z+"/"+dianpuids.size());
         z--;
			//ee.printStackTrace();
			//page--;
         if(ee.getMessage().indexOf("error")!=-1)
				
		 {
		// ckpool.changeProxyStatus(cks, 4);
		// ipService.changeProxyStatus(proxyIP, 1);
		 }
	 else
	 {
		// ckpool.changeProxyStatus(cks, 1);
		// ipService.changeProxyStatus(proxyIP, 4);
	 }
			// TODO Auto-generated catch block
			//System.out.println(proxyIP.getHost()+"  ----> error:"+e.getMessage());
			//ee.printStackTrace();
			continue;
		}

		//ckpool.changeProxyStatus(cks, 1);
		//ipService.changeProxyStatus(proxyIP, 1);
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(urlString);
		//System.out.println(doc.outerHtml());
		
		//response = Jsoup.connect(urlString).ignoreContentType(true).execute();
		String reString=response.body();
	//	JSONArray ja=new JSONArray();
   JSONObject  re=JSONObject.fromObject(reString);
   
		//JSONObject joJsonObject=re.getJSONObject("msg");
	
		//Elements ja=doc.select("div.comment-list ul li[data-id]");		
		// System.out.println(ja.size()+"   ip	"+proxyIP.getHost()+":"+proxyIP.getPort()+"	OK	"+sdf.format(new Date()) +" page:"+" index:"+z+"/"+dianpuids.size());
    JSONArray big=re.getJSONArray("dealDetails");
    JSONArray small=re.getJSONArray("dealMoreDetails");
    JSONArray huodong=re.getJSONArray("promoList");
//proxyIP.getHost()+":"+proxyIP.getPort()
		  log.info("   ip	"+"	OK	"+sdf.format(new Date()) +" page:"+" index:"+z+"/"+dianpuids.size());

cxs.delete(cxs.query("from DazhongCaterPromotion where caterid=?"  ,Long.parseLong( dianpuids.get(z))));
			
   for(int i=0;i<big.size();i++)
   {
	   
	   
	   DazhongCaterPromotion cx=new DazhongCaterPromotion();
		
		String name="";
		String pic="";
		String price="";
		String oldprice="";
		String yisou="";
		String tag="团";
		String id="";
		
	name=big.getJSONObject(i).getString("productTitle");
	pic=big.getJSONObject(i).getString("imageUrl");
	price=big.getJSONObject(i).getString("price");
	oldprice=big.getJSONObject(i).getString("marketPrice");
	yisou=big.getJSONObject(i).getJSONObject("realTimeInfoDTO").getString("currentJoin");
	//tag="";
	/*try {
		tag=big.getJSONObject(i).getString("tag");
	}catch(Exception ee){
		
	}*/
	//tag=big.getJSONObject(i).getString("tag");
	id=big.getJSONObject(i).getString("id");
	/*int index0=id.lastIndexOf("/");
	id=id.substring(index0+1);*/
	/*if(tag.equals("null"))
		tag="团";
	else
		tag="促";*/
	 cx.setPromotionname(name);
	 cx.setPromotionpicurl(pic);
	 cx.setNowPrice(Float.parseFloat(price));
	 cx.setOldPrice(Float.parseFloat(oldprice));
		cx.setSaleCount(Integer.parseInt(yisou));
		cx.setPromoUuid(Long.parseLong(id));

		cx.setTag(tag);
		  cx.setCaterid(Long.parseLong((dianpuids.get(z)))); 
		cxs.save(cx);
	 // cx.setCaterid(Long.parseLong((dianpuids.get(z)))); 
	   
   }
	
   for(int i=0;i<small.size();i++)
   {
	   DazhongCaterPromotion cx=new DazhongCaterPromotion();
		
		String name="";
		String pic="";
		String price="";
		String oldprice="";
		String yisou="0";
		String tag="团";
		String id="";
		
	name=small.getJSONObject(i).getString("desc");
	//pic=small.getJSONObject(i).getString("imageUrl");
	price=small.getJSONObject(i).getString("price");
	oldprice=small.getJSONObject(i).getString("marketPrice");
	//yisou=small.getJSONObject(i).getJSONObject("realTimeInfoDTO").getString("currentJoin");
	/*try{
		tag=small.getJSONObject(i).getString("tag");
	}
	catch(Exception ee)
	{
		
	}*/
	id=small.getJSONObject(i).getString("href");
	int index0=id.lastIndexOf("/");
	id=id.substring(index0+1);
	/*if(tag.equals("null"))
		tag="团";
	else
		tag="促";*/
	 cx.setPromotionname(name);
	 cx.setPromotionpicurl(pic);
	 cx.setNowPrice(Float.parseFloat(price));
	 cx.setOldPrice(Float.parseFloat(oldprice));
		cx.setSaleCount(Integer.parseInt(yisou));
		cx.setPromoUuid(Long.parseLong(id));
		
		cx.setTag(tag);
		  cx.setCaterid(Long.parseLong((dianpuids.get(z)))); 
		
		
		
		
		cxs.save(cx);
   }
   for(int i=0;i<huodong.size();i++)
   {
	   DazhongCaterPromotion cx=new DazhongCaterPromotion();
		
		String name="";
		String pic="";
		String price="";
		String oldprice="";
		String yisou="0";
		String tag="促";
		String id="";
		
	name=huodong.getJSONObject(i).getString("desc");
	//pic=huodong.getJSONObject(i).getString("imageUrl");
	price=huodong.getJSONObject(i).getString("price");
	oldprice=huodong.getJSONObject(i).getString("marketPrice");
	//yisou=huodong.getJSONObject(i).getJSONObject("realTimeInfoDTO").getString("currentJoin");
	/*tag="";
	try{
		tag=huodong.getJSONObject(i).getString("tag");
	}catch(Exception ee)
	{
		
	}*/
	
	id=huodong.getJSONObject(i).getJSONObject("extInfo").getString("launchId");
	/*if(tag.equals("null"))
		tag="团";
	else
		tag="促";*/
	 cx.setPromotionname(name);
	 cx.setPromotionpicurl(pic);
	 if(price.equals("null"))
		 price="0";
	 if(oldprice.equals("null"))
		 oldprice="0";
	 cx.setNowPrice(Float.parseFloat(price));
	 cx.setOldPrice(Float.parseFloat(oldprice));
		cx.setSaleCount(Integer.parseInt(yisou));
		cx.setPromoUuid(Long.parseLong(id));
		  cx.setCaterid(Long.parseLong((dianpuids.get(z)))); 
		cx.setTag(tag);
		
		cxs.save(cx);
   }
		
	//	page++;
		//}
		
//DazhongCaterFirm firm=cts.get(Integer.parseInt(dianpuids2.get(z)+""));
DazhongCaterFirm firm=cts.queryUnique("from DazhongCaterFirm where id=?", dianpuids2.get(z));
if(firm!=null)
{
		firm.setOk4(1);
		//firm.set
		cts.update(firm);
}
		
		
		
		}
		  queueManger.set("dzdpdp", "0");
		// return renshuString;
		
	}
	
	public static void updatecanting()
	{
		 ApplicationContext context=ApplicationContextUtil.getContext();  
			CantingService cts = (CantingService) context.getBean("cantingService");
		
			
			//int i=0;
			while(true){
				
				
				Calendar cd=Calendar.getInstance();
				
				
				/*int  ho=cd.get(Calendar.HOUR_OF_DAY);
				if(ho!=0)
					
				{
					System.out.println(".. 程序休息..SLEEPING...120S...."+new Date());
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				*/
				
				
				
				
				cd.set(Calendar.HOUR_OF_DAY, 0);
				cd.set(Calendar.MINUTE, 0);

				cd.set(Calendar.SECOND, 0);
				cd.add(Calendar.DATE, -1);
				Set<String>  dps=new HashSet<String>();
			
             int z=1;
         
			while(true)
			{
				//System.out.println("程序扫描到:"+i*1000);
			//List<DazhongCaterFirm> cantings2=cts.query(z,1000,"from DazhongCaterFirm where createTime>"+Integer.parseInt((cd.getTimeInMillis()/1000)+"")+" and ( ok is null or ok=0)");
			List<DazhongCaterFirm> cantings2=cts.query(z,1000,"from DazhongCaterFirm where  ( ok4 is null or ok4=0)");

			
			if(cantings2.size()==0)
				break;
			List<DazhongCaterFirm> cantings=new ArrayList<>();

			for (int k = 0; k < cantings2.size(); k++) {
				if(dps.add(cantings2.get(k).getUuid()))
					cantings.add(cantings2.get(k));
				
			}
		
			
			if(cantings.size()==0)
			{
				z++;
				continue;
			}
			
	int nThreads = 10;
	
			
			int avg=cantings.size()/nThreads;
			 
			if(avg==0)
			{
				nThreads=cantings.size();
				avg=1;
			}
			//for (int j = 0; j >-1; j++) {
				//Set<String> set = new HashSet<String>(); // 非同步，非线程安全的Set
				//Set<String> syncSet = Collections.synchronizedSet(set); // 返回了一个线程安全的Set
			
		 	

			ExecutorService pool = Executors
					.newFixedThreadPool(nThreads );
			for (int i = 0; i < nThreads; i++) {
				ProxyIPServiceImpl ipService =new ProxyIPServiceImpl(); 

			//	ProxyIPServiceImpl2 keywordService =new ProxyIPServiceImpl2(); 
				int fromIndex = i * avg;
				int toIndex = i == nThreads - 1 ? cantings.size() : fromIndex + avg;
				System.out.println("test://fromindex-toindex/"+fromIndex+"-"+toIndex);
			pool.execute(new getcaidan(ipService,cantings.subList(fromIndex, toIndex)));
				System.out.println("ok"+i);
			//	Thread.sleep(10000);
			}
		
			

			
			
			pool.shutdown();
			try {
				pool.awaitTermination(200, TimeUnit.HOURS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		/*	if(cantings.size()==0)
				break;
			for(int k=0;k<cantings.size();k++)
			{
				getpinglun(cantings.get(k).getUuid());
			}*/
			z++;
			
			}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*try {
				System.out.println("程序休息一小时。。。。");
				Thread.sleep(3600000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			}

	}
	
	public static void main(String[]args) 
	{
		/// 122.194.214.202:4357
 		              updatecanting();
	//	String urlString="http://www.dianping.com/ajax/json/shopfood/wizard/getMixReviewListFPAjax?act=getreviewfilters&shopId=6232395&tab=all&order=all&_token=eJxtkttO20AQht9lr1fxzuw5EhcGcmHHaaPaCaoQFzQgiIAkSlzcBPHuzGyIBVIlS%2F7m8P87O9o3sS3uxBCUUgakeL3fiqGAgRo4IUW7o4oFA9GqGLzxUiy%2B5CB49FaKP9v5pRhem2ClDf6GE78ovgbtnPQu3MhPREI09HFPQS3isW03wyzrum5wt7xdbZarh8Fi%2FZLtHtebzKFGHS0NIkjw0rDAewkYAuW%2BUDhRUD3pnkxPrif%2FH4pMfO0ITIYpuSCT6ckyKSZWeNaCYmvPEgBGl7LIgzluBc29VjNaHtL4hNxg2BUc2%2Bok83y%2BTmbpkjo5pHtgyqahMcnSDMCIwDKVEAkh8mmoGYPmDT7xBul%2Fe9okXVxW%2BfmoOi6Cw8tingxQIlmfiqA5tn2M9FYwOllP8x8Uah8pxL6slf3mzbMj7TJnaVTfe11I1qmWfI4M8XhEzpO3p8kn9DqpuFs%2BrIjuy39N%2FdT93c1gWtXP42rRTqpZVh0unsuq3lzt93Fc17hvFodyVr%2F%2B3F9kXVONYHIocLL9reZ1WzZ5UY67UT49Xzd1fnYm3j8ALMew%2Fg%3D%3D&uuid=68fc843d-e9f3-410c-0478-6c714edcd59a.1513827481&platform=1&partner=150&originUrl=http%3A%2F%2Fwww.dianping.com%2Fshop%2F6232395";

		/*try {
			Document doc=Jsoup.connect(urlString).cookies(str2map("_lxsdk_cuid=1607ee321e1c8-021772358c357e-5f19331c-100200-1607ee321e1c8; _lxsdk=1607ee321e1c8-021772358c357e-5f19331c-100200-1607ee321e1c8; _hc.v=3e691410-adbb-49b1-c427-81d9cf9636f2.1513957303; cy=2; cye=beijing; s_ViewType=10; _lxsdk_s=16087b6b67c-11a-4b6-872%7C%7C42")).timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").header("Host", "www.dianping.com").header("Referer", "http://www.dianping.com/shop/66569336").ignoreContentType(true).get();
			System.out.println(doc.outerHtml());
		
		} catch (IOException e) {
			System.out.println("程序休息一小时。。。。"+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}

}
