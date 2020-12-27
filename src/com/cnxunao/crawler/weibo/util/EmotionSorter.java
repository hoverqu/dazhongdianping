package com.cnxunao.crawler.weibo.util;

import java.util.HashMap;
import java.util.Iterator;



public class EmotionSorter 
{
	QMatch qm;
	
	public  void initailize(String conffilename)throws Exception
	{
		qm=new QMatch();
		qm.loadConfig(conffilename);
	}
	
	final static String POSITIVE_LABEL="正面";
	final static String NEGATIVE_LABEL="负面";
	
	public final static int POSITIVE	=	1;
	public final static int NEGATIVE	=	2;
	public final static int NONE	=		0;
	public final static int BOTH	=		3;
	
	public void clear()
	{
		if(qm!=null)
			qm.clear();
	}
	public int scan(String txt)throws Exception
	{
		if(txt==null)
			return NONE;
		if(txt.length()<300)
			return scan_short(txt);
		return scan_long(txt);
	}
	public int scan_long(String txt)throws Exception
	{
		int txtlen=txt.length();
		HashMap rmap=qm.scan(txt);
		if(rmap!=null)
		{
			HashMap pm=(HashMap)rmap.get(POSITIVE_LABEL);
			int ptotal=0;
			if(pm!=null)
			{	
				Iterator it=pm.values().iterator();
				while(it.hasNext())
				{
					int v[]=(int [])it.next();
					ptotal+=v[0];	
				}	
			}
			HashMap nm=(HashMap)rmap.get(NEGATIVE_LABEL);
			int ntotal=0;
			if(nm!=null)
			{	
				Iterator it=nm.values().iterator();
				while(it.hasNext())
				{
					int v[]=(int [])it.next();
					ntotal+=v[0];	
				}	
			}
			if(nm!=null && nm.size()>=6 && txtlen/300<nm.size())
			{ 
				if(pm==null ||pm.size()<=nm.size()/2 || ptotal<ntotal/2)
				   return NEGATIVE;
				if(nm.size()>pm.size())
				     return BOTH;
			}	
			if(pm!=null && pm.size()>=6 && txtlen/300<pm.size()) 
			{
				 if( nm==null||nm.size()<= pm.size()/2 || ntotal<ptotal/2)
					 return POSITIVE;
				 return BOTH;
			}	
			
		}
		
		return NONE;
	}
	public int scan_short(String txt)throws Exception
	{
		int txtlen=txt.length();
		HashMap rmap=qm.scan(txt);
		if(rmap!=null)
		{
			HashMap pm=(HashMap)rmap.get(POSITIVE_LABEL);
			int ptotal=0;
			if(pm!=null)
			{	
				Iterator it=pm.values().iterator();
				while(it.hasNext())
				{
					int v[]=(int [])it.next();
					ptotal+=v[0];	
				}	
			}
			HashMap nm=(HashMap)rmap.get(NEGATIVE_LABEL);
			int ntotal=0;
			if(nm!=null)
			{	
				Iterator it=nm.values().iterator();
				while(it.hasNext())
				{
					int v[]=(int [])it.next();
					ntotal+=v[0];	
				}	
			}
			if(nm!=null && nm.size()>=1 && txtlen/300<nm.size())
			{ 
				if(pm==null ||pm.size()<=nm.size()/2 || ptotal<ntotal/2)
				   return NEGATIVE;
				if(nm.size()>pm.size())
				     return BOTH;
			}	
			if(pm!=null && pm.size()>=1 && txtlen/300<pm.size()) 
			{
				 if( nm==null||nm.size()<= pm.size()/2 || ntotal<ptotal/2)
					 return POSITIVE;
				 return BOTH;
			}	
			
		}
		
		return NONE;
	}
	public static void main(String []argv)throws Exception
	{
		String conf_file="conf\\step4.emotion_keywords.2.txt";
		EmotionSorter sorter=new EmotionSorter();
		sorter.initailize(conf_file);
		String txt="捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠";
		if(argv.length>0)
		{
			txt=FileUtil.read(argv[0]);
		}	
		System.out.println(txt);
		int s=sorter.scan(txt);
		switch(s)
		{
		case NONE: 	   System.out.println("NONE");break;
		case POSITIVE: 	   System.out.println("POSITIVE");break;
		case NEGATIVE: 	   System.out.println("NEGATIVE");break;
		case BOTH: 	   System.out.println("BOTH");break;
		}
	}
}
