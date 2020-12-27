package lps.service.im;

import java.util.Date;

import javax.annotation.Resource;

import lps.base.BaseDao;
import lps.base.ImBaseService;
import lps.entities.Canting;
import lps.entities.DazhongCaterDish;
import lps.entities.DazhongCaterDissSummary;
import lps.entities.DazhongCaterDisscus;
import lps.entities.DazhongDishRecommend;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;
import lps.service.CantingService;
import lps.service.DishService;
import lps.service.DishsummeryService;
import lps.service.UserLogService;

import org.springframework.stereotype.Service;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Service("dishsummeryService")
public class ImDishsummeryService extends ImBaseService<DazhongCaterDissSummary> implements
DishsummeryService {

	@Resource(name = "dishsummaryDao")
	public void setBaseDao(BaseDao<DazhongCaterDissSummary> baseDao) {
		super.setBaseDao(baseDao);
	}


}
