package com.cnxunao.crawler.weibo.mvc.service;

import com.cnxunao.crawler.weibo.mvc.model.ProxyIP;

public interface ProxyIPService {

	public abstract void changeProxyStatus(ProxyIP proxyIP, int status);

	public abstract ProxyIP getProxyIP();

	public abstract void inittime();
	public abstract int getright();
	public abstract void setright();

	

}