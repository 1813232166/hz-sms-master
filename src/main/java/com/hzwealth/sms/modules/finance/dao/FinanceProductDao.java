package com.hzwealth.sms.modules.finance.dao;

import java.util.List;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.finance.entity.FinanceProduct;

/**
 * 出借产品DAO接口
 * @author FYP
 * @version 2017-06-28
 */
@MyBatisDao
public interface FinanceProductDao extends CrudDao<FinanceProduct> {
	public List<FinanceProduct>  findListNoXS();
}