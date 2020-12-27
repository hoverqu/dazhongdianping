package lps.dao.im;


import lps.base.ImBaseDao;
import lps.dao.UserLogDao;
import lps.entities.DazhongCaterThinkSummary;
import lps.entities.UserLog;

import org.springframework.stereotype.Repository;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Repository("userLogDao")
public class ImUserLogDao extends ImBaseDao<DazhongCaterThinkSummary> implements UserLogDao {

}
