package com.cnxunao.crawler.weibo.mvc.model;

/**
 * 关键词 <br>
 * 所有关键词处于队列中，下一次下载时间最小的最先下载 <br>
 * 根据下载数量的多少决定下一次下载时间，数量越多，下一次下载时间越小
 * 
 * @author glt
 * 
 */
public class Keyword {

	private int id;
	private String code;
	private String word;
	private int times; // 下载次数
	private int amount; // 下载总数（排重后）
	private int next; // 下一次抓取
	private long latest; // 最新微博的发布时间
	private String url; // 最新微博URL
	private String growth; // 最近5次的数据增量，以“,”分隔
	private int status; // 状态，空闲、暂停（正在下载中）
	private int index;
	private int interminute;

	public Keyword() {

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

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public long getLatest() {
		return latest;
	}

	public void setLatest(long latest) {
		this.latest = latest;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGrowth() {
		return growth;
	}

	public void setGrowth(String growth) {
		this.growth = growth;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIndex() {
		return index;
	}

	public void setInterminute(int interminute) {
		this.interminute = interminute;
	}
	public int getInterminute() {
		return interminute;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
