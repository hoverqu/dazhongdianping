package com.cnxunao.crawler.weibo.mvc.model;

/*
 * 每日下载统计
 */
public class DailyDownloadStatistics {

	private int id;
	private String code;
	private String date;
	private int amount;
	private int times;
	private String kwCode; // 关键词
	
	public DailyDownloadStatistics() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getKwCode() {
		return kwCode;
	}

	public void setKwCode(String kwCode) {
		this.kwCode = kwCode;
	}

}
