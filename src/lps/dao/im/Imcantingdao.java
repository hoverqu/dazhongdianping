package lps.dao.im;


import lps.base.ImBaseDao;
import lps.dao.CantingDao;
import lps.dao.UserLogDao;
import lps.entities.Canting;
import lps.entities.DazhongCaterFirm;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;

import org.springframework.stereotype.Repository;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Repository("cantingDao")
public class Imcantingdao extends ImBaseDao<DazhongCaterFirm> implements CantingDao {

}
