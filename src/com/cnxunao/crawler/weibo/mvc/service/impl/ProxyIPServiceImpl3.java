package com.cnxunao.crawler.weibo.mvc.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


import redis.clients.jedis.Jedis;
import sun.security.x509.IPAddressName;

import com.cnxunao.crawler.weibo.mvc.model.ProxyIP;
import com.cnxunao.crawler.weibo.mvc.service.ProxyIPService;
import com.cnxunao.crawler.weibo.util.Constant;
import com.cnxunao.crawler.weibo.util.Convert;
import com.cnxunao.crawler.weibo.util.Profile;

public class ProxyIPServiceImpl3 implements ProxyIPService {

	public static volatile List<ProxyIP> proxyIPs;
 int waittime=0;
 //Jedis queueManger = new Jedis("172.31.0.7");
 //Jedis queueManger = new Jedis("172.27.1.142");
 //Jedis queueManger = new Jedis("127.0.0.1");
 Jedis queueManger = new Jedis("172.31.0.5",6379);

 public  String cookieSet;

public int rightnumber=1;
	static {
		System.out.println("---1---");
		if (proxyIPs == null) {
			System.out.println("---2---");
			proxyIPs = new ArrayList<ProxyIP>();
			String ipStr = "";
	    	String path = Profile.path("conf")+File.separator+"ip.txt";
	    	String path2 = Profile.path("conf")+File.separator+"cookie.txt";

	    	System.out.println(path);
	    	File file = new File(path);
	    	File file2 = new File(path2);
	    	List<String> cksList=new ArrayList<String>();
	    	if(file.exists()){
	    		try {
	    			BufferedReader br2= new BufferedReader(new FileReader(file));
					String temp2 = null;
					temp2 = br2.readLine();
					while(temp2!=null){
						if(!temp2.equals(""))
						cksList.add(temp2);
						//ipStr = ipStr + "," +temp;
						temp2 = br2.readLine();
						//System.out.println(kw);
					}
					br2.close();
				} catch (FileNotFoundException e2) {
					System.out.println("找不到文件异常");
				}catch (IOException e2) {
					System.out.println("I/O流异常");
				}
	    		try {
	    			BufferedReader br= new BufferedReader(new FileReader(file));
					String temp = null;
					temp = br.readLine();
					while(temp!=null){
						ipStr = ipStr + "," +temp;
						temp = br.readLine();
						
						//System.out.println(kw);
					}
					br.close();
				} catch (FileNotFoundException e) {
					System.out.println("找不到文件异常");
				}catch (IOException e) {
					System.out.println("I/O流异常");
				}
	    	}else{
	    		System.out.println(path+"文件不存在");
	    	}
	    	//if(!ipStr.equals(""))
	    	//	ipStr = ipStr.substring(1);
	    	//String ips[] = ipStr.split(",");
	    //	String[] array = Convert.split(ips[0], ":");
	    	System.out.println("cklist:"+cksList.size());
	    	for(int j=0;j<cksList.size();j++)
	    	{
	    		ProxyIP proxyIP = new ProxyIP(cksList.get(j).split(":")[0],
						Convert.toInt(cksList.get(j).split(":")[1]));
				proxyIP.setStatus(Constant.STATUS_FREE);
				proxyIP.setIndex(0);
				proxyIP.setLastModified(new Date( new Date().getTime()-60000) );
			//	System.out.println(j+cksList.get(j));
				//proxyIP.setCk(cksList.get(j));
				proxyIPs.add(proxyIP);
				//ii++;
	    	}
	    	/*int ii=0;
			for (String ip : ips) {
				
				String[] array = Convert.split(ip, ":");
				if (array.length == 2) {
					ProxyIP proxyIP = new ProxyIP(array[0],
							Convert.toInt(array[1]));
					proxyIP.setStatus(Constant.STATUS_FREE);
					proxyIP.setLastModified(new Date( new Date().getTime()-3600000) );
					System.out.println(ii+cksList.get(ii));
					proxyIP.setCk(cksList.get(ii));
					proxyIPs.add(proxyIP);
				}
				ii++;
			}*/
		}
	}

	public  static void init()
	{
		
			System.out.println("---1---");

			if (proxyIPs == null) {
				System.out.println("---2---");

				proxyIPs = new ArrayList<ProxyIP>();
				String ipStr = "";
		    	String path = Profile.path("conf")+File.separator+"ipAddress.txt";
		    	String path2 = Profile.path("conf")+File.separator+"cookie.txt";

		    	System.out.println(path);
		    	File file = new File(path);
		    	File file2 = new File(path2);
		    	List<String> cksList=new ArrayList<String>();
		    	if(file.exists()){
		    		try {
		    			BufferedReader br2= new BufferedReader(new FileReader(file));
						String temp2 = null;
						temp2 = br2.readLine();
						while(temp2!=null){
							if(!temp2.equals(""))
							cksList.add(temp2);
							//ipStr = ipStr + "," +temp;
							temp2 = br2.readLine();
							//System.out.println(kw);
						}
						br2.close();
					} catch (FileNotFoundException e2) {
						System.out.println("找不到文件异常");
					}catch (IOException e2) {
						System.out.println("I/O流异常");
					}
		    		try {
		    			BufferedReader br= new BufferedReader(new FileReader(file));
						String temp = null;
						temp = br.readLine();
						while(temp!=null){
							ipStr = ipStr + "," +temp;
							temp = br.readLine();
							//System.out.println(kw);
						}
						br.close();
					} catch (FileNotFoundException e) {
						System.out.println("找不到文件异常");
					}catch (IOException e) {
						System.out.println("I/O流异常");
					}
		    	}else{
		    		System.out.println(path+"文件不存在");
		    	}
		    	//if(!ipStr.equals(""))
		    	//	ipStr = ipStr.substring(1);
		    	//String ips[] = ipStr.split(",");
		    	//String[] array = Convert.split(ips[0], ":");
		    	for(int j=0;j<cksList.size();j++)
		    	{
		    		ProxyIP proxyIP = new ProxyIP(cksList.get(j).split(":")[0],
							Convert.toInt(cksList.get(j).split(":")[1]));
					proxyIP.setStatus(Constant.STATUS_FREE);
					proxyIP.setIndex(0);
					proxyIP.setLastModified(new Date( new Date().getTime()-60000) );
				//	System.out.println(j+cksList.get(j));
					//proxyIP.setCk(cksList.get(j));
					proxyIPs.add(proxyIP);
		    	}
		    	/*int ii=0;
				for (String ip : ips) {
					
					String[] array = Convert.split(ip, ":");
					if (array.length == 2) {
						ProxyIP proxyIP = new ProxyIP(array[0],
								Convert.toInt(array[1]));
						proxyIP.setStatus(Constant.STATUS_FREE);
						proxyIP.setLastModified(new Date( new Date().getTime()-3600000) );
						System.out.println(ii+cksList.get(ii));
						proxyIP.setCk(cksList.get(ii));
						proxyIPs.add(proxyIP);
					}
					ii++;
				}*/
			}
		System.out.println("init succeseful ");
		
	}
	
	public synchronized void inittime() {
		waittime=0;
	}
	public  int getright() {
		return  rightnumber;
	}
	/**
	 * 获取任意空闲代理
	 */
	public synchronized ProxyIP getProxyIP2() {
		ProxyIP proxyIP = null;
		try {
			Thread.sleep(new Random().nextInt(10) * 3 + 100);
		} catch (InterruptedException e) {

		}
		int k=0;
		//System.out.println("size:"+proxyIPs.size());
		//if(proxyIPs.size()>=0)
		//	return proxyIPs.get(0);
		for (int i = 0; i < proxyIPs.size(); i++) {
			
			ProxyIP ip = proxyIPs.get(i);
			
			if (ip.getStatus() == 4)
				k++;
		//	if((proxyIPs.size()-k)<100)
			//	  rightnumber=0;
				//  rightnumber=0;
			//System.out.println("get ip...."+i+"/"+proxyIPs.size()+" "+ip.getHost()+" "+ip.getStatus()+" 间隔时间:"+(System.currentTimeMillis()-ip.getLastModified().getTime()));
			if (ip.getStatus() == Constant.STATUS_FREE && (System.currentTimeMillis()-ip.getLastModified().getTime()>=1000) ) {
				//System.out.println(Thread.currentThread().getId()+"---"+"get ip...."+ip.getHost()+"-- "+ip.getStatus()+";"+i+"/"+proxyIPs.size()+";坏cookie个数:"+k);

				proxyIP = ip;
				
				break;
			}
		}
		if (k==proxyIPs.size()) {
			return null;
			
		}
		//System.out.println("000000000000000");
		/* 没有空闲代理 */
		if (proxyIP == null) {
			try {//System.out.println("no ip...");
				//waittime++;
			/*	System.out.println("no ip..."+waittime);
				Thread.sleep(3 * 1000l);
					waittime++;
					if(waittime>=5)
					{
						return null;
					}else {
						proxyIP = getProxyIP();
						
					}*/
				return null;
			} finally {
				//proxyIP = getProxyIP();
			}
		} else {
			// 修改代理状态为使用中
			changeProxyStatus(proxyIP, Constant.STATUS_USE);
			proxyIP.setIndex(proxyIP.getIndex()+1);
		}
		return proxyIP;
	}
	public  ProxyIP getProxyIP()
	{

		
		ProxyIP item = null;
		
		//int waittime=1;
		//int size=cookieSet.size();
		//rightnumber=size;
		int k=0;
		int errNum=0;
		String cookie="";
		String status="";
		
		while(true) {
			//String obj = cookieSet;
			//k++;
			try{
			cookieSet=queueManger.spop("proxyipsdzdp");
			queueManger.set("dzdpdp", "1");

			item=null;
				errNum=0;
				cookie=cookieSet;
				status="";
				//if(errNum>20)
				//System.out.println("ipsize:"+queueManger.scard("proxyips"));
				try{
					/*long size=queueManger.scard("proxyipsdz");
					if(size<10)
					{
						importip.imip();
					}*/
					}
					catch(Exception eee)
					{
						
					}
					if(cookie==null)
					{
						System.out.println("无可用IP");
						//importip.imip();
					//	Thread.sleep(10000);
						//break;
					}
					else
					{
					String str =cookieSet;//带有计数信息
					String[] info = str.split("-");
					if (info.length == 1) {
						cookie=info[0];
					}else if (info.length == 2) {
						cookie=info[0];
						errNum=Integer.parseInt(info[1]);
					}
					else if(info.length==3)
					{
						cookie=info[0];
						errNum=Integer.parseInt(info[1]);
						status=info[2];
					}
					
					}
					
					
					
					
				if (cookie!=null&&!cookie.equals("")) {
					if (!queueManger.exists(cookie)){
						System.out.println("发现新IP");

						item=new ProxyIP();
						item.setCk(status);
						item.setStatus(errNum);
						item.setHost(cookie.split(":")[0]);
						item.setPort(Integer.parseInt(cookie.split(":")[1]));
						item.setThreadName(status);
						//queueManger.spop(key)
						  //  queueManger.srem("weixincookie", str);  

						//queueManger.set(cookie, "");
						//queueManger.expire(cookie, 120);
						break;
					}
					else {
					//	System.out.println("IP等待中");
						queueManger.sadd("proxyipsdzdp", cookie+"-"+errNum+"-"+status);
						
					}
				}
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
			
		} 
		
		//System.out.println("kkkk:"+k);
		//if (k==proxyIPs.size()) {
		//	return null;
			
		//}
		//System.out.println("000000000000000");
		/* 没有空闲代理 */
		
			// 修改代理状态为使用中
		//	changeProxyStatus(proxyIP, Constant.STATUS_USE);
			//proxyIP.setIndex(proxyIP.getIndex()+1);
		
		//Thread.sleep(10 * 1000l);
		return item;
	
	}
	
	public  void setright() {
		  rightnumber=1000;
	}
	@Override
	public void changeProxyStatus(ProxyIP proxyIP, int status) {
		

		//proxyIP.setStatus(status);
		//proxyIP.setLastModified(new Date());
		String cookieString=proxyIP.getHost()+":"+proxyIP.getPort();
		int errnum=proxyIP.getStatus();
		String str=proxyIP.getThreadName();//ip status history
		int feiqi=0;
		if(str.length()>=10){// 判断是否长度大于等于4
			 
		       String history=str.substring(str.length()- 10);//一个参数表示截取传递的序号之后的部分
		   //     String strsub1=str.substring(str.length()- 10,str.length());//截取两个数字之间的部分
		      // proxyIP.setThreadName(threadName);
		       str=history;
		       if(history.equals("0000000000"))
		       {
		    	   feiqi=1;
		       }
		     }
		
		if(status==4)
		{
		errnum+=1;
		str+="0";
		if(feiqi==0)
		{
			queueManger.set(cookieString, "666");
			queueManger.expire(cookieString, 30);
			queueManger.sadd("proxyipsdzdp", cookieString+"-"+errnum+"-"+str);
			
			
		}

		
		
		}
		else if(status==1)
		{
			str+="1";
			queueManger.set(cookieString, "666");
			queueManger.expire(cookieString, 30);
			queueManger.sadd("proxyipsdzdp", cookieString+"-"+errnum+"-"+str);
			

		}
	
			
		
	}
	
}
