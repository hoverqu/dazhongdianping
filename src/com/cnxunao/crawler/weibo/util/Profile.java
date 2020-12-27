package com.cnxunao.crawler.weibo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 配置文件
 * 
 * @author guild
 * 
 */
public final class Profile {

	// 配置文件路径
	private static String CONFIG_PATH = null;

	private Properties p;

	/**
	 * 配置文件： <br>
	 * 
	 * tomcat: project/conf/filename <br>
	 * web: project/WebRoot/conf/filename <br>
	 * java: project/conf/filename <br>
	 * runnable jar: folder/conf/filename
	 */
	public Profile(final String filename) {
		InputStream in = null;
		try {
			if (CONFIG_PATH == null)
				CONFIG_PATH = path("conf");
			in = new FileInputStream(filepath = CONFIG_PATH + File.separator
					+ filename);
			p = new Properties();
			p.load(in);
		} catch (IOException e) {
			throw new RuntimeException("profile " + filename + " is not exist");
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {

				}
		}
	}
	
	public static void main(String[] args) {
		String path = path();
		System.out.println(path());
		System.out.println(path("conf"));
		System.out.println(path("conf", "1", "2"));
	}
	
	public static String path(String... dir) {
		URL url = Profile.class.getProtectionDomain().getCodeSource()
				.getLocation();
		String path = url.getPath(), suffix;
		/* tomcat */
		if (path.endsWith(suffix = ".class")) {
			String className = Profile.class.getName().replace(".", "/")
					+ suffix;
			path = path.substring(0, path.indexOf(className));
		}
		File file = new File(path);
		path = file.getPath();
		/* web, tomcat */
		if (path.endsWith("classes"))
			path = file.getParentFile().getParent();
		/* java, jar */
		else if (path.endsWith("bin") || path.endsWith("jar"))
			path = file.getParent();
		if (dir != null && dir.length > 0) {
			for (String folder : dir) {
				path += File.separator + folder;
			}
			if (!new File(path).exists())
				new File(path).mkdirs();
		}
		return path;
	}

	public String getValue(String key) {
		if (key == null)
			return null;
		String value = p.getProperty(key);
		return value;
	}

	public String getValue(String key, String defaultValue) {
		String value = this.getValue(key);
		return value == null ? defaultValue : value;
	}

	public String getEncodeValue(String key) {
		String value = this.getValue(key);
		return this.encode(value);
	}

	public String getEncodeValue(String key, String defaultValue) {
		String value = this.getValue(key);
		return value == null ? defaultValue : this.encode(value);
	}

	public void setValue(String key, String value) {
		p.setProperty(key, value);
		this.store();
	}

	public void setValues(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet())
			p.setProperty(entry.getKey(), entry.getValue());
		this.store();
	}

	public void remove(String key) {
		p.remove(key);
		this.store();
	}

	public void clear() {
		p.clear();
		this.store();
	}

	private String filepath;

	/*
	 * 属性重写入配置文件
	 */
	private void store() {
		OutputStream out = null;
		try {
			out = new FileOutputStream(filepath);
			p.store(out, null);
		} catch (IOException e) {

		}
	}

	/*
	 * 字符编码
	 */
	private String encode(String v) {
		try {
			CharsetEncoder encoder = Charset.forName("ISO-8859-1").newEncoder();
			if (encoder.canEncode(v))
				v = new String(v.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		return v;
	}

	public boolean containsKey(String key) {
		return p.containsKey(key);
	}

	public List<String> keys() {
		List<String> list = new ArrayList<String>();
		Set<Object> keys = p.keySet();
		for (Object key : keys) {
			list.add((String) key);
		}
		return list;
	}

	public Map<String, String> values() {
		Map<String, String> map = new HashMap<String, String>();
		List<String> keys = this.keys();
		for (String key : keys) {
			String value = this.getValue(key);
			map.put(key, this.encode(value));
		}
		return map;
	}

	public Properties properties() {
		return p;
	}

}
