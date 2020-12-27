package com.cnxunao.crawler.weibo.util;

public class emotion
{

 QMatch qmatch_loc;
  EmotionSorter emotion_sorter;

public void init()
	{
this.qmatch_loc = new QMatch();
  this.emotion_sorter = new EmotionSorter();


try{

    this.emotion_sorter.initailize("conf\\step4.emotion_keywords.2.txt");

    this.qmatch_loc.loadConfig("conf\\step4.location_keywords.txt");
}
catch(Exception e)
		{
	         System.out.println("未发现配置文件，请确认");
		}


	}

   public String getlocation(String tx)
   {

           String re= this.qmatch_loc.match(tx);
		 //  System.out.println(re+"aaaaaaaaaaaaaaaaaaaaaaa"+qmatch_loc);
		 return re;
   }



public String getemotion(String tx)throws Exception
   {

          int s = this.emotion_sorter.scan(tx);
    if (s == 2)
      return "负面";
    if (s == 1)
      return "正面";
    if (s == 3)
      return "有正有负";
    return "无正负";
		 
   }





	public static void main(String []args)throws Exception
	{
            emotion em=new emotion();
			em.init();
			String loc=em.getlocation("北京 -cp 和 -classpath 一样，是指定类运行所依赖其他类的路径，通常是类库，jar包之类，需要全路径到jar包，window上分号“;”");

            String emo=em.getemotion("捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠捐赠 ");
             System.out.println("-----------:"+loc);
			 System.out.println("-----------:"+emo);



	}
}