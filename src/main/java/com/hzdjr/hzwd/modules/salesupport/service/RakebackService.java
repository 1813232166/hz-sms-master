
package com.hzdjr.hzwd.modules.salesupport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.content.entity.Article;
import com.hzdjr.hzwd.modules.salesupport.dao.RakebackDao;
import com.hzdjr.hzwd.modules.salesupport.entity.Rakeback;
import com.hzdjr.hzwd.modules.salesupport.entity.Rakeback2;
import com.hzdjr.hzwd.modules.usermanage.entity.TUser;
import com.hzdjr.hzwd.modules.content.dao.ArticleDao;

/**
 * 返佣设置Service
 * 
 * @author 
 * @version 
 */
@Service
public class RakebackService {

	@Autowired
	RakebackDao rakebackDao;

	// 查询预览（根据ID查询返佣）
	public Rakeback getRakebackPerview(String id) {

		return rakebackDao.getRakebackPerview(id);

	}
   public  List<Rakeback> getRakebackList(Map<String, Object> paramMap) {
		
		return rakebackDao.getRakebackList(paramMap);
	}
	
	
	
	public int updatestaus(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return rakebackDao.updateStatus(paramMap);
	}
	@Transactional(readOnly = false)
	public int save(Rakeback rakeback) {
		return rakebackDao.insert1(rakeback);
		
	}
	public int updateRakeback(Rakeback rakeback) {
		return rakebackDao.updateRakeback(rakeback);
	}
	public int delrakeback(String id) {
		return rakebackDao.delrakeback(id);
		
	}
	public String getCount(String status) {
		String amount=null ;
		if(!"".equals(status)){
			if("2".equals(status)){
				amount=rakebackDao.selectCount();	
		     }else if("1".equals(status)){
			amount=rakebackDao.selctCount();
		     }
		}
		return amount;
	}
	
}