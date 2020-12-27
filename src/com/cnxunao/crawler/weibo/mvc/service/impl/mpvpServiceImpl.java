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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import sun.security.x509.IPAddressName;

import com.cnxunao.analysisquestion;
import com.cnxunao.crawler.weibo.mvc.model.ProxyIP;
import com.cnxunao.crawler.weibo.mvc.service.ProxyIPService;
import com.cnxunao.crawler.weibo.mvc.service.mpvpService;
import com.cnxunao.crawler.weibo.util.Constant;
import com.cnxunao.crawler.weibo.util.Convert;
import com.cnxunao.crawler.weibo.util.Profile;

public class mpvpServiceImpl implements mpvpService {
analysisquestion ans=new analysisquestion();
 int waittime=0;
public int rightnumber=1;
	static {
	
	}

	public  static void init()
	{
		
		
	}
	
	public synchronized void inittime() {
		waittime=0;
	}

    /*     */   public static String getjson(String str,String rega,int gr)
    /*     */   {
    /* 187 */     String result = "null";
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
    
	
	

	@Override
	public String getmp(String url) throws IOException {
		// TODO Auto-generated method stub
		 Document document=null;
		   String haibaoString="";
		   int i=0;
		while(document==null)
		{
			i++;
			if(i>=3)
				return "";
			try{
			document=Jsoup.connect(url).timeout(20000).get();
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
				System.out.println("央广网服务器异常:"+ee.getMessage()+url);
			}
		
		    
		}
		 haibaoString= getjson(document.outerHtml(),"flvImgUrl=\"(.*)\"",1);
		    return haibaoString;
	}

	
	
	public Document getmp2(String url) throws IOException {
		// TODO Auto-generated method stub
		 Document document=null;
		   String haibaoString="";
		   int i=0;
		while(document==null)
		{
			i++;
			if(i>=13)
				return null;
			try{
			document=Jsoup.connect(url).timeout(20000).get();
			}
			catch(Exception ee)
			{
				//ee.printStackTrace();
				System.out.println("央广网服务器异常:"+ee.getMessage()+url);
			}
		
		    
		}
		return document;
		// haibaoString= getjson(document.outerHtml(),"flvImgUrl=\"(.*)\"",1);
		  //  return haibaoString;
	}
	
	
	@Override
	public String getvp(String url,String ip,int port) {
		// TODO Auto-generated method stub
		//String mv=ans.getvideolist(url, ip, port,"");
		String mv=ans.getvideolist2(url, ip, port);

		//if(mv.equals("fre"))
			//return "fre
		return mv;
	}

	@Override
	public String getvp(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
