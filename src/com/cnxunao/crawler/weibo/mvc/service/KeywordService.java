package com.cnxunao.crawler.weibo.mvc.service;

import com.cnxunao.crawler.weibo.mvc.model.Keyword;

public interface KeywordService {

	public abstract Keyword getKw();
	//public abstract int getgezishu();

	public abstract void updateKw(Keyword keyword, int nGrab);

	public abstract void addfirst(Keyword kk, int i);

}