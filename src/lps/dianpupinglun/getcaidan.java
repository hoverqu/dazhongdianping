package lps.dianpupinglun;

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
import lps.entities.DazhongCaterThinkSummary;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterDisscus;
import lps.entities.ElmCaterFirm;
import lps.service.CantingService;
import lps.service.DishService;
import lps.service.DishsummeryService;
import lps.service.DisscussService;
import lps.service.UserLogService;
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
import com.cnxunao.crawler.weibo.util.luanma;

public class getcaidan implements Runnable {

	// ProxyIPServiceImpl ipService =null; 
	 Logger log = Logger.getLogger(getcaidan.class);

	ProxyIPService ipService =null; 
	List<String> dianpuids=new ArrayList<String>();
	List<Long> dianpuids2=new ArrayList<Long>();

	ApplicationContext context=ApplicationContextUtil.getContext();  
	 Jedis queueManger = new Jedis("172.31.0.12",6380);

	CantingService cts = (CantingService) context.getBean("cantingService");
	UserLogService thinkservice = (UserLogService) context.getBean("userLogService");
	DishsummeryService summer = (DishsummeryService) context.getBean("dishsummeryService");
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
		DisscussService ds = (DisscussService) context.getBean("disscussService");
	//	ds.delete("from DazhongCaterFirm where uuid=?",id);

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
		for(int z=0;z<dianpuids.size();z++)
		{
			int page=1;
		while(true)
		{
			System.out.println(""+page);
		String urlString="http://www.dianping.com/shop/"+dianpuids.get(z)+"/review_all"+"/p"+page;
		
		String  renshuString="0";
		Document doc=null;
		Response response;
		
		//System.out.println("成果获取IP:"+proxyIP.getHost());
		
		String ck="cy=109; _lxsdk_cuid=160a0c7f75fc8-02dc36b07238d3-454f032b-15f900-160a0c7f760c8; _lxsdk=160a0c7f75fc8-02dc36b07238d3-454f032b-15f900-160a0c7f760c8; _hc.v=8ff1a658-f612-d617-8b4c-c1fcc1fd2ae8.1514525948; _lxsdk_s=160a0c7f762-24f-add-45b%7C%7C8; cye=lishui; ";

		
		ProxyIP cks=ckpool.getProxyIP();
		
		ck=cks.getHost();
		

		String agent=ck.split("===")[1];
		ck=ck.split("===")[0];
		
	//	String agent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36";
		              //Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0
		System.out.println("["+ck+"]");
		System.out.println("["+agent+"]");
		
		try{
			
			//.proxy(proxyIP.getHost(), proxyIP.getPort())
			 // Thread.sleep(10000);
			 doc=	Jsoup.connect(urlString).proxy(proxyIP.getHost(), proxyIP.getPort()).timeout(10000).header("User-Agent", agent).header("Host", "www.dianping.com").cookies(str2map(ck)).header("Referer", "http://www.dianping.com/shop/76882941/review_all").get();
            
			//doc = Jsoup.connect(urlString).proxy(proxyIP.getHost(), proxyIP.getPort()).timeout(10000).header("Referer", "http://www.sojson.com").header("Host", "www.dianping.com").header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36").get();
		}catch(Exception ee)
		{
			
			//ee.printStackTrace();+proxyIP.getHost()+":"+proxyIP.getPort()
			 System.out.println(proxyIP.getHost()+":"+ proxyIP.getPort() +"   ip	"+ck+"&&&&&"+agent+"	FAIL	"+sdf.format(new Date())+" "+ee.getMessage() +" page:"+page+" index:"+z+"/"+dianpuids.size());
			  log.info(proxyIP.getHost()+":"+ proxyIP.getPort() +"  ip	"+ck+"	FAIL	"+sdf.format(new Date())+" "+ee.getMessage() +" page:"+page+" index:"+z+"/"+dianpuids.size());

			//ee.printStackTrace();
			//page--;
			  int tag=0;
			
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
				 
				 
			 }
				
					

			// TODO Auto-generated catch block
			//System.out.println(proxyIP.getHost()+"  ----> error:"+e.getMessage());
			//ee.printStackTrace();
			continue;
		}

		ckpool.changeProxyStatus(cks, 1);
//ipService.changeProxyStatus(proxyIP, 1);
	//System.out.println(urlString);
		//System.out.println(doc.outerHtml());
		
		//response = Jsoup.connect(urlString).ignoreContentType(true).execute();
		//String reString=response.body();
		//JSONArray ja=new JSONArray();

		//ja=JSONArray.fromObject(reString);
	
		
		
		if(page==1)
		{
			
	          thinkservice.delete(thinkservice.query("from DazhongCaterThinkSummary where caterid="+dianpuids.get(z)));

			Elements summerys=doc.select("div.content span.good.tag a");		
			for(int k=0;k<summerys.size();k++)
			{
				
				DazhongCaterThinkSummary sum=new DazhongCaterThinkSummary();
				String tag="";
				String count="";
				tag=summerys.get(k).attr("date-type");
				count=summerys.get(k).text();
				int index0=count.indexOf("(");
				int index1=count.indexOf(")");
				count=count.substring(index0+1,index1);
				sum.setThinkConent(tag);
				sum.setThinkSum(Integer.parseInt(count));
				sum.setCaterid(Integer.parseInt(dianpuids.get(z)));
				sum.setCreateTime(new Timestamp(new Date().getTime()));
				
				thinkservice.save(sum);
				
			}
			
			
			Elements summerys2=doc.select("div.content span.bad.tag a");		
			for(int k=0;k<summerys2.size();k++)
			{
				DazhongCaterThinkSummary sum=new DazhongCaterThinkSummary();
				String tag="";
				String count="";
				tag=summerys2.get(k).attr("date-type");
				count=summerys2.get(k).text();
				int index0=count.indexOf("(");
				int index1=count.indexOf(")");
				count=count.substring(index0+1,index1);
				sum.setThinkConent(tag);
				sum.setThinkSum(Integer.parseInt(count));
				sum.setCaterid(Integer.parseInt(dianpuids.get(z)));
				sum.setCreateTime(new Timestamp(new Date().getTime()));
				
				thinkservice.save(sum);
			}
			
			
			
			Elements labels=doc.select("div.filters label span.count");		
			
				
				String aa="0",bb="0",cc="0";
				try{
					aa=labels.get(1).text().replace("(", "").replace(")", "");
					 bb=labels.get(2).text().replace("(", "").replace(")", "");
					 cc=labels.get(3).text().replace("(", "").replace(")", "");
				}
				catch(Exception ee)
				{
					System.out.print("dianpuid:"+dianpuids.get(z)+"   店铺没有评论数");

				}
				

				int aa1=Integer.parseInt(aa);
				int bb1=Integer.parseInt(bb);
				int cc1=Integer.parseInt(cc);
			
				
				DazhongCaterDissSummary summeritem=new DazhongCaterDissSummary();
				summeritem.setCaterFirmid(Long.parseLong(dianpuids.get(z)));
				summeritem.setCreateTime(Integer.parseInt((System.currentTimeMillis()/1000)+""));
				summeritem.setDissCount(aa1+bb1+cc1);
				summeritem.setGoodDiss(aa1);
				summeritem.setModiDiss(bb1);
				summeritem.setLowDiss(cc1);
				
				DazhongCaterDissSummary summeritemold=summer.queryUnique("from DazhongCaterDissSummary where caterFirmid=?", dianpuids2.get(z));
				if(summeritemold!=null)
				{
						//summeritemold
						//firm.set
					summeritem.setId(summeritemold.getId());
						summer.update(summeritem);
				}
				else
					summer.save(summeritem);
				
				
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		Elements ja=doc.select("div.comment-list ul li[data-id]");		
		 System.out.println(proxyIP.getHost()+":"+ proxyIP.getPort() +"  "+ja.size()+"   ip	"+ck+"	OK	"+sdf.format(new Date()) +" page:"+page+" index:"+z+"/"+dianpuids.size());

		  log.info(proxyIP.getHost()+":"+ proxyIP.getPort() +"  "+ja.size()+"   ip	"+ck+"	OK	"+sdf.format(new Date()) +" page:"+page+" index:"+z+"/"+dianpuids.size());

		if(ja.size()==0)
		{
			ja=doc.select("div.reviews-items ul li");
		}

	   if(ja.size()==0)
	     	break;
		System.out.println("size:"+ja.size());
		for(int j=0;j<ja.size();j++)
		{
			
			//String data=ja.get(j).select(".tit a").attr("title");
			String name="";
			try{
				name=ja.get(j).select("a.name").get(0).text();
			}
			catch(Exception ab)
			{
				
			}
			String img="";
			try{
				img=ja.get(j).select("a.dper-photo-aside img").get(0).attr("src");
			}
			catch(Exception ab)
			{
				
			}
			String tm="";
			try{
				tm=ja.get(j).select("span.time").get(0).text();
			}
			catch(Exception ad)
			{
				
			}
		
			
			//String send_time=doc.select("cost_time").;
			//item-rank-rst irr-star40
		//	System.out.println(ja.get(j).outerHtml());
			String xingji="0";
			try{
			 xingji =ja.get(j).select(".sml-rank-stars").get(0).attr("class");
			 int inde=xingji.indexOf("sml-str");
				xingji=xingji.substring(inde+7,inde+9);
				
			}
			catch(Exception ee)
			{
				
				//ee.printStackTrace();
				/*try {
					Thread.sleep(3600000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		//	String dizhi=ja.get(j).select("span.item").text();
			String rating_text=ja.get(j).select("div.review-words").text();
			String reply_text="";
			try{
				reply_text=ja.get(j).select("div.comment-follows。p.desc。J-desc。Hide").text();
			}
			catch(Exception er){
				
			}
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
			}
			//String img="";
		/*if(!img.equals(""))
			{
			img=img.substring(0,1)+"/"+img.substring(1,3)+"/"+img.substring(3);
			
		    // realimg="https://p0.meituan.net/userheadpicbackend/"+img+"%4048w_48h_1e_1c_1l%7Cwatermark%3D0";
		    
			}*/
			dis.setDisscussUrl(img);
			dis.setDisscusor(name);
			try{
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

				//ds(dis);
		    //	System.out.println("保存cg");

				
				
				List<DazhongCaterDisscus> ecm=	ds.query("from DazhongCaterDisscus where plid =?", Integer.parseInt(plid));
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
               // else
               // {
               // //	cts.save(ct);
				//}
				
				
				//ct.setId(Integer.parseInt(id));
			//	cantings.add(ct);
		

					
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
				/*try {
					Thread.sleep(3600000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println(ee.getMessage());
				
			}
		}
		
		page++;
		}
		
//DazhongCaterFirm firm=cts.get(Integer.parseInt(dianpuids2.get(z)+""));
DazhongCaterFirm firm=cts.queryUnique("from DazhongCaterFirm where id=?", dianpuids2.get(z));
if(firm!=null)
{
		firm.setOk(1);
		cts.update(firm);
}
		
		
		
		}
		  queueManger.set("dzdppl", "0");
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
			List<DazhongCaterFirm> cantings2=cts.query(z,1000,"from DazhongCaterFirm where  ( ok is null or ok=0)");

			
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
				 ProxyIPService ipService =new ProxyIPServiceImpl(); 

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
	/*	try {
			Jsoup.connect("http://www.dianping.com/shop/66569336/review_all").cookies(str2map("_lxsdk_cuid=1607ee321e1c8-021772358c357e-5f19331c-100200-1607ee321e1c8; _lxsdk=1607ee321e1c8-021772358c357e-5f19331c-100200-1607ee321e1c8; _hc.v=3e691410-adbb-49b1-c427-81d9cf9636f2.1513957303; cy=2; cye=beijing; s_ViewType=10; _lxsdk_s=16087b6b67c-11a-4b6-872%7C%7C42")).proxy("118.252.79.68", 4368).timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").header("Host", "www.dianping.com").header("Referer", "http://www.dianping.com/shop/66569336").get();
		} catch (IOException e) {
			System.out.println("程序休息一小时。。。。"+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok");*/
		
	}

}
