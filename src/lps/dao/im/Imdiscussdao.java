package lps.dao.im;


import lps.base.ImBaseDao;
import lps.dao.CantingDao;
import lps.dao.DishDao;
import lps.dao.DisscussDao;
import lps.dao.UserLogDao;
import lps.entities.Canting;
import lps.entities.DazhongCaterDisscus;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterDisscus;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;

import org.springframework.stereotype.Repository;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Repository("disscussDao")
public class Imdiscussdao extends ImBaseDao<DazhongCaterDisscus> implements DisscussDao {

}
