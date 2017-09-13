package com.hzdjr.hzwd.modules.financialadmis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.financialadmis.dao.PlatformDoorDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.Overdue;

@Service
@Transactional(readOnly = true)
public class PlatformDoorService extends CrudService<PlatformDoorDao, TBorrow> {
    @Autowired
    private PlatformDoorDao platformDao;
    
    @Override
    public TBorrow get(String id) {
        
        return super.get(id);
    }

    @Override
    public TBorrow get(TBorrow entity) {
        
        return super.get(entity);
    }

    @Override
    public List<TBorrow> findList(TBorrow entity) {
        
        return super.findList(entity);
    }

    @Override
    public Page<TBorrow> findPage(Page<TBorrow> page, TBorrow entity) {
        
        return super.findPage(page, entity);
    }

    @Override
    public void save(TBorrow entity) {
        
        super.save(entity);
    }

    @Override
    public void delete(TBorrow entity) {
        
        super.delete(entity);
    }

    public List<TBorrow> exportList(Map<String,Object> map){
        return platformDao.exportList(map);
    }
    public Map<String, Object> findcountMount(TBorrow platformdoor){
        return platformDao.findcountMount(platformdoor);
    }

    public Page<Overdue> findPayinList(Page<Overdue> page,Overdue overdue) {
        overdue.setPage(page);
        Page<Overdue> overdueList = page.setList(platformDao.findPayinList(overdue));
        return overdueList;
    }

    public Map<String, Object> findAdvanceCountMount(Overdue overdue) {
        return platformDao.findAdvanceCountMount(overdue);
    }
    
}
