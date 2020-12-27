package lps.dao.im;


import lps.base.ImBaseDao;
import lps.dao.CantingDao;
import lps.dao.DishpinglunDao;
import lps.dao.UserLogDao;
import lps.entities.Canting;
import lps.entities.DazhongCaterPromotion;
import lps.entities.ElmCaterDishevaluate;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;

import org.springframework.stereotype.Repository;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Repository("dishpinglundao")
public class Imdishpinglundao extends ImBaseDao<DazhongCaterPromotion> implements DishpinglunDao {

}
