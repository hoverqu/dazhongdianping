package com.cnxunao.crawler.weibo.util;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class Convert {

	public static void main(String[] args) throws Exception {

	}

	/**
	 * string <code>s</code> is converted into (short) number <br>
	 * if it fails it returns (short) 0
	 */
	public static short toShort(String s) {
		return toShort(s, (short) 0);
	}

	/**
	 * string <code>s</code> is converted into (short) number <br>
	 * if it fails it returns defaultValue
	 */
	public static short toShort(String s, short defaultValue) {
		if (s == null)
			return defaultValue;
		try {
			return Short.parseShort(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * string <code>s</code> is converted into (int) number <br>
	 * if it fails it returns 0
	 */
	public static int toInt(String s) {
		return toInt(s, 0);
	}

	/**
	 * string <code>s</code> is converted into (int) number <br>
	 * if it fails it returns defaultValue
	 */
	public static int toInt(String s, int defaultValue) {
		if (s == null)
			return defaultValue;
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * string <code>s</code> is converted into (long) number <br>
	 * if it fails it returns 0l
	 */
	public static long toLong(String s) {
		return toLong(s, 0l);
	}

	/**
	 * string <code>s</code> is converted into (long) number <br>
	 * if it fails it returns defaultValue
	 */
	public static long toLong(String s, long defaultValue) {
		if (s == null)
			return defaultValue;
		try {
			return Long.parseLong(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * string <code>s</code> is converted into (float) number <br>
	 * if it fails it returns 0f
	 */
	public static float toFloat(String s) {
		return toFloat(s, 0f);
	}

	/**
	 * string <code>s</code> is converted into (float) number <br>
	 * if it fails it returns defaultValue
	 */
	public static float toFloat(String s, float defaultValue) {
		if (s == null)
			return defaultValue;
		try {
			return Float.parseFloat(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * string <code>s</code> is converted into (double) number <br>
	 * if it fails it returns 0d
	 */
	public static double toDouble(String s) {
		return toDouble(s, 0d);
	}

	/**
	 * string <code>s</code> is converted into (double) number <br>
	 * if it fails it returns defaultValue
	 */
	public static double toDouble(String s, double defaultValue) {
		if (s == null)
			return defaultValue;
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * string <code>s</code> is converted into boolean <br>
	 * if it fails it returns false
	 */
	public static boolean toBoolean(String s) {
		return toBoolean(s, false);
	}

	/**
	 * string <code>s</code> is converted into boolean <br>
	 * if it fails it returns defaultValue
	 */
	public static boolean toBoolean(String s, boolean defaultValue) {
		if (s == null)
			return defaultValue;
		try {
			return Boolean.parseBoolean(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static <T> List<T> toList(T... a) {
		if (a == null)
			return null;
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
		return list;
	}

	public static <K, V> Map<K, V> toMap(K key, V value) {
		Map<K, V> map = new HashMap<K, V>();
		map.put(key, value);
		return map;
	}

	public static <T> boolean containsField(Class<T> clazz, String field) {
		boolean flag = false;
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().equals(field)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * getter
	 */
	public static <T> String getValue(T entity, String field) {
		String value;
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field,
					entity.getClass());
			Method m = pd.getReadMethod();
			value = (String) m.invoke(entity);
		} catch (Exception e) {
			value = null;
		}
		return value;
	}

	/**
	 * setter
	 */
	public static <T> void setValue(T entity, String field, String value) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field,
					entity.getClass());
			Method m = pd.getWriteMethod();
			// 参数类型
			Type type = m.getGenericParameterTypes()[0];
			Object args = null;
			/* int */
			if (type == Integer.TYPE || type == Integer.class)
				args = Convert.toInt(value);
			/* long */
			else if (type == Long.TYPE || type == Long.class)
				args = Convert.toLong(value);
			/* double */
			else if (type == Double.TYPE || type == Double.class)
				args = Convert.toDouble(value);
			/* String */
			else if (type == String.class)
				args = value;
			if (args != null)
				m.invoke(entity, args);
		} catch (Exception e) {

		}
	}

	public static <T> void setValue(T entity, Map<String, String> map) {
		Field[] fields = entity.getClass().getDeclaredFields();
		field: for (Field field : fields) {
			String name = field.getName();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (name.equals(entry.getKey())) {
					Convert.setValue(entity, name, entry.getValue());
					continue field;
				}
			}
		}
	}

	public static int extractInt(String s) {
		if (isBlank(s))
			return 0;
		String digital = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 48 && s.charAt(i) <= 57)
				digital += s.charAt(i);
		}
		int num = toInt(digital);
		return num;
	}

	/**
	 * whether the string <code>s</code> is empty
	 */
	public static boolean isBlank(String s) {
		return (s == null || s.length() == 0 || s.trim().length() == 0) ? true
				: false;
	}

	/**
	 * whether the string <code>s</code> is not empty
	 */
	public static boolean isNotBlank(String s) {
		return (s == null || s.length() == 0 || s.trim().length() == 0) ? false
				: true;
	}

	/**
	 * elete the spaces around the ends of the string <code>s</code>
	 */
	public static String trim(String s) {
		return (s == null || s.length() == 0) ? s : s.trim();
	}

	/**
	 * reverse string <code>s</code>
	 */
	public static String reverse(String s) {
		if (s == null || s.length() <= 1)
			return s;
		return new StringBuilder(s).reverse().toString();
	}

	/**
	 * whether a string <code>s<code> of digits
	 */
	public static boolean isNumeric(String s) {
		if (s == null || s.length() == 0)
			return false;
		for (char ch : s.toCharArray()) {
			if (!Character.isDigit(ch))
				return false;
		}
		return true;
	}

	/**
	 * whether a string <code>s<code> of letters
	 */
	public static boolean isAlpha(String s) {
		if (s == null || s.length() == 0)
			return false;
		for (char ch : s.toCharArray()) {
			if (!Character.isLetter(ch))
				return false;
		}
		return true;
	}

	/**
	 * whether a string <code>s<code> of letters and numbers
	 */
	public static boolean isAlphanumeric(String s) {
		if (s == null || s.length() == 0)
			return false;
		for (char ch : s.toCharArray()) {
			if (!Character.isLetter(ch) && !Character.isDigit(ch))
				return false;
		}
		return true;
	}

	/**
	 * whether the string contains numbers
	 */
	public static boolean hasNumeric(String s) {
		if (s == null || s.length() == 0)
			return false;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(s);
		while (m.find())
			return true;
		return false;
	}

	/**
	 * Whether the string contains chinese
	 */
	public static boolean hasChinese(String s) {
		if (s == null || s.length() == 0)
			return false;
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");
		Matcher m = p.matcher(s);
		while (m.find())
			return true;
		return false;
	}

	/**
	 * string <code>s</code> whether the ISO-8859-1 encoding
	 */
	public static boolean isLatin(String s) {
		CharsetEncoder encoder = Charset.forName("ISO-8859-1").newEncoder();
		return encoder.canEncode(s);
	}

	/**
	 * string <code>s</code> extract digital
	 */
	public static String getDigit(String s) {
		if (s == null || s.length() == 0)
			return s;
		String n = "";
		for (char ch : s.toCharArray()) {
			if (Character.isDigit(ch))
				n += ch;
		}
		return n;
	}

	/**
	 * string <code>s</code> is empty, return ""
	 */
	public static String defaultString(String s) {
		return s == null ? "" : s;
	}

	/**
	 * string <code>s</code> is empty, return defaultString
	 */
	public static String defaultString(String s, String defaultString) {
		return s == null ? defaultString : s;
	}

	/**
	 * by the given regular expression <code>reg</code> to split a string
	 * <code>s</code>
	 */
	public static String[] split(String s, String reg) {
		if (s == null)
			return null;
		if (reg == null)
			return new String[] { s };
		String[] array = s.split(reg);
		List<String> list = new ArrayList<String>();
		for (String str : array) {
			if (isNotBlank(str))
				list.add(str);
		}
		return list.toArray(new String[list.size()]);
	}

	public static final String CHARSET_ISO = "ISO-8859-1";

	/**
	 * The character encoding converter <br>
	 * ISO-8859-1 converted to UTF-8 or GBK
	 */
	public static String converter(String s, String charset) {
		if (s == null)
			return null;
		try {
			s = new String(s.getBytes(CHARSET_ISO), charset);
		} catch (UnsupportedEncodingException e) {

		}
		return s;
	}

	public static final String CHARSET_UTF = "UTF-8";

	/**
	 * The character encoding converter <br>
	 * ISO-8859-1 converted to UTF-8
	 */
	public static String converterUTF(String s) {
		return converter(s, CHARSET_UTF);
	}

	public static final String CHARSET_GBK = "GBK";

	/**
	 * The character encoding converter <br>
	 * ISO-8859-1 converted to GBK
	 */
	public static String converterGBK(String s) {
		return converter(s, CHARSET_GBK);
	}

	private static final String eKey = "1234567890abcdef";

	private static final String algorithm = "AES";

	/**
	 * <code>source</code> decrypted by the ASCII <br>
	 * returns the encrypted string consisting of lowercase letters with
	 */
	public static String encrypt(final String source) throws Exception {
		byte[] eKeys = eKey.getBytes("ASCII");
		SecretKeySpec keySpec = new SecretKeySpec(eKeys, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] b = cipher.doFinal(source.getBytes());
		return byteToHex(b).toLowerCase();
	}

	public static String byteToHex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * <code>v</code> decrypted by the ASCII <br>
	 * returns the source string
	 */
	public static String decrypt(final String v) throws Exception {
		byte[] eKeys = eKey.getBytes("ASCII");
		SecretKeySpec keySpec = new SecretKeySpec(eKeys, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] encrypted = hexToByte(v);
		byte[] b = cipher.doFinal(encrypted);
		return new String(b);
	}

	public static byte[] hexToByte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
					16);
		}
		return b;
	}

	/**
	 * MD5 Encryption <br>
	 * if the encryption successful 32 lowercase letters and digits encrypted
	 * string is returned
	 */
	public static String digest(final String v) throws NoSuchAlgorithmException {
		if (v == null)
			return null;
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(v.getBytes());
		StringBuilder sBuilder = new StringBuilder(32);
		String s;
		for (int i = 0; i < bytes.length; i++) {
			s = Integer.toHexString((int) bytes[i] & 0xff);
			if (s.length() < 2)
				sBuilder.append('0');
			sBuilder.append(s);
		}
		return sBuilder.toString();
	}

	/**
	 * string operations <br>
	 * for example: (1 + 2) / 100 * 3
	 * 
	 * @param formula
	 *            format string operations
	 * @return operational results
	 */
	public static BigDecimal caculate(String formula) {
		formula = formula.replaceAll(" ", "");
		Stack<BigDecimal> numbers = new Stack<BigDecimal>();
		Stack<Character> chs = new Stack<Character>();
		int start = 0;
		int end = -1;
		StringBuffer sb = new StringBuffer(formula);
		while ((end = sb.indexOf(")")) > 0) {
			String s = sb.substring(start, end + 1);
			int first = s.lastIndexOf("(");
			BigDecimal value = caculate(sb.substring(first + 1, end));
			sb.replace(first, end + 1, value.toString());
		}
		StringBuffer num = new StringBuffer();
		String tem = null;
		char next;
		while (sb.length() > 0) {
			// 获取字符串的第一个字符
			tem = sb.substring(0, 1);
			sb.delete(0, 1);
			if (tem.trim().matches("[0-9]")) {
				// 如果是数字，将其放入num当中
				num.append(tem);
			} else {
				if (num.length() > 0 && !"".equals(num.toString().trim())) {// 当截取的字符不是数字时，则认为num中放置的时一个完整的数字，
					// 如123+1,当获取到+时，前面的123可以认为是一个完整的数
					BigDecimal bd = new BigDecimal(num.toString().trim());
					numbers.push(bd);
					num.delete(0, num.length());
				}
				// 如果chs为空，这认为这时第一个字符直接放入
				if (!chs.isEmpty()) {
					// 当当前的运算符优先级等于或者小于栈顶得预算符时，做运算.
					// 例如,1+2+3,当截取到2,3之间的“+”与1,2之间的"+"优先级相等时，可以先计算1+2，使其变成3+3
					// 同样，1*2+3,当截取到2,3之间的“+”与1,2之间的"*"优先级小，可以先计算1*2，使其变成2+3
					while (!compare(tem.charAt(0), chs)) {
						caculate(numbers, chs);
					}
				}
				// 当数字栈也为空时,既运算式的第一个数字为负数时
				if (numbers.isEmpty()) {
					num.append(tem);
				} else {
					chs.push(new Character(tem.charAt(0)));
				}
				// 判断后一个字符是否为“-”号，为"-"号时，认为数字为负数
				// 例如 1*2*(-5)，因为此运算不计算()，因此将被改写为1*2*-5,如此情况，须将"-"认为是负数表达式而非减号
				next = sb.charAt(0);
				if (next == '-') {
					num.append(next);
					sb.delete(0, 1);
				}
			}
		}
		// 由于前面将数字放入栈时，是通过获取符号为时处理，导致最后一个数字没有放入栈中，因此将最后的数字放入栈中
		BigDecimal bd = new BigDecimal(num.toString().trim());
		numbers.push(bd);
		// 此时符号栈上最多只有2个符号，并且栈顶得符号优先级高，做运算
		while (!chs.isEmpty()) {
			caculate(numbers, chs);
		}
		return numbers.pop();
	}

	/*
	 * 比较当前操作符与栈顶元素操作符优先级，如果比栈顶元素优先级高，则返回true，否则返回false
	 * 
	 * @param str 需要进行比较的字符
	 * 
	 * @return boolean
	 */
	private static boolean compare(char str, Stack<Character> chs) {
		if (chs.empty()) {
			// 当为空时，显然 当前优先级最低，返回高
			return true;
		}
		char last = (char) chs.lastElement();
		switch (str) {
		case '*': {
			// '*/'优先级只比'+-'高
			if (last == '+' || last == '-')
				return true;
			else
				return false;
		}
		case '/': {
			if (last == '+' || last == '-')
				return true;
			else
				return false;
		}
		// '+-'为最低，一直返回false
		case '+':
			return false;
		case '-':
			return false;
		}
		return true;
	}

	private static void caculate(Stack<BigDecimal> numbers, Stack<Character> chs) {
		// 第二个运算数
		BigDecimal b = numbers.pop();
		// 第一个运算数
		BigDecimal a = numbers.pop();
		char ope = chs.pop();
		// 运算结果
		BigDecimal result = null;
		switch (ope) {
		case '+':
			result = a.add(b);
			// 将操作结果放入操作数栈
			numbers.push(result);
			break;
		case '-':
			result = a.subtract(b);
			// 将操作结果放入操作数栈
			numbers.push(result);
			break;
		case '*':
			result = a.multiply(b);
			// 将操作结果放入操作数栈
			numbers.push(result);
			break;
		case '/':
			result = a.divide(b);
			// 将操作结果放入操作数栈
			numbers.push(result);
			break;
		}
	}

}
