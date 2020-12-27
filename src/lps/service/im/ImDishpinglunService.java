package lps.service.im;

import java.util.Date;

import javax.annotation.Resource;

import lps.base.BaseDao;
import lps.base.ImBaseService;
import lps.entities.Canting;
import lps.entities.DazhongCaterPromotion;
import lps.entities.ElmCaterDishevaluate;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;
import lps.service.CantingService;
import lps.service.DishpinglunService;
import lps.service.UserLogService;

import org.springframework.stereotype.Service;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Service("dishpinglunService")
public class ImDishpinglunService extends ImBaseService<DazhongCaterPromotion> implements
		DishpinglunService {

	@Resource(name = "dishpinglundao")
	public void setBaseDao(BaseDao<DazhongCaterPromotion> baseDao) {
		super.setBaseDao(baseDao);
	}


}
