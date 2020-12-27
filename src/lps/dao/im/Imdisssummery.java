package lps.dao.im;


import lps.base.ImBaseDao;
import lps.dao.CantingDao;
import lps.dao.DishDao;
import lps.dao.UserLogDao;
import lps.dao.dissummary;
import lps.entities.Canting;
import lps.entities.DazhongCaterDissSummary;
import lps.entities.DazhongDishRecommend;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;

import org.springframework.stereotype.Repository;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Repository("dishsummaryDao")
public class Imdisssummery extends ImBaseDao<DazhongCaterDissSummary> implements dissummary {

}
