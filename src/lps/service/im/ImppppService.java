package lps.service.im;

import java.util.Date;

import javax.annotation.Resource;

import lps.base.BaseDao;
import lps.base.ImBaseService;
import lps.entities.Canting;
import lps.entities.DazhongCaterDish;
import lps.entities.DazhongCaterDisscus;
import lps.entities.DazhongCaterPromotion;
import lps.entities.DazhongDishRecommend;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;
import lps.service.CantingService;
import lps.service.DishService;
import lps.service.UserLogService;
import lps.service.pppp;

import org.springframework.stereotype.Service;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Service("ppppService")
public class ImppppService extends ImBaseService<DazhongCaterPromotion> implements
pppp{

	@Resource(name = "ppppDao")
	public void setBaseDao(BaseDao<DazhongCaterPromotion> baseDao) {
		super.setBaseDao(baseDao);
	}


}
