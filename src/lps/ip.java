package lps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ip {

	public static volatile List<String> keywords;
	
	static {
		if (keywords == null) {
			keywords = new ArrayList<String>();
			String kw = "";
			String path="./conf/ip.txt";
	    	System.out.println(path);
	    	File file = new File(path);
	    	List<String> kws=new ArrayList<String>();
	    	System.out.println("-正在读取关键词到磁盘....");
	    	if(file.exists()){
	    		
	    		try {
	    			 InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
	    			BufferedReader br= new BufferedReader(isr);
					String temp = null;
					temp = br.readLine();
					while(temp!=null){
						//kw = kw + "," +temp;
						keywords.add(temp);
						temp = br.readLine();
					}
					System.out.println(kw);
					br.close();
				} catch (FileNotFoundException e) {
					System.out.println("找不到文件异常");
				}catch (IOException e) {
					System.out.println("I/O流异常");
				}
	    	}else{
	    		System.out.println(path+"文件不存在");
	    	}
	    //	if(!kw.equals(""))
	    	//	kw = kw.substring(1);
	    	//、、String kws[] = kw.split(",");
			
		
	}

	

}}
