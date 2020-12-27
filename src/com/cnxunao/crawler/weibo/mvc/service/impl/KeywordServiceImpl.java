package com.cnxunao.crawler.weibo.mvc.service.impl;

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

import com.cnxunao.crawler.weibo.mvc.model.Keyword;
import com.cnxunao.crawler.weibo.mvc.service.KeywordService;
import com.cnxunao.crawler.weibo.util.Constant;
import com.cnxunao.crawler.weibo.util.Convert;
import com.cnxunao.crawler.weibo.util.Profile;

public class KeywordServiceImpl implements KeywordService {

	public static volatile List<Keyword> keywords;
	
	static {
		if (keywords == null) {
			keywords = new ArrayList<Keyword>();
			String kw = "";
	    	String path = Profile.path("conf")+File.separator+"door.txt";
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
						kws.add(temp);
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
			for (int i = 0, len = kws.size(); i < len; i++) {
				Keyword keyword = new Keyword();
				keyword.setWord(kws.get(i).split("\t")[0]);
				keyword.setInterminute(Integer.parseInt(kws.get(i).split("\t")[1]));
				keyword.setNext(0);
				keyword.setLatest(444l);
				keyword.setStatus(Constant.STATUS_FREE);
				keyword.setIndex(i);
				keywords.add(keyword);
			}
		}
	}

	/**
	 * 获取next值最小的空闲关键词
	 */
	public synchronized Keyword getKw() {
		//System.out.println("getting..");
		Keyword keyword = null;
		/*try {
			Thread.sleep(new Random().nextInt(300) * 3 + 100);
		} catch (InterruptedException e) {

		}*/

		for (int i = 0; i < keywords.size(); i++) {
			
			Keyword kw = keywords.get(i);
			System.out.println("###getting"+keywords.size());
			if (kw.getStatus() == Constant.STATUS_FREE
					&& ( kw.getLatest()<=System.currentTimeMillis()))
				{
				System.out.println("get word  "+i+"/"+keywords.size()+kw.getWord());
				keyword = kw;
				keywords.remove(i);
				break;
				}
		}
	////	System.out.println("###geting);
		if (keyword == null) {
			
				System.out.println("no word:"+";size:"+keywords.size());
				//Thread.sleep(5 * 1000l);
			
		} else {
			keyword.setStatus(Constant.STATUS_USE);
			//keywords.remove(i);
		}
		return keyword;
	}

	/**
	 * 抓取完成后更新关键词
	 * 
	 * @param nGrab
	 *            次数抓取的增量，异常抓取则为 -1
	 */
	
	@Override
	public synchronized void updateKw(Keyword keyword, int nGrab) {
		/* 异常时，仅改变关键词状态 */
		 if (nGrab != -1) {
			keyword.setStatus(Constant.STATUS_FREE);
			
			// 最近5次下载的增量
			//String growth = getGrowth(nGrab, keyword.getGrowth());
			//keyword.setGrowth(growth);
			// 下一次抓取
			//int next = keyword.getNext() + calculateNext(growth);
			//keyword.setNext(next);
			//keyword.setAmount(keyword.getAmount() + nGrab);
			//keyword.setTimes(keyword.getTimes() + 1);
			//System.out.println("qqlps:"+keyword.getInterminute());
		}
		//keywords.remove(keyword.getIndex());
		//keyword.setIndex(keyword;
		//keywords.set(keyword.getIndex(), keyword);
		keyword.setLatest(System.currentTimeMillis()+keyword.getInterminute()*60*1000L);
		
		 keywords.add(keyword);
	}
	
	
	
	
	

	@Override
	public synchronized void addfirst(Keyword keyword, int nGrab) {
		/* 异常时，仅改变关键词状态 */
		 if (nGrab != -1) {
			keyword.setStatus(Constant.STATUS_FREE);
			
			// 最近5次下载的增量
			//String growth = getGrowth(nGrab, keyword.getGrowth());
			//keyword.setGrowth(growth);
			// 下一次抓取
			//int next = keyword.getNext() + calculateNext(growth);
			//keyword.setNext(next);
			//keyword.setAmount(keyword.getAmount() + nGrab);
			//keyword.setTimes(keyword.getTimes() + 1);
			//System.out.println("qqlps:"+keyword.getInterminute());
		}
		//keywords.remove(keyword.getIndex());
		//keyword.setIndex(keyword;
		//keywords.set(keyword.getIndex(), keyword);
		keyword.setLatest(System.currentTimeMillis());
		
		 keywords.add(0,keyword);
	}
	
	
	
	

	/**
	 * 最近几次的爬虫抓取增量
	 */
	private String getGrowth(int nGrab, String growth) {
		if (growth == null || growth.length() == 0)
			growth = String.valueOf(nGrab);
		else
			growth += (growth.endsWith(",") ? "" : ",") + nGrab;
		String[] strs = Convert.split(growth, ",");
		String result = "";
		for (int len = strs.length, i = len > 5 ? len - 5 : 0; i < len; i++) {
			result += strs[i] + (i == len - 1 ? "" : ",");
		}
		return result;
	}

	/* 基数 */
	private static final int[] CARDINALITY = { 1, 2, 4, 8, 16 };

	/* 每次抓取最大数量 */
	private static final int MAX_GRAB = 20;

	/**
	 * 计算关键词抓取的顺序，由小到大
	 */
	private int calculateNext(String growth) {
		int next = keywords.size();
		// 最近几次爬虫抓取的增量
		String[] array = Convert.split(growth, ",");
		for (int i = 0, len = array.length; i < len; i++) {
			// 爬虫抓取的最大数量（20）与实际抓取数量之间的差数
			int diff = Convert.toInt(array[i]);
			diff = MAX_GRAB - diff < 0 ? 0 : MAX_GRAB - diff;
			next += diff * CARDINALITY[CARDINALITY.length - len + i];
		}
		return next;
	}

}
