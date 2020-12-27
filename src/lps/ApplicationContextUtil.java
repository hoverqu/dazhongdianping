package lps;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextUtil implements ApplicationContextAware {  
	  
    private static ApplicationContext context;//声明一个静态变量保存 

    @Override  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
        this.context=applicationContext;  
    }  
  
    public static ApplicationContext getContext(){  
    	if(context==null)
    	{
    		context = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
    		System.out.println("success");
    	//	System.out.println("/applicationContext.xml");
    		//context = new FileSystemXmlApplicationContext("/applicationContext.xml");
    	//	context = new ClassPathXmlApplicationContext(BeanFactory.class.getResource("/")+"/applicationContext.xml");
         
    	}
          return context;  
    }  
}  

