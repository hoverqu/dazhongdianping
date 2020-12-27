package lps.service.im;

import java.util.Date;

import javax.annotation.Resource;

import lps.base.BaseDao;
import lps.base.ImBaseService;
import lps.entities.Canting;
import lps.entities.DazhongCaterDish;
import lps.entities.DazhongCaterDisscus;
import lps.entities.DazhongDishRecommend;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;
import lps.service.CantingService;
import lps.service.DishService;
import lps.service.UserLogService;

import org.springframework.stereotype.Service;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Service("dishService")
public class ImDishService extends ImBaseService<DazhongDishRecommend> implements
		DishService {

	@Resource(name = "dishDao")
	public void setBaseDao(BaseDao<DazhongDishRecommend> baseDao) {
		super.setBaseDao(baseDao);
	}


}
