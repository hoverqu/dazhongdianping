package com.cnxunao.crawler.weibo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间
 * 
 * @author guild
 * 
 */
public final class DateUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final long MILLIS_HOUR = 3600000l;

	public static final long MILLIS_DAY = 86400000l;

	/**
	 * in the current time format template <code>pattern</code>
	 */
	public static String format(final String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * formatting a template <code>pattern</code> time <code>date</code>
	 */
	public static String format(final Date date, final String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * the time string <code>source</code> format and return date
	 * 
	 * @param source
	 *            dateFormat："yyyy-MM-dd"、"yyyy-M-d"、 <br>
	 *            "yyyy-M-dd"、"yyyy-MM-d"、 "yyyy-MM-dd HH:mm:ss"
	 */
	public static Date parse(final String source) {
		if (source == null)
			return null;
		try {
			String text = source;
			if (text.indexOf(".") != -1)
				text = text.substring(0, text.indexOf("."));
			/* yyyy-M-d */
			if (text.length() == 9 || text.length() == 8) {
				String[] dateStr = text.split("-");
				text = dateStr[0] + (dateStr[1].length() == 1 ? "-0" : "-")
						+ dateStr[1] + (dateStr[2].length() == 1 ? "-0" : "-")
						+ dateStr[2];
			}
			/* yyyy-MM-dd */
			if (text.length() == 10)
				text += " 00:00:00";
			/* yyyy-MM-dd HH */
			if (text.length() == 13)
				text += " :00:00";
			/* yyyy-MM-dd HH:mm */
			if (text.length() == 16)
				text += ":00";
			if (text.length() != 19)
				return null;
			SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
			return dateFormat.parse(text);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * fixed format <code>pattern</code> format string date <code>source</code>
	 */
	public static Date parse(final String source, final String pattern) {
		if (source == null)
			return null;
		if (pattern == null)
			return parse(source);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.parse(source);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * the time string <code>source</code> format and return date <br>
	 * if it fails it returns <code>def</code>
	 */
	public static Date parse(final String source, final Date def) {
		Date date = parse(source);
		return date == null ? def : date;
	}

	public static boolean isToday(Date date) {
		if (date == null)
			throw new IllegalArgumentException("The date must not be null");
		final Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		final Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());
		return isSameDay(c1, c2);
	}

	/**
	 * Checks if two date objects are on the same day ignoring time.<br>
	 * 
	 * @param c1
	 *            the first calendar, not altered, not null
	 * @param c2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameDay(final Date date1, final Date date2) {
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException("The date must not be null");
		final Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		final Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return isSameDay(c1, c2);
	}

	/**
	 * Checks if two calendar objects are on the same day ignoring time.<br>
	 * 
	 * @param c1
	 *            the first calendar, not altered, not null
	 * @param c2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 */
	public static boolean isSameDay(final Calendar c1, final Calendar c2) {
		if (c1 == null || c2 == null)
			throw new IllegalArgumentException("The date must not be null");
		return c1.get(Calendar.ERA) == c2.get(Calendar.ERA)
				&& c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Checks if two date objects represent the same instant in time. <br>
	 * This method compares the long millisecond time of the two objects.
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameInstant(final Date date1, final Date date2) {
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException("The date must not be null");
		return date1.getTime() == date2.getTime();
	}

	/**
	 * Checks if two calendar objects represent the same instant in time. <br>
	 * This method compares the long millisecond time of the two objects.
	 * 
	 * @param c1
	 *            the first calendar, not altered, not null
	 * @param c2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameInstant(final Calendar c1, final Calendar c2) {
		if (c1 == null || c2 == null)
			throw new IllegalArgumentException("The date must not be null");
		return c1.getTimeInMillis() == c2.getTimeInMillis();
	}

	public static Calendar calendar(Date date) {
		return calendar(date, false);
	}

	public static Calendar calendar(boolean clear) {
		return calendar(new Date(), clear);
	}

	public static Calendar calendar(Date date, boolean clear) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (clear) {
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		}
		return c;
	}

	/**
	 * the number of days between two dates
	 */
	public static int getDaysInterval(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return 0;
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		long t = Math.abs((t1 - t2) / MILLIS_DAY);
		return (int) t;
	}

	/**
	 * get the current time offset time, time cleared
	 */
	public static Date getDaysOffset(int offset) {
		return getDaysOffset(new Date(), offset, true);
	}

	/**
	 * get offset time
	 * 
	 * @param date
	 *            specified time
	 * @param offset
	 *            time Offset Days
	 * @param clear
	 *            whether the time is cleared
	 * @return Date
	 */
	public static Date getDaysOffset(Date date, int offset, boolean clear) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) - offset);
		if (clear) {
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		}
		return c.getTime();
	}
	public static Date getDate(String s) {
		Date date = null;
		if (s == null)
			return new Date();
		try {
			if (s.length() > 10) {
				date = new SimpleDateFormat(TIME_FORMAT).parse(s);
			} else {
				date = new SimpleDateFormat(DATE_FORMAT).parse(s);
			}
		} catch (Exception e) {

		}
		return date;
	}
	/**
	 * 获得时间点已清零的 long 时间
	 */
	public static long getClearedTime(int offset) {
		return getClearedTime(null, offset);
	}
	/**
	 * 获得时间点已清零的 long 时间
	 */
	public static long getClearedTime(String s, int offset) {
		Date date = getDate(s);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DATE, c.get(Calendar.DATE) - offset);
		return c.getTimeInMillis();
	}


}
