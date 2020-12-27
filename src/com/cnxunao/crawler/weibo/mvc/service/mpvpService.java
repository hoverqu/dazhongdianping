package com.cnxunao.crawler.weibo.mvc.service;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.cnxunao.crawler.weibo.mvc.model.Keyword;

public interface mpvpService {

	public abstract String getmp(String url) throws IOException;

	public abstract String getvp(String url);

	String getvp(String url, String ip, int port);

	public abstract Document getmp2(String url) throws IOException;

}