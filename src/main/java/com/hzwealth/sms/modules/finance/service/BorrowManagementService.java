package com.hzwealth.sms.modules.finance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.finance.dao.BorrowManagementDao;
import com.hzwealth.sms.modules.finance.entity.BorrowManagementVo;

/**
 * 散标管理Service
 * @author HDG
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class BorrowManagementService extends CrudService<BorrowManagementDao, BorrowManagementVo> {

}
