package lps;

import java.util.BitSet;  
import java.util.HashMap;

public class geohash {
    private static int numbits = 6 * 5; //经纬度单独编码长度  
    //32位编码对应字符
    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',  
            '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',  
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };  
    //定义编码映射关系  
    final static HashMap<Character, Integer> lookup = new HashMap<Character, Integer>();  
    //初始化编码映射内容
    static {  
        int i = 0;  
        for (char c : digits)  
            lookup.put(c, i++);  
    }  
    
    //对编码后的字符串解码
    public double[] decode(String geohash) {  
        StringBuilder buffer = new StringBuilder();  
        for (char c : geohash.toCharArray()) {  
  
            int i = lookup.get(c) + 32;  
            buffer.append( Integer.toString(i, 2).substring(1) );  
        }
        
        BitSet lonset = new BitSet();  
        BitSet latset = new BitSet();  
          
        //偶数位，经度
        int j =0;  
        for (int i=0; i< numbits*2;i+=2) {  
            boolean isSet = false;  
            if ( i < buffer.length() )  
              isSet = buffer.charAt(i) == '1';  
            lonset.set(j++, isSet);  
        }  
          
        //奇数位，纬度 
        j=0;  
        for (int i=1; i< numbits*2;i+=2) {  
            boolean isSet = false;  
            if ( i < buffer.length() )  
              isSet = buffer.charAt(i) == '1';  
            latset.set(j++, isSet);  
        }  
          
        double lon = decode(lonset, -180, 180);  
        double lat = decode(latset, -90, 90);  
          
        return new double[] {lat, lon};       
    }  
    
    //根据二进制和范围解码
    private double decode(BitSet bs, double floor, double ceiling) {  
        double mid = 0;  
        for (int i=0; i<bs.length(); i++) {  
            mid = (floor + ceiling) / 2;  
            if (bs.get(i))  
                floor = mid;  
            else  
                ceiling = mid;  
        }  
        return mid;  
    }  
      
    //对经纬度进行编码
    public String encode(double lat, double lon) {  
        BitSet latbits = getBits(lat, -90, 90);  
        BitSet lonbits = getBits(lon, -180, 180);  
        StringBuilder buffer = new StringBuilder();  
        for (int i = 0; i < numbits; i++) {  
            buffer.append( (lonbits.get(i))?'1':'0');  
            buffer.append( (latbits.get(i))?'1':'0');  
        }  
        return base32(Long.parseLong(buffer.toString(), 2));  
    }  
  
    //根据经纬度和范围，获取对应二进制
    private BitSet getBits(double lat, double floor, double ceiling) {  
        BitSet buffer = new BitSet(numbits);  
        for (int i = 0; i < numbits; i++) {  
            double mid = (floor + ceiling) / 2;  
            if (lat >= mid) {  
                buffer.set(i);  
                floor = mid;  
            } else {  
                ceiling = mid;  
            }  
        }  
        return buffer;  
    }  
  
    //将经纬度合并后的二进制进行指定的32位编码
    private String base32(long i) {  
        char[] buf = new char[65];  
        int charPos = 64;  
        boolean negative = (i < 0);  
        if (!negative)  
            i = -i;  
        while (i <= -32) {  
            buf[charPos--] = digits[(int) (-(i % 32))];  
            i /= 32;  
        }  
        buf[charPos] = digits[(int) (-i)];  
  
        if (negative)  
            buf[--charPos] = '-';  
        return new String(buf, charPos, (65 - charPos));  
    }  
    
    public static void main(String[] args)  throws Exception{  
        geohash geohash2 = new geohash();
        float buchang=0.00001f;
        float qishi=39.69787f;
        while(true)
        {
        	float jingdu=116.08864f;
            
        	if(qishi>40.00231f)
        		break;
        	while(true)
            {
        		if(jingdu>116.76014f)
            		break;
        		 String s = geohash2.encode(qishi,jingdu);
        	        //    String s = geohash2.encode(39, 116);
        	            System.out.println(s);
        	jingdu+=buchang;
                
            }
            
           
        //	 double[] geo = geohash2.decode(s);
         //    System.out.println(geo[0]+" "+geo[1]);
            qishi+=buchang;
        }

       // System.out.println(s);
       
    }  
}