package lps;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.python.util.PythonInterpreter;

public class python {
	public static void main(String[] args) {
		//定义参数
		/*String[] args2 = {"arg1","arg2"};
		//设置参数
		PythonInterpreter.initialize(null, null, args2);
		PythonInterpreter interpreter = new PythonInterpreter(); 
		//执行
		interpreter.execfile("C:\\Users\\Administrator\\Desktop\\R语言深度学习\\py.py");
		System.out.println("----------run over!----------");*/
		
		try {
			Response doc = Jsoup.connect("http://www.dianping.com/search/category/2/10/g110").header("Referer", "http://www.dianping.com/search").header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36").execute();
			System.out.println(doc.cookies());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}

}
