/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.ContractSeal;

/**
 * 签章列表DAO接口
 * @author l
 * @version 2016-11-24
 */
@MyBatisDao
public interface ContractSealDao extends CrudDao<ContractSeal> {

	/**
	 * @param ContractSeal PXCJ 普享标出借协议
	 * */
	List<ContractSeal> findListPXCJ(ContractSeal entity);
	/**
	 * @param contractType PXJK普享标借款协议
	 * */
	List<ContractSeal> findListPXJK(ContractSeal entity);
	/**
	 * @param contractType 无类型普享标借款协议
	 * */
	List<ContractSeal> findPageNoType(ContractSeal entity);
	
	List<ContractSeal> selectContractSealExcel(ContractSeal contractSeal);
	
	int queryContractSealStatus(String borrowId);
	
	int updateBorrowState(String borrowId);
	
	int updateBorrowBillTime(String borrowId);
	
	List<Map<String,Object>> cancelBiao(String borrowId);
	
	int updateStatus(String borrowId);
	
    int updateBorrowMakeloan(String borrowId);
	
    public int recordContract(String referenceId,String contractType);
	
	public int isExistsRecord(String referenceId,String contractType);

	
}