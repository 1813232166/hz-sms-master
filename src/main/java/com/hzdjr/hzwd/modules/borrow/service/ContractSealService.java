/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.borrow.dao.ContractSealDao;
import com.hzdjr.hzwd.modules.borrow.entity.ContractSeal;

/**
 * 签章列表Service
 * @author l
 * @version 2016-11-24
 */
@Service
@Transactional
public class ContractSealService extends CrudService<ContractSealDao, ContractSeal> {

	@Autowired
	private ContractSealDao contractSealDao;
	public ContractSeal get(String id) {
		return super.get(id);
	}
	
	public List<ContractSeal> findList(ContractSeal contractSeal) {
		return super.findList(contractSeal);
	}
	
	public Page<ContractSeal> findPage(Page<ContractSeal> page, ContractSeal contractSeal) {
		Page<ContractSeal> findPagePXCJ = findPagePXCJ(page, contractSeal);
		return findPagePXCJ;
	}
	
	

	@Transactional(readOnly = false)
	public void save(ContractSeal contractSeal) {
		super.save(contractSeal);
	}
	
	@Transactional(readOnly = false)
	public void delete(ContractSeal contractSeal) {
		super.delete(contractSeal);
	}
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @param contractType  PXCJ 普享标出借协议
	 * @return
	 */
	@Transactional(readOnly = false)
	public Page<ContractSeal> findPagePXCJ(Page<ContractSeal> page, ContractSeal entity) {
		entity.setPage(page);
		page.setList(contractSealDao.findListPXJK(entity));
		return page;
	}
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @param contractType PXJK普享标借款协议
	 * @return
	 */
	@Transactional(readOnly = false)
	public Page<ContractSeal> findPagePXJK(Page<ContractSeal> page, ContractSeal entity) {
		entity.setPage(page);
		page.setList(contractSealDao.findListPXJK(entity));
		return page;
	}
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @param contractType 无类型
	 * @return
	 */
	@Transactional(readOnly = false)
	private Page<ContractSeal> findPageNoType(Page<ContractSeal> page, ContractSeal entity) {
		entity.setPage(page);
		page.setList(contractSealDao.findPageNoType(entity));
		return page;
	}

	public List<ContractSeal> selectContractSealExcel(ContractSeal contractSeal) {
		return contractSealDao.findListPXJK(contractSeal);
	}
	/**
	 * 查询该标的签章状态
	 * @param borrowId
	 * @return 1:已签章,0:未签章
	 */
	public int queryContractSealStatus(String borrowId) {
		return contractSealDao.queryContractSealStatus(borrowId);
	}
	
	/**
	 * 签章信息入库
	 * @param borrowId
	 * @return 1:成功,0:失败
	 */
	public int recordContract(String referenceId,String contractType) {
		int flag=contractSealDao.isExistsRecord(referenceId,contractType);
		if(flag<1){
			logger.info(contractType+":"+referenceId+"不存在，插入");
			return contractSealDao.recordContract(referenceId,contractType);
		}
		logger.info(contractType+":"+referenceId+"存在");
		return 1;
	}
	
	/**
	 * 
	 * 放款确认后改状态和时间
	 */
	@Transactional(readOnly = false)
	public boolean updateBorrowState(String borrowId){
	    //放款确认后 更改t_borrow中的borrowstatus状态
		int status = contractSealDao.updateBorrowState(borrowId);
//		//放款确认后更改 t_borrow_billplan 中的repay_time
//		int  time = contractSealDao.updateBorrowBillTime(borrowId);	
		//放款确认后 修改 make_loan 为0  未提现
		int makeloan = contractSealDao.updateBorrowMakeloan(borrowId);
		if(status>0 &&makeloan>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * t_invest和t_borrow
	 * 
	 */
	@Transactional(readOnly = false)
	public List<Map<String,Object>> cancelBiao(String borrowId){
		//根据borrowId查询t_invest中的主键和amount
		List<Map<String, Object>> biaoList = contractSealDao.cancelBiao(borrowId);
		//修改t_borrow中的borrowSatus状态
		int status = contractSealDao.updateStatus(borrowId);
		
		return biaoList;
	}
	
	
}