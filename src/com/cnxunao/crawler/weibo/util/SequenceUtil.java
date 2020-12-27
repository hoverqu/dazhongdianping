package com.cnxunao.crawler.weibo.util;

public class SequenceUtil {

	private static long index;

	public static synchronized long next() {
		if (index == 0)
			index = System.currentTimeMillis();
		return index++;
	}

}