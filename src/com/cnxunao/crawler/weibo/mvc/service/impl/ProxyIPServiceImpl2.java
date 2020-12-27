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

import sun.security.x509.IPAddressName;

import com.cnxunao.crawler.weibo.mvc.model.ProxyIP;
import com.cnxunao.crawler.weibo.mvc.service.ProxyIPService;
import com.cnxunao.crawler.weibo.util.Constant;
import com.cnxunao.crawler.weibo.util.Convert;
import com.cnxunao.crawler.weibo.util.Profile;

import redis.clients.jedis.Jedis;

public class ProxyIPServiceImpl2 implements ProxyIPService {

	public static volatile List<ProxyIP> proxyIPs;
 int waittime=0;
public int rightnumber=1;
Jedis queueManger = new Jedis("172.31.0.7");
//Jedis queueManger = new Jedis("127.0.0.1");

public  String cookieSet;

	static {
		System.out.println("---1---");
		if (proxyIPs == null) {
			System.out.println("---2---");
			proxyIPs = new ArrayList<ProxyIP>();
			String ipStr = "";
	    	String path = Profile.path("conf")+File.separator+"wyq.txt";
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
						7008);
				proxyIP.setStatus(Constant.STATUS_FREE);
				proxyIP.setIndex(0);
				proxyIP.setLastModified(new Date( new Date().getTime()-60000) );
				proxyIP.setCk(cksList.get(j).split(":")[1]);
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
		    	String path = Profile.path("conf")+File.separator+"wyq.txt";
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
							7008);
					proxyIP.setStatus(Constant.STATUS_FREE);
					proxyIP.setIndex(0);
					proxyIP.setLastModified(new Date( new Date().getTime()-10000) );
				//	System.out.println(j+cksList.get(j));
					proxyIP.setCk(cksList.get(j).split(":")[1]);
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
	public synchronized ProxyIP getProxyIP() {

		
		ProxyIP item = null;
		
		//int waittime=1;
		//int size=cookieSet.size();
		//rightnumber=size;
		int k=0;
		int errNum=0;
		String cookie="";
		
		while(true) {
			//String obj = cookieSet;
			//k++;
			try{
			item=null;
			cookieSet=queueManger.spop("zuobiao");
		//	queueManger.set("dzdp", "0");

				cookie=cookieSet;
				//if(errNum>20)

			//	System.out.println("zuobiaosize:"+queueManger.scard("zuobiao"));
					if(cookie==null)
						{
						System.out.println("地图坐标扫描完毕");
						break;
						}
				if (cookie!=null&&!cookie.equals("")) {
					if (!queueManger.exists(cookie)){
						item=new ProxyIP();
						item.setCk(cookie);
						item.setStatus(errNum);
						item.setHost(cookie);
						item.setPort(7008);
						//queueManger.spop(key)
						  //  queueManger.srem("weixincookie", str);  

						//queueManger.set(cookie, "");
						//queueManger.expire(cookie, 120);
						break;
					}
					else {queueManger.sadd("zuobiao", cookie);
						
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
		String cookieString=proxyIP.getHost();
		int errnum=proxyIP.getStatus();
		if(status==4)
		{
		
			queueManger.sadd("zuobiao", cookieString);
			queueManger.set(cookieString, "666");
			queueManger.expire(cookieString, 86400);

		
		
		}
		else if(status==1)
		{
			queueManger.sadd("zuobiao", cookieString);
			queueManger.set(cookieString, "666");
			queueManger.expire(cookieString, 864000);

		}
	
			
		
	}
	
}
