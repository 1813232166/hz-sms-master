package com.hzdjr.hzwd.modules.finance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.finance.dao.BorrowManagementDao;
import com.hzdjr.hzwd.modules.finance.entity.BorrowManagementVo;

/**
 * 散标管理Service
 * @author HDG
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class BorrowManagementService extends CrudService<BorrowManagementDao, BorrowManagementVo> {

}
