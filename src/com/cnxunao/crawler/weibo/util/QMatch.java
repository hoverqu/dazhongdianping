package com.cnxunao.crawler.weibo.util;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;



public class QMatch {
   
	public QMatch()
	{
		
	}
	boolean hasSingleWord=false;
	
	Hashtable<String,String[]> table=new Hashtable<String,String[]>();
	Hashtable<String,String>table_single=new Hashtable<String,String>();
	
	public void clear()
	{
		table.clear();
		table_single.clear();
	}
	public void loadConfig(String cfn)throws Exception
	{
		File f=new File(cfn);
		if(!f.exists())throw new Exception("file "+cfn+" does not exist");
		 BufferedReader in=new BufferedReader( new InputStreamReader(new FileInputStream(f),"GBK"));
		String line=in.readLine();
		while(line!=null)
		{
			String ss[]=Convert.split(line,"=");
			ss[0]=ss[0].trim();
			ss[1]=ss[1].trim();
			if(ss[0].length()==1)
			{
				table_single.put(ss[0],ss[1]);
			}	
			else
			{	
				String k=ss[0].substring(0,2);
				if(table.get(k)!=null)
				{	
					String vs[]=table.get(k);
					int vl=vs.length;
					String nvs[]=new String[vl+2];
					int i=0;
					for(;i<vl/2;i++)
					{	
						if(vs[2*i].length() >ss[0].length())
						{ nvs[2*i]=vs[2*i];
						nvs[2*i+1]=vs[2*i+1];
						}else
							break;
					}
					nvs[2*i]=ss[0];
					nvs[2*i+1]=ss[1];
					for(;i<vl/2;i++)
					{	
						nvs[2*i+2]=vs[2*i];
						nvs[2*i+3]=vs[2*i+1];

					}

					table.put(k, nvs);
					//System.out.println("the dup:"+line);
				}
				else 
					table.put(k, ss);
			}
			
		//	System.out.println(k);
			line=in.readLine();
		}
		in.close();
		if(table_single.size()>0)
			hasSingleWord=true;
		
		/*Enumeration<String> en=table.keys();
		while(en.hasMoreElements())
		{
			String vs[]=table.get(en.nextElement());
			if(vs.length>2)
			{
				System.out.println("========="+vs.length);
				for(int i=0;i<vs.length;i++)
				{
					System.out.println(vs[i]);
				}	
			}	
		}	*/
	}
	public String match(String txt)
	{
		if(txt==null)return null;
		
		StringBuffer strb=new StringBuffer();
		
		int num=0;
		
		HashMap map=new HashMap();
		for(int i=0;i<txt.length()-1;i++)
		{
			String s=txt.substring(i,i+2);
			String vs[]=table.get(s);
			if(vs!=null)
			{
				for(int j=0;j<vs.length/2;j++)
				{
					int onelen=vs[(j<<1)].length();
					if(onelen==2)
					{	if( map.get(vs[(j<<1)+1])==null)
						{
						if(num!=0)
							strb.append(",");
						strb.append(vs[(j<<1)+1]);					
						map.put(vs[(j<<1)+1],vs[(j<<1)+1]);
						num++;
						}
					}else
					{
						if(i+onelen<=txt.length())
						{
							boolean found=true;
							for(int k=2;k<onelen;k++)
							  if(txt.charAt(k+i)!=vs[(j<<1)].charAt(k))
							  {
								  found=false;
								  break;
							  }
							if(found)
							{
								if( map.get(vs[(j<<1)+1])==null)
								{
								if(num!=0)
									strb.append(",");
								strb.append(vs[(j<<1)+1]);					
								map.put(vs[(j<<1)+1],vs[(j<<1)+1]);
								num++;
								}
								break;
							}	
						}	
					} 	
					
				}	
			}	
		}
		
		if(hasSingleWord)
			for(int i=0;i<txt.length();i++)
			{
				String s=txt.substring(i,i+1);
				String vs=table_single.get(s);
				if(vs!=null)
				{
					if( map.get(vs)==null)
					{
					if(num!=0)
						strb.append(",");
					strb.append(vs);					
					map.put(vs,vs);
					num++;
					}
				}
			}
		
		if(num==0)
			return null;
		
		   return strb.toString();
		
		
	}
	public HashMap scan(String txt)
	{
		
		if(txt==null)return null;
		
		
		
		
		HashMap map=new HashMap();
		
		for(int i=0;i<txt.length()-1;i++)
		{
			String s=txt.substring(i,i+2);
			String vs[]=table.get(s);
			if(vs!=null)
			{
				for(int j=0;j<vs.length/2;j++)
				{
					int onelen=vs[(j<<1)].length();
					if(onelen==2)
					{	
						
						putToMap(map,vs[(j<<1)],vs[(j<<1)+1]);
						
					}else
					{
						if(i+onelen<=txt.length())
						{
							boolean found=true;
							for(int k=2;k<onelen;k++)
							  if(txt.charAt(k+i)!=vs[(j<<1)].charAt(k))
							  {
								  found=false;
								  break;
							  }
							if(found)
							{
								putToMap(map,vs[(j<<1)],vs[(j<<1)+1]);
								break;
							}	
						}	
					} 	
					
				}	
			}	
		}
		
		if(hasSingleWord)
			for(int i=0;i<txt.length();i++)
			{
				String s=txt.substring(i,i+1);
				String vs=table_single.get(s);
				if(vs!=null)
				{
					putToMap(map,s,vs);
				}
			}
		
		   return map;
		
		
	}
	private void putToMap(HashMap map, String keyword, String category) {
		
		HashMap m=(HashMap) map.get(category);
		if( m==null)
		{
		   m=new HashMap();
		   int v[]=new int[1];
		   v[0]=1;
		   m.put(keyword, v);
					
		   map.put(category,m);
		   return;
		
		}
		
		int v[]=(int[]) m.get(keyword);
		if(v==null)
		{
			v=new int[1];
			   v[0]=1;
			   m.put(keyword, v);
			   return;
		}	
		
		v[0]++;
	}
	public static void main(String argv[])throws Exception
	{
		QMatch qm=new QMatch();
		qm.loadConfig("conf\\location_keywords.txt");
	    String r=qm.match("����˹ά��˹�ĵڶ��죬��ȥ�˿ɿڿ��ֹ�˾������18��ȫ���粻ͬ��ζ�Ŀɿڿ��֡���ζ�����[��][��] ����@���٠�_��˥ ˵Ҫ����ʯͷ����Ӯ�ľ�Ҫѡһ������ġ���@Loli� һֱû�䣬����������۸���������������һ���������[͵Ц] ������4�Σ����˱�����ˮζ���Ŀ���[��][��]   #Weicoƴͼ#");	
	    System.out.println(r);
	    System.out.println(qm.scan("������=���ɹ�������:����������ɽ��=����׳��������:������"));
	}
}
