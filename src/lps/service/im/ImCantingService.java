package lps.service.im;

import java.util.Date;

import javax.annotation.Resource;

import lps.base.BaseDao;
import lps.base.ImBaseService;
import lps.entities.Canting;
import lps.entities.DazhongCaterFirm;
import lps.entities.ElmCaterFirm;
import lps.entities.UserLog;
import lps.service.CantingService;
import lps.service.UserLogService;

import org.springframework.stereotype.Service;



/**
 * 用户日志
 * 
 * @author guild
 * 
 */
@Service("cantingService")
public class ImCantingService extends ImBaseService<DazhongCaterFirm> implements
		CantingService {

	@Resource(name = "cantingDao")
	public void setBaseDao(BaseDao<DazhongCaterFirm> baseDao) {
		super.setBaseDao(baseDao);
	}


}
