package com.cnxunao;
//package com.cnxunao.crawler.weibo.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import com.cnxunao.crawler.weibo.util.Profile;

import net.sf.json.*;
 
public class config {
	// c3P0
public  static JSONObject  weixinhaomoban=new JSONObject();
 //JSONObject liebiaomoban=new JSONObject();
 public static JSONObject zhengwenmoban=new JSONObject();
public static String temp="temp/";
public static String temp2="temp2/";
public static String prex="";

public static String downzip="d:/weixin/downzip/";
public static String downzip2="d:/weixin/downzip2/";

public static Map<String,String> cookie=new HashMap();
public static HashSet filterlist=new HashSet(1024000);

	static{
		Properties props = new Properties();
InputStream is = null;

		try{
		

//is = config.class.getResourceAsStream("files/moban.conf");
	System.out.println("loaidng config...");
	String path = Profile.path("conf")+File.separator+"moban.conf";
	String path2 = Profile.path("conf")+File.separator+"field_filter.conf";

	 InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
		
props.load(isr);
System.out.println("loading ok");
File p=new File(path2);

loadOneFile(p);
 weixinhaomoban.put("url",  JSONArray.fromObject(props.getProperty("weixinhaomoban.url").split("\t")));
 weixinhaomoban.put("div",  JSONArray.fromObject(props.getProperty("weixinhaomoban.div").split("\t")));
 weixinhaomoban.put("title",  JSONArray.fromObject(props.getProperty("weixinhaomoban.title").split("\t")));
 weixinhaomoban.put("biaozhi",  JSONArray.fromObject(props.getProperty("weixinhaomoban.biaozhi").split("\t")));
 weixinhaomoban.put("weixinhao",  JSONArray.fromObject(props.getProperty("weixinhaomoban.weixinhao").split("\t")));
 weixinhaomoban.put("jieshao",  JSONArray.fromObject(props.getProperty("weixinhaomoban.jieshao").split("\t")));
 weixinhaomoban.put("href",  JSONArray.fromObject(props.getProperty("weixinhaomoban.href").split("\t")));
 weixinhaomoban.put("count",  JSONArray.fromObject(props.getProperty("weixinhaomoban.count").split("\t")));


temp=props.getProperty("temp");
temp2=props.getProperty("temp2");

	downzip=props.getProperty("downzip");
	downzip2=props.getProperty("downzip2");
	prex=props.getProperty("prex");
	if (prex==null) {
		prex="";
	}

System.out.println("downzip:"+downzip);

 zhengwenmoban.put("title",  JSONArray.fromObject(props.getProperty("zhengwenmoban.title").split("\t")));

 zhengwenmoban.put("riqi",  JSONArray.fromObject(props.getProperty("zhengwenmoban.riqi").split("\t")));
 
 zhengwenmoban.put("author",  JSONArray.fromObject(props.getProperty("zhengwenmoban.author").split("\t")));
 
 zhengwenmoban.put("gz",  JSONArray.fromObject(props.getProperty("zhengwenmoban.gz").split("\t")));
 
 zhengwenmoban.put("content",  JSONArray.fromObject(props.getProperty("zhengwenmoban.content").split("\t")));

 
 
 String[] cookiess=props.getProperty("cookie").split("; ");
//String cck="acw_tc=AQAAALuzuxTbKAUAaYU1bgcO6WHfp7Xh; PHPSESSID=sapi7qcn468n8ojfsrf0ch8321; Hm_lvt_293b2731d4897253b117bb45d9bb7023=1489459247; Hm_lpvt_293b2731d4897253b117bb45d9bb7023=1489459254; bdshare_firstime=1489459254111";
 //String[] cookiess=cck.split("; ");

for(int  j=0;j<cookiess.length;j++)
			{
	cookie.put(cookiess[j].split("=")[0],cookiess[j].split("=")[1]);

			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("属性配置有误");

		}


	}
	
	public static long loadOneFile(File f) throws Exception
	{
		filterlist.clear();
		System.out.println(f.getPath());
		long n=0;
		InputStreamReader isr=new InputStreamReader(new FileInputStream(f),"UTF-8");
		BufferedReader in=new BufferedReader(isr);
		String line=in.readLine();
		while(line!=null)
		{
			filterlist.add(line);
			line=in.readLine();
			n++;
		}	
		in.close();
		return n;
	}
	
}
