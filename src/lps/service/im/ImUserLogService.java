package lps.service.im;

import java.util.Date;

import javax.annotation.Resource;

import lps.base.BaseDao;
import lps.base.ImBaseService;
import lps.entities.DazhongCaterThinkSummary;
import lps.entities.UserLog;
import lps.service.UserLogService;

import org.springframework.stereotype.Service;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Service("userLogService")
public class ImUserLogService extends ImBaseService<DazhongCaterThinkSummary> implements
		UserLogService {

	@Resource(name = "userLogDao")
	public void setBaseDao(BaseDao<DazhongCaterThinkSummary> baseDao) {
		super.setBaseDao(baseDao);
	}

	public void info(String channel, String module, String operate) {}

	public void error(String channel, String module, String operate) {}

}
