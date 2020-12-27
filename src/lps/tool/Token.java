package lps.tool;
import java.util.ArrayList;
public class Token {
private static final String TOKEN_LIST_NAME = "tokenList";
public static final String TOKEN_STRING_NAME = "token";



private synchronized static String generateTokenString(){
	String temp=new Long(System.currentTimeMillis()).toString();
	String re="";
	for(int i=0;i<11;i++)
       re+=temp; 
    System.out.print(re);
return temp;
}

/** *//**
     * Generate a token string, and save the string in session, then return the token string.
     * @param HttpSession session
     * @return a token string used for enforcing a single request for a particular transaction.
     */


/** *//**

     * check whether token string is valid. if session contains the token string, return true. 
     * otherwise, return false.
     * @param String tokenStr
     * @param HttpSession session
     * @return true: session contains tokenStr; false: session is null or tokenStr is id not in session
     */
public static void main(String []args)
{
	generateTokenString();
}
   
}