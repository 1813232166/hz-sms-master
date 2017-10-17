/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrowlist.service;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.borrow.entity.Borrow;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.borrow.util.BorrowAliasUtils;
import com.hzwealth.sms.modules.borrowlist.dao.TBorrowListDao;
import com.hzwealth.sms.modules.borrowlist.entity.TBorrowList;
import com.hzwealth.sms.modules.invest.entity.Invest;

/**
 * 散标集Service
 * @author xq
 * @version 2017-5-4
 */
@Service
@Transactional
public class TBorrowListService extends CrudService<TBorrowListDao, TBorrowList> {

    @Autowired
    private TBorrowListDao tborrowListDao;
	
    @Override
    public TBorrowList get(String id) {
       
        return super.get(id);
    }

    @Override
    public TBorrowList get(TBorrowList entity) {
       
        return super.get(entity);
    }

    @Override
    public List<TBorrowList> findList(TBorrowList entity) {
       
        return super.findList(entity);
    }

    @Override
    public Page<TBorrowList> findPage(Page<TBorrowList> page, TBorrowList entity) {
        Page<TBorrowList> newPage = super.findPage(page, entity);
        List<TBorrowList> pageList = newPage.getList();
        List<String> blistIdS=new ArrayList<String>();
        for (TBorrowList tBorrowList : pageList) {
            blistIdS.add(tBorrowList.getBorrowListId());
        }
        //关联数据
        List<TBorrowList> tbList = tborrowListDao.findRelateList(blistIdS);
        Iterator<TBorrowList> iterator = tbList.iterator();
        if (null!=tbList&&tbList.size()>0) {
            while (iterator.hasNext()){
               this.judgeTBorrowListStatusAndSum(iterator.next());
               /* 
                TBorrowList judgeList = this.judgeTBorrowListStatusAndSum(iterator.next());
                //查询状态条件过滤   null!=entity&&null!=entity.getBorrowListStatus()&&(!"".equals(entity.getBorrowListStatus()))
                if (null!=entity&&null!=entity.getBorrowListStatus()&&(!"".equals(entity.getBorrowListStatus()))&&
                        (!judgeList.getBorrowListStatus().equals(entity.getBorrowListStatus()))) {
                    //pageList.remove(iterator.next());
                    iterator.remove();
                }
                */
            }
        }
        Collections.sort(tbList);
       // this.sortList(tbList);
        newPage.setList(tbList);
        return newPage;
    }

    /**
     * 判断散标集状态及计算借款总额
     * @param borrowList 一个散标集
     * @return  判断后的散标集
     */
    public TBorrowList judgeTBorrowListStatusAndSum(TBorrowList borrowList){
        Set<TBorrow> tBorrows = borrowList.gettBorrow();
        String borrowListStatus = borrowList.getBorrowListStatus();
        if (null==tBorrows || tBorrows.size()<1) {
            return borrowList;
        }
        Iterator<TBorrow> it = tBorrows.iterator();
      //判断散标集状态
        Date curreDate=new Date();
        //1待审核;2审核未通过;3审核中;4审核通过;5、预发布;6、招标中;7、已满标;8、还款中;9、已逾期;10、已结清;11、已流标;12、已撤销;
        if ("1".equals(borrowListStatus)) {
            borrowList.setStatusMemo("待审核");
        }
        if("2".equals(borrowListStatus)){
            borrowList.setStatusMemo("审核未通过");
        }
        if ("3".equals(borrowListStatus)) {
            borrowList.setStatusMemo("审核中");
        } 
        if ("12".equals(borrowListStatus)) {
            borrowList.setStatusMemo("已撤销");
        } 
        if ("4".equals(borrowListStatus) && null!=borrowList.getOpenTime()) {
                if (borrowList.getOpenTime().getTime()>curreDate.getTime()) {
                    borrowList.setBorrowListStatus("5");
                    borrowList.setStatusMemo("预发布");
                }
        }
            int full=0;//已满标，
            int repay=0;//还款中
            int settle=0;//已结清
            int noBit=0; //已流标
            while (it.hasNext()){
               TBorrow tBorrow = it.next();
               //散标状态
               String borrowstatus = tBorrow.getBorrowstatus();
               if ("4".equals(borrowListStatus) && null!=borrowList.getOpenTime()) {
                   //10 已满标 ;11 还款中;12 已还清
                   if ("10".equals(borrowstatus)) {
                       full++;
                   }
                   if ("11".equals(borrowstatus)) {
                       repay++;
                   }
                   if ("12".equals(borrowstatus)) {
                       settle++;
                   }
                  
                   if ("9".equals(borrowstatus)) {
                       borrowList.setBorrowListStatus("6");
                       borrowList.setStatusMemo("招标中");
                   }
               }
               if ("16".equals(borrowstatus)) {
                   noBit++;
               }
             
            } //while
            // 全满标;包含已满标、还款中、已结清、已流标是已满标;
            if (full==tBorrows.size() || (full+repay+settle+noBit)==tBorrows.size()) {
                borrowList.setBorrowListStatus("7");
                borrowList.setStatusMemo("已满标");
            }
            if (repay==tBorrows.size() || (repay+settle+noBit)==tBorrows.size()) {
                borrowList.setBorrowListStatus("8");
                borrowList.setStatusMemo("还款中");
            }
            List<TBorrowBillplan> billplans = tborrowListDao.findTBorrowBillplans(tBorrows);
            if (null!=billplans && billplans.size()>0) {
                borrowList.setBorrowListStatus("9");
                borrowList.setStatusMemo("已逾期");
            }
            if (settle==tBorrows.size() || (settle+noBit)==tBorrows.size()) {
                borrowList.setBorrowListStatus("10");
                borrowList.setStatusMemo("已结清");
            }
            if (noBit==tBorrows.size()) {
                borrowList.setBorrowListStatus("11");
                borrowList.setStatusMemo("已流标");
            }
           
        return borrowList;
    }
    /**
     * 散标集合排序
     * @param list
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<TBorrowList> sortList(List<TBorrowList> list){
        Comparator mycmp = ComparableComparator.getInstance();
        mycmp = ComparatorUtils.nullLowComparator(mycmp);  //允许null  
        mycmp = ComparatorUtils.reversedComparator(mycmp); //逆序  
        Comparator<Object> comparator=Collator.getInstance();
        ArrayList<Object> sortFields = new ArrayList<Object>(); 
        sortFields.add(new BeanComparator("borrowListStatus",comparator));  // 
        sortFields.add(new BeanComparator("openTime", mycmp));     // 
        ComparatorChain multiSort = new ComparatorChain(sortFields);  
        Collections.sort(list,multiSort);  
        return list;
    }
    @Override
    public void save(TBorrowList entity) {
       
        super.save(entity);
    }

    @Override
    public void delete(TBorrowList entity) {
       
        super.delete(entity);
    }
    public int updatePerBorrowStatus(Set<TBorrow> borrows){
        return tborrowListDao.updatePerBorrowStatus(borrows);
    }
    
    public Map<String,Object>  findBorrowListSum(TBorrowList tBorrowList){
        return tborrowListDao.findBorrowListSum(tBorrowList);
    }
    
    public List<TBorrowList> exportBorrowList(TBorrowList entity){
        List<TBorrowList> tbList = tborrowListDao.exportBorrowList(entity);
        Iterator<TBorrowList> iterator = tbList.iterator();
        if (null!=tbList&&tbList.size()>0) {
            while (iterator.hasNext()){
                TBorrowList judgeList = this.judgeTBorrowListStatusAndSum(iterator.next());
                //查询状态条件过滤   null!=entity&&null!=entity.getBorrowListStatus()&&(!"".equals(entity.getBorrowListStatus()))
                if (null!=entity&&null!=entity.getBorrowListStatus()&&(!"".equals(entity.getBorrowListStatus()))&&
                        (!judgeList.getBorrowListStatus().equals(entity.getBorrowListStatus()))) {
                    iterator.remove();
                }
            }
        }
        Collections.sort(tbList);
        //this.sortList(tbList);
        return tbList;
    }
    
  public List<TBorrow> getTBorrow(String id) {
    return dao.getTBorrow(id);
  }

  public Page<Invest> getInvRecByBid(Page<Invest> page, String id, Invest invest) {
    invest.setPage(page);
    page.setList(dao.getInvRecByBid(id));
    return page;
  }
  public BigDecimal findcountMount(TBorrow tBorrow) {
		// TODO Auto-generated method stub
		return dao.findcountMount(tBorrow);
	}
  public Page<TBorrow> findNewPage(Page<TBorrow> page, TBorrow tBorrow) {
		tBorrow.setPage(page);
		page.setList(dao.findNewBorrowList(tBorrow));
		return page;
	}
  
	/**
	 * 
	 * @Title: findLoans   
	 * @Description: 查询待打包散标  
	 * @param borrowIdList
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 10:23:22 AM
	 */
  	public List<Borrow> findLoans(List<String> borrowCodeList){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("borrowCodeList", borrowCodeList);
		List<String> borrowStatusList = new ArrayList<String>();
		borrowStatusList.add("6");
//		borrowStatusList.add("8");
		paramMap.put("borrowStatusList", borrowStatusList);
		List<Borrow> loans = tborrowListDao.findLoanByBorrowsCodes(paramMap);
		return loans;
	}
	
  	/**
  	 * 
  	 * @Title: sumLoanTotal   
  	 * @Description: 计算出借总金额 
  	 * @param loans
  	 * @return        
  	 * @throws   
  	 * @author zhf
  	 * @date May 9, 2017 10:23:44 AM
  	 */
	public BigDecimal sumLoanTotal(List<Borrow> loans){
		BigDecimal loanTotal = new BigDecimal(0);
		for (Borrow borrow : loans) {
			BigDecimal borrowAmont = new BigDecimal(borrow.getBorrowamount());
			loanTotal = loanTotal.add(borrowAmont);
		}
		return loanTotal;
	}
	
	/**
	 * 
	 * @Title: parseBorrowList   
	 * @Description: 转换成散标集，显示使用
	 * @param borrowIdList
	 * @param riskWarning
	 * @param usageofloan
	 * @param raiseterm
	 * @param minTenderSum
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 10:24:10 AM
	 */
	public TBorrowList parseBorrowList(List<String> borrowCodeList, String riskWarning, String usageofloan,
			String raiseterm, String minTenderSum){
		List<Borrow> loans = findLoans(borrowCodeList);
		BigDecimal loanTotal = new BigDecimal(0);
		for(Borrow borrow: loans){
			String borrowNo = BorrowAliasUtils.getBorrowNoWithoutName();
			borrow.setBorrowaliasno("PX" + borrowNo);
			borrow.setBorrowalias(borrowNo);//保存使用
			loanTotal = loanTotal.add(new BigDecimal(borrow.getBorrowamount()));
		}
		
		String borrowListNo = BorrowAliasUtils.getBorrowListNo();
		Borrow borrow = loans.get(0);
		TBorrowList borrowList = initBorrowList(borrowListNo, loanTotal, borrow, riskWarning, usageofloan, raiseterm, minTenderSum);
		borrowList.setBorrowListNo(borrowListNo);// 散标集序号（拼散标集编号、散标集名称使用）
		borrowList.setBorrows(loans);
		return borrowList;
	}
	
	/**
	 * 
	 * @Title: createBorrowList   
	 * @Description: 发布散标集 
	 * @param borrowCodeList
	 * @param borrowNos
	 * @param riskWarning
	 * @param usageofloan
	 * @param raiseterm
	 * @param minTenderSum
	 * @param borrowListNo        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 3:27:27 PM
	 */
	public void createBorrowList(List<String> borrowCodeList, String[] borrowNos, String riskWarning, 
			String usageofloan, String raiseterm, String minTenderSum, String borrowListNo){
		List<Borrow> loans = findLoans(borrowCodeList);
		
		String borrowListId = UUID.randomUUID().toString().replace("-", "");
		BigDecimal loanTotal = new BigDecimal(0);
		for(int i = 0; i < loans.size(); i++){
			Borrow borrow = loans.get(i); 
			String borrowNo = borrowNos[i];
			borrow.setBorrowaliasno("PX" + borrowNo);// 标的中文别名编号
			borrow.setBorrowalias("普享标" + borrowNo);// 标的中文别名
			borrow.setBorrowtitle("普享标" + borrowNo);// 借款标题
			borrow.setRiskWarning(riskWarning);// 风险提示
			borrow.setProjectBrief(usageofloan);// 项目介绍
			borrow.setRaiseterm(raiseterm);// 筹标期限(募集期)
			borrow.setRaisetermunit("1");// 筹标期限单位（0小时 1天）
			borrow.setMintendersum(minTenderSum);// 起投金额
			borrow.setMaxtendersum(borrow.getBorrowamount());// 最大投资金额
			borrow.setBorrowstatus("18");// 标的状态 已包装
			borrow.setBorrowListId(borrowListId);// 普享标集合id
			
//			borrow.setIsMatch("1");// 是否匹配 0是未匹配 1是匹配
//			borrow.setFillMark("0");// 是否补标 0不补 1补标
//			borrow.setOpenborrowdate(new Date());// 开标时间
			
			loanTotal = loanTotal.add(new BigDecimal(borrow.getBorrowamount()));
		}
		int releaseBorrows = tborrowListDao.releaseBorrows(loans);
		System.out.println("releaseBorrows：" + releaseBorrows);
		
		Borrow borrow = loans.get(0);
		TBorrowList borrowList = initBorrowList(borrowListNo, loanTotal, borrow, riskWarning, usageofloan, raiseterm, minTenderSum);
		borrowList.setBorrowListId(borrowListId);
		borrowList.setCreateTime(new Date());
		borrowList.setBorrowListStatus("1");// 待审核
		int releaseBorrowList = tborrowListDao.releaseBorrowList(borrowList);
		System.out.println("releaseBorrowList:" + releaseBorrowList);
	}
	
	/**
	 * 
	 * @Title: initBorrowList   
	 * @Description: 初始化散标集  
	 * @param borrowListNo
	 * @param loanTotal
	 * @param borrow
	 * @param riskWarning
	 * @param usageofloan
	 * @param raiseterm
	 * @param minTenderSum
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 3:28:30 PM
	 */
	private TBorrowList initBorrowList(String borrowListNo, BigDecimal loanTotal, Borrow borrow, 
			String riskWarning, String usageofloan, String raiseterm, String minTenderSum){
		TBorrowList borrowList = new TBorrowList();
		borrowList.setBorrowListCode("PXJH" + borrowListNo);// 标的编号
		borrowList.setBorrowListTitle("普享标集合" + borrowListNo);// 标的名称
		borrowList.setBorrowListAmount(loanTotal);// 借款金额
		borrowList.setAnualRate(borrow.getAnualrate());// 出借年化利率
		borrowList.setDeadLine(borrow.getDeadline());// 借款期限
		borrowList.setPayMethod(borrow.getPayMethod());// 还款方式(debx等额本息 xxhb先息后本ychbx一次性还本付息)
		borrowList.setRiskWarning(riskWarning);// 风险提示
		borrowList.setProjectBrief(usageofloan);// 项目介绍
		borrowList.setRaiseDay(raiseterm);// 募集期
		borrowList.setMinTenderSum(new BigDecimal(minTenderSum));// 起投金额
		return borrowList;
	}
}
