package com.cnxunao.crawler.weibo.mvc.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class testpic {
	
	public static void main(String[] args) throws IOException {
		//go();
		test("http://mini.eastday.com/07.imgmini.eastday.com/mobile/20171027/20171027232628_e4e4499359330c86cb582a1b4489b523_1.jpeg");
	}
	public static boolean test(String path) throws IOException
	{
		boolean bol;
		String posturl = path;  
        URL url = new URL(posturl);  
        HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();  
        urlcon.setConnectTimeout(1000);
        urlcon.setReadTimeout(1000);
        urlcon.setInstanceFollowRedirects(false);
        urlcon.setRequestMethod("GET");  
        urlcon.setRequestProperty("Content-type",  
                "application/x-www-form-urlencoded");  
        if (urlcon.getResponseCode() == HttpURLConnection.HTTP_OK) {  
            System.out.println(HttpURLConnection.HTTP_OK + posturl  
                    + ":posted ok!");  
            bol = true;  
        } else {  
            System.out.println(urlcon.getResponseCode() + posturl  
                    + ":Bad post...");  
            bol = false;  
        }
     return bol;
	}
	
	
	public static void go()
	{
		
	           String pics= "https://www.baidu.com/img/bd_logo0.png,https://www.baidu.com/img/bd_logo2.png,https://www.baidu.com/img/bd_logo1.png";
	           String[] picss=pics.split(",");
	           String picrightString="";
	           for (int j = 0; j < picss.length; j++) {
				String pathString=picss[j];
				boolean tt=false;
				try {
					tt=testpic.test(pathString);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(tt==true)
				{
					picrightString+=pathString+",";
				}
			}
	           if(!picrightString.equals(""))
	           {
	        	  picrightString=picrightString.substring(0,picrightString.length()-1);
	        	 //  article.setValue("TC",picrightString);
	        	  System.out.println(picrightString);
					//article.setValue("DM", "图片");
	           }
	           else
	           {
	        	  // article.setValue("TC","");
					//article.setValue("DM", "文本");
	        	   System.out.println("文本");
	        	   

	           }

		}
        
	

}
