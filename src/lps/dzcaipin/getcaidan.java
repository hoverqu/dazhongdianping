package lps.dzcaipin;

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
import lps.entities.DazhongCaterDisscus;
import lps.entities.DazhongCaterFirm;
import lps.entities.DazhongDishRecommend;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterDisscus;
import lps.entities.ElmCaterFirm;
import lps.service.CantingService;
import lps.service.DishService;
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
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImplckpool;
import com.cnxunao.crawler.weibo.mvc.service.impl.ProxyIPServiceImplcp;
import com.cnxunao.crawler.weibo.util.luanma;

public class getcaidan implements Runnable {

	// ProxyIPServiceImpl ipService =null; 
	 Logger log = Logger.getLogger(getcaidan.class);

	ProxyIPService ipService =null; 
	List<String> dianpuids=new ArrayList<String>();
	List<Long> dianpuids2=new ArrayList<Long>();

	ApplicationContext context=ApplicationContextUtil.getContext();  
	 Jedis queueManger = new Jedis("172.31.0.21",6380);

	CantingService cts = (CantingService) context.getBean("cantingService");
	 ProxyIPService ckpool =new ProxyIPServiceImplckpool(); 

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
		DishService ds = (DishService) context.getBean("dishService");
	//	ds.delete("from DazhongCaterFirm where uuid=?",id);

	/*	proxyIP = ipService.getProxyIP();
	      
		while (proxyIP==null) {
			try {
				Thread.sleep(10000);
				proxyIP = ipService.getProxyIP();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		for(int z=0;z<dianpuids.size();z++)
		{
			int page=1;
	while(true){
		
	
	
			System.out.println("page:"+page);
		String urlString="http://www.dianping.com/shop/"+dianpuids.get(z)+"/dishlist"+"/p"+page;
		System.out.println(urlString);
		//http://www.dianping.com/shop/90498178/dishlist
		String  renshuString="0";
		Document doc=null;
		Response response;
	
		//System.out.println("成果获取IP:"+proxyIP.getHost());
String ck="_lxsdk_cuid=16087ae33e1c8-0806e87cdbffc1-5f19331c-15f900-16087ae33e2c8; _lxsdk=16087ae33e1c8-0806e87cdbffc1-5f19331c-15f900-16087ae33e2c8; _hc.v=e9ab8493-cab5-f645-bc98-f023cc66c56e.1514104829; s_ViewType=10; cy=2; cye=beijing; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_s=160972d2438-c92-a2b-185%7C%7C31";

		
	/*	ProxyIP cks=ckpool.getProxyIP();
		
		ck=cks.getHost();
		

		String agent=ck.split("===")[1];
		ck=ck.split("===")[0];
		System.out.println("["+ck+"]");
		System.out.println("["+agent+"]");*/
		
		
		try{
			
			//.proxy(proxyIP.getHost(), proxyIP.getPort())
			// doc=	Jsoup.connect("http://www.dianping.com/shop/90498178/review_all").timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").header("Host", "www.dianping.com").cookies(str2map(ck)).header("Referer", "http://www.dianping.com/shop/27452208").get();
			//doc=Jsoup.connect("http://www.dianping.com/shop/90498178/review_all").timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").header("Host", "www.dianping.com").cookies(str2map(ck)).header("Referer", "http://www.dianping.com/shop/27452208").get();
			doc=Jsoup.connect(urlString).timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").header("Host", "www.dianping.com").cookies(str2map(ck)).header("Referer", "http://www.dianping.com/shop/57962644").get();
//.proxy(proxyIP.getHost(), proxyIP.getPort())
			//doc = Jsoup.connect(urlString).proxy(proxyIP.getHost(), proxyIP.getPort()).timeout(10000).header("Referer", "http://www.sojson.com").header("Host", "www.dianping.com").header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36").get();
		}catch(Exception ee)
		{
			
			//ee.printStackTrace();
			// System.out.println("ip	"+proxyIP.getHost()+":"+proxyIP.getPort()+"	FAIL	"+sdf.format(new Date())+" "+ee.getMessage() +" page:"+page+" index:"+z+"/"+dianpuids.size());
			  log.info("ip	"+ck+"	FAIL	"+sdf.format(new Date())+" "+ee.getMessage() +" page:"+page+" index:"+z+"/"+dianpuids.size());

			//ee.printStackTrace();
			//page--;
			  z--;
			 // if(ee.)
			/*  int tag=0;
				
				 if(ee.getMessage().indexOf("error")!=-1)
					
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
					 
					 
				 }*/
					
			//e.printStackTrace();
			continue;
		}
		//System.out.println(doc.outerHtml());

		// ckpool.changeProxyStatus(cks, 1);

		//System.out.println(urlString);
		//System.out.println(doc.outerHtml());
		
		//response = Jsoup.connect(urlString).ignoreContentType(true).execute();
		//String reString=response.body();
		//JSONArray ja=new JSONArray();

		//ja=JSONArray.fromObject(reString);
	
		//Elements ja=doc.select("ul.shop-food-item a.item");
		//Elements =doc.select("ul.recommend-photo clearfix li.item");   +proxyIP.getHost()+":"+proxyIP.getPort()    +proxyIP.getHost()+":"+proxyIP.getPort()
		Elements ja =doc.select("ul a.shop-food-item");
		 System.out.println(ja.size()+"   ip	"+"	OK	"+sdf.format(new Date()) +" page:"+page+" index:"+z+"/"+dianpuids.size());

		  log.info(ja.size()+"   ip	"+"	OK	"+sdf.format(new Date()) +" page:"+page+" index:"+z+"/"+dianpuids.size());

		/*if(ja.size()==0)
		{
			ja=doc.select("div.reviews-items ul li");
		}*/

	   if(ja.size()==0)
	     	break;
		System.out.println("size:"+ja.size());
		for(int j=0;j<ja.size();j++)
		{
			
			String data=ja.get(j).select("div.shop-food-name").text();
			//String name="";
			/*try{
				name=ja.get(j).select("a.name").get(0).text();
			}
			catch(Exception ab)
			{
				
			}
		*/
			String img="";
			try{
				//String img=ja.get(j).select("div.shop-food-img").attr("src");
				
				img=ja.get(j).select("div.shop-food-img img").get(0).attr("src");
			}
			catch(Exception ab)
			{
				
			}
			String tm="";
			try{
				tm=ja.get(j).select("div.recommend-count").get(0).text();
			}
			catch(Exception ad)
			{
				
			}
			String jg="";
			try{
				jg=ja.get(j).select("div.shop-food-money").get(0).text();
			}
			catch(Exception ad)
			{
				
			}
			Elements je =ja.get(j).select("div.recommend-reson span.recommend-reson-item");
			String pj ="";
			try{
			for(int k=0; k<=je.size(); k++)
			{
				pj+=je.get(k).text()+" ";
			}
			
			
				}
				
			
			catch(Exception ad)
			{
				
			}
			//System.out.println(ja.get(j).outerHtml());
			int indexa=ja.get(j).outerHtml().indexOf("herf=\"//");
			int indexb=ja.get(j).outerHtml().indexOf("\" class=\"shop-food-item\"");
			String dishid=ja.get(j).outerHtml().substring(indexa+8,indexb);
			// String dishid=ja.get(j).attr("herf");
			/*//String send_time=doc.select("cost_time").;
			//item-rank-rst irr-star40
		
			String xingji="0";
			try{
			 xingji =ja.get(j).select(".sml-rank-stars").get(0).attr("class");
			 int inde=xingji.indexOf("sml-str");
				xingji=xingji.substring(inde+7,inde+9);
				
			}
			catch(Exception ee)
			{
				
				//ee.printStackTrace();
				try {
					Thread.sleep(3600000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//	String dizhi=ja.get(j).select("span.item").text();
			String rating_text=ja.get(j).select("div.review-words").text();
			String reply_text="";
			
			//String rating_star="0";
			//if(jo.containsKey("score"))
					//rating_star=jo.getString("desc");
			
			String kouwei="0";
			String huanjing="0";
			String fuwu="0";
			String renjun="0";
			try{
			 kouwei=ja.get(j).select("span.score.item span").get(0).text();
			 huanjing=ja.get(j).select("span.score.item span").get(1).text();
			 fuwu=ja.get(j).select("span.score.item span").get(2).text();
			 renjun=ja.get(j).select("span.score..item span").get(3).text();
			 
			}catch(Exception e){
				
				
				
			}
			
			
		String plid="3";
		try{
			plid=ja.get(j).select("a.favor").get(0).attr("data-id");
		}
		catch(Exception ee)
		{
			
		}

			
		
			
		//	Date dt=new Date();
			DazhongCaterDisscus  dis=new DazhongCaterDisscus ();
			dis.setUuid(dianpuids.get(z));
			dis.setCaterid(Long.parseLong(dianpuids.get(z)));
			try {
				Date dt=df2.parse(tm);
				dis.setDisscusTime(dt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}*/
			//String img="";
		/*if(!img.equals(""))
			{
			img=img.substring(0,1)+"/"+img.substring(1,3)+"/"+img.substring(3);
			
		    // realimg="https://p0.meituan.net/userheadpicbackend/"+img+"%4048w_48h_1e_1c_1l%7Cwatermark%3D0";
		    
			}*/
			DazhongDishRecommend  dis=new DazhongDishRecommend ();
			dis.setDishTag(pj);
			dis.setDishname(data);
			dis.setPicurl(img);
			
			try{
				dis.setPrice(Integer.parseInt(jg.substring(jg.indexOf("￥")+1)));
			}
			catch  (Exception r){
				dis.setPrice(0);
			}
			
			dis.setRecommendCount(Integer.parseInt(tm.substring(0,tm.indexOf("人推荐"))));
			dis.setCaterid(Long.parseLong(dianpuids.get(z)));
			System.out.println(dishid);
			dis.setDishid(Long.parseLong(dishid.substring(dishid.indexOf("dish")+4)));
			
			//dis.setDisscusor(name);
			/*ry{
			dis.setDisscusLevel(Integer.parseInt(xingji));
			}
			catch(Exception ee)
			{
				System.out.println("xingji:"+xingji);
				log.info(dianpuids.get(z)+ "  xingji:"+xingji+" page:"+page);
				dis.setDisscusLevel(0);

			}
			rating_text=luanma.filterEmoji(rating_text);
		//	rating_text=luanma.removeFourChar(rating_text);
			dis.setKouwei(Float.parseFloat(kouwei.substring(kouwei.indexOf(":")+1)));
			dis.setHuanjing(Float.parseFloat(huanjing.substring(huanjing.indexOf(":")+1)));
			dis.setFuwu(Float.parseFloat(fuwu.substring(fuwu.indexOf(":")+1)));
			dis.setRenjun(Float.parseFloat(renjun.substring(renjun.indexOf(":")+1)));
			dis.setPlid(Integer.parseInt(plid));
		    dis.setDisscusContent(rating_text);
		    dis.setUuid(dianpuids.get(z));
		    //Date tm=new Date();
			//try {
			//	tm = df.parse(rated_at);
			//} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
		    		dis.setDisscusReturn(reply_text);
		    		reply_text=luanma.filterEmoji(reply_text);
		    	//	reply_text=luanma.removeFourChar(reply_text);
		    		//dis.setDisscusType(type);
		    		//dis.setBuyDish(foodids);
		    		//Timestamp ts = new Timestamp(System.currentTimeMillis());  
*/
		    		dis.setCreateTime(Integer.parseInt((System.currentTimeMillis()/1000)+""));

		    //dis.setDisscusTime(tm);
		    try{
				//System.out.println("条数:"+(k++));
		    //	ElmCaterDisscus  ct2=ds.queryUnique("from ElmCaterDisscus where diss_uuid=?", id);
			//	if(ct2!=null)
			///	{
				//	dis.setId(ct2.getId());
//	cts.saveOrUpdate(ct);
				//}
				//else
				//{
					//cts.saveOrUpdate(ct);

				//}
		    //	System.out.println("即将保存");
		    	/*if(tt.add(ct.getUuid()))

				{
						ct.setId(id);
						cantings.add(ct);
				}*/
		    	List<DazhongDishRecommend> ecm=	ds.query("from DazhongDishRecommend where dishid =?", dis.getDishid());
                if(ecm.size()>0)
                	//continue;
                { 
                	
                	dis.setId(ecm.get(0).getId());
                //	continue;
				  //cantings.add(ct);
               
                ds.update(dis);
				}
                else
                	ds.save(dis);
			//	ds.saveOrupdate(dis);
		    //	System.out.println("保存cg");


					
			}
			catch(Exception ea)
			{
				ea.printStackTrace();
				/*try {
					Thread.sleep(3600000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println(ea.getMessage());
				
			}
		
		
		
		}page++;
	}
//DazhongCaterFirm firm=cts.get(Integer.parseInt(dianpuids2.get(z)+""));
DazhongCaterFirm firm=cts.queryUnique("from DazhongCaterFirm where id=?", dianpuids2.get(z));
if(firm!=null)
{
		firm.setOk2(1);
		cts.update(firm);
}
		
		
		
		}
		  queueManger.set("dzdpcp", "0");
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
			List<DazhongCaterFirm> cantings2=cts.query(z,1000,"from DazhongCaterFirm where  ( ok2 is null or ok2=0)");

			
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
 		              updatecanting();
	}

}
