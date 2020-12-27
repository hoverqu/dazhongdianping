package lps.service.im;

import java.util.Date;

import javax.annotation.Resource;

import lps.base.BaseDao;
import lps.base.ImBaseService;
import lps.entities.Canting;
import lps.entities.DazhongCaterDisscus;
import lps.entities.ElmCaterDish;
import lps.entities.ElmCaterDisscus;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;
import lps.service.CantingService;
import lps.service.DishService;
import lps.service.DisscussService;
import lps.service.UserLogService;

import org.springframework.stereotype.Service;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Service("disscussService")
public class ImDisscussService extends ImBaseService<DazhongCaterDisscus> implements
		DisscussService {

	@Resource(name = "disscussDao")
	public void setBaseDao(BaseDao<DazhongCaterDisscus> baseDao) {
		super.setBaseDao(baseDao);
	}


}
