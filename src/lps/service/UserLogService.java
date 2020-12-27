package lps.service;

import lps.base.BaseService;
import lps.entities.DazhongCaterThinkSummary;
import lps.entities.UserLog;


/**
 * 用户日志
 * 
 * @author guild
 * 
 */
public interface UserLogService extends BaseService<DazhongCaterThinkSummary> {

	public void info(String channel, String module, String operate);

	public void error(String channel, String module, String operate);

}
