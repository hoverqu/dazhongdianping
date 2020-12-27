package com.cnxunao.crawler.weibo.mvc.model;

import java.util.Date;

/**
 * 代理IP <br>
 * 大部分代理IP处于工作状态，预留几个处于空闲状态 <br>
 * 当有IP被限制使用时，确保有其它空闲IP替换
 * 
 * @author glt
 * 
 */
public class ProxyIP {

	private int id;
	private String code;
	private String host;
	private int port;
	private String username; // 用户名，代理不需要验证时可省略
	private String password;
	private int status; // 代理IP的状态，空闲、使用、停止
	private Date lastModified; // 最后修改时间
	private int index;
	private String threadName;
	private String ck;

	public ProxyIP() {

	}

	public ProxyIP(String host, int port) {
		super();
		this.host = host;
		this.port = port;
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getCk() {
		return ck;
	}

	public void setCk(String ck) {
		this.ck = ck;
	}
}
