package com.hzdjr.hzwd.modules.salesupport.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.buss.service.MessageModulesService;
import com.hzdjr.hzwd.modules.common.SendSms;
import com.hzdjr.hzwd.modules.salesupport.dao.CouponSendDao;
import com.hzdjr.hzwd.modules.salesupport.entity.CouponStatistics;
import com.hzdjr.hzwd.modules.salesupport.entity.ExcelCouponStatistics;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCoupon;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponAuditRecord;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponGroup;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponLog;
import com.hzdjr.hzwd.modules.salesupport.util.JDBCUtil;
import com.hzdjr.hzwd.modules.salesupport.util.SmsThread;
import com.hzdjr.hzwd.modules.sys.security.SystemAuthorizingRealm.Principal;

@Service
public class CouponSendService extends CrudService<CouponSendDao, TYxCouponGroup>{
    Logger logger = LoggerFactory.getLogger(CouponSendService.class);
    
    @Autowired
    private CouponSendDao couponSendDao;
    @Autowired
    private CouponAuditService couponAuditService;
    @Autowired
    private MessageModulesService messageModulesService;

    /**
     * 发放列表
     * @param page
     * @param couponGroup
     * @return
     */
    public Page<TYxCouponGroup> findCouponSendList(Page<TYxCouponGroup> page, TYxCouponGroup couponGroup) {
        couponGroup.setPage(page);
        return page.setList(couponSendDao.findCouponSendList(couponGroup));
    }

    public Page<TYxCouponGroup> findCouponSendAuditList(Page<TYxCouponGroup> page, TYxCouponGroup couponGroup) {
        couponGroup.setPage(page);
        return page.setList(couponSendDao.findCouponSendAuditList(couponGroup));
    }
    public void saveUpdateCoupon(TYxCouponGroup couponGroup,Principal principal) {
        if (StringUtils.isBlank(couponGroup.getCouponGroupId())) {
            couponGroup.setCouponGroupId(UUIDUtil.genUUIDString());
            couponGroup.setCreateby(principal.getId());
            couponGroup.setCreatedate(new Date());
            couponSendDao.insert(couponGroup);
        }else {
            couponGroup.setUpdateby(principal.getId());
            couponGroup.setUpdatedate(new Date());
            couponSendDao.update(couponGroup);
        }
    }

    /**
     * 提交审核
     * @param coupon
     * @return
     */
    @Transactional
    public int saveSubmitAuditCouponSend(TYxCouponGroup couponGroup,Principal principal){
        couponGroup.setAuditStatus("0");
        couponGroup.setUpdateby(principal.getId());
        int subAudit = couponSendDao.saveSubmitAuditCouponSend(couponGroup);
          subAudit = couponAuditService.saveAuditCouponRecord(this.converCouponGroup(couponGroup,principal));
        return subAudit;
    }
    /**
     * 审核
     * @param coupon
     * @return
     */
    @Transactional
    public int saveAuditCouponSend(TYxCouponGroup couponGroup,Principal principal) {
        couponGroup.setUpdateby(principal.getId());
        couponGroup.setCouponList(this.getRelCoupon(couponGroup.getCouponGroupId()));
        //更新t_yx_coupon_group 通过auditStatus=1更新authTime,sendTime;不通过更新authTime
        int updateAudit = couponSendDao.updateAuditCouponSend(couponGroup);
        couponAuditService.saveAuditCouponRecord(this.converCouponGroup(couponGroup,principal));
        if (null!=couponGroup && "1".equals(couponGroup.getAuditStatus())) {
            //id,mobile,coupon_group_id,coupon_group_name,status,createDate,createBy 
            String sql="INSERT INTO t_yx_coupon_log (id,mobile,coupon_group_id,coupon_group_name,status,createDate,createBy,coupon_id,coupon_type,end_time) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
            //批量保存 t_yx_coupon_log
            JDBCUtil jdbc = new JDBCUtil(sql);
            try {
                this.batchInser(jdbc,sql,couponGroup);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("批量插入营销日志异常"+e.getMessage());
            }finally{
                JDBCUtil.releaseDB(jdbc.conn, jdbc.pst, null);
                System.out.println(jdbc+"  conn: "+jdbc.conn);
            }
        }
        return updateAudit;
    }
    /**
     * 详情统计
     * @param page
     * @param couponStatistics
     * @return
     */
    public Page<CouponStatistics> couponStatistics(Page<CouponStatistics> page,CouponStatistics couponStatistics) {
        couponStatistics.setPage(page);
        return page.setList(couponSendDao.couponStatistics(couponStatistics));
    }

    public List<CouponStatistics> findAllCoouCouponStatistics(CouponStatistics couponStatistics){
        return couponSendDao.couponStatistics(couponStatistics);
    }
    
    
    /**
     * 查询所有的优惠券关联用户的统计
     * 活动详情页面使用
     * @author zhouzhankui
     * @param excelCouponStatistics
     * @return
     */
    public    List<ExcelCouponStatistics> getAllCouponStatistics(ExcelCouponStatistics excelCouponStatistics){
        return couponSendDao.getAllCouponStatistics(excelCouponStatistics);
    }
    
    
    
    
    
    /**
     * 根据优惠券组id 查看优惠券
     * @param couponGroupId
     * @return
     */
    public List<TYxCoupon> getRelCoupon(String couponGroupId) {
    	
    	List<TYxCoupon> couponList=couponSendDao.getRelCoupon(couponGroupId);
    	for (TYxCoupon tYxCoupon : couponList) {
		        if (StringUtils.isBlank(tYxCoupon.getEffectiveDays())) {
		        	tYxCoupon.setEffectiveDays("0");
				} if (null==tYxCoupon.getLoanAmountSum() || tYxCoupon.getLoanAmountSum().equals("")) {
					tYxCoupon.setLoanAmountSum(BigDecimal.valueOf(0));
				}
		}
       
        return couponList;
    }

   
    /**
     * 保存信息
     * @param coupon
     * @return
     */
	@Transactional(readOnly = false)
    public int saveTYxCouponGroupInfo(TYxCouponGroup coupon) {
        return couponSendDao.saveTYxCouponGroupInfo(coupon);
    }
	
	/**
	 * 删除优惠券组信息
	 * @param couponGroupId
	 * @return
	 */
	@Transactional(readOnly = false)
    public int deleteTYxCouponGroupInfo(String couponGroupId) {
        return couponSendDao.deleteTYxCouponGroupInfo(couponGroupId);
    }
	/**
	 * 用户总数
	 * @return 用户数
	 */
	public Map<String,Long> findUserCount(){
	    return couponSendDao.findUserCount();
	}
	/**
	 * 用户已使用的优惠券
	 * @param userMobile
	 * @return
	 */
	 public List<Map<String, Object>> getUsedCoupon(CouponStatistics couponStatistics) {
	        return couponSendDao.getUsedCoupon(couponStatistics);
	    }
	 //转为审核记录对象
    public TYxCouponAuditRecord converCouponGroup(TYxCouponGroup couponGroup,Principal principal){
        TYxCouponAuditRecord auditRecord=new TYxCouponAuditRecord();
        if (null!=couponGroup.getAuditStatus() && "0".equals(couponGroup.getAuditStatus())) {//提交审核
            auditRecord.setSubmitterId(principal.getId());
        }else {//审核
           auditRecord.setAuditorId(principal.getId());
        }
        auditRecord.setMainId(couponGroup.getCouponGroupId());;
        auditRecord.setRelationType("group");
        auditRecord.setCreateby(principal.getId());
        auditRecord.setAuditContent(couponGroup.getRemarks());
        auditRecord.setRemarks(couponGroup.getRemarks());
        auditRecord.setAuditFlag(couponGroup.getAuditStatus());
        return auditRecord;
    }
    
    //转为t_yx_coupon_log
    public TYxCouponLog convertCouponLog(TYxCouponGroup couponGroup){
        TYxCouponLog couponLog = new TYxCouponLog();
        couponLog.setCouponIds(couponGroup.getCouponIds());
        couponLog.setCouponGroupId(couponGroup.getCouponGroupId());
        couponLog.setCouponGroupName(couponGroup.getCouponGroupName());
        couponLog.setCouponList(couponGroup.getCouponList());
        couponLog.setStatus("0");
        couponLog.setCreateBy(couponGroup.getUpdateby());
        return couponLog;
    }

    //批量插入  
   // @Transactional(propagation=Propagation.NESTED)
    public boolean batchInser(JDBCUtil jdbc,String sql,TYxCouponGroup couponGroup) throws Exception{
        boolean flag=true;
        List<String> mobileList =null;
        TYxCouponLog convertCouponLog = this.convertCouponLog(couponGroup);
        Connection  connection = jdbc.conn;
        PreparedStatement  ps = jdbc.pst;
        System.out.println("优惠券发送用户 mobilseList: "+couponGroup.getMobilseList());
            if ("some".equals(couponGroup.getHolderType())&&(couponGroup.getMobilseList().trim().length()>1)) {
                mobileList=Arrays.asList(couponGroup.getMobilseList().split(";"));
                flag = jdbc.batchInsertCouponLog(connection,ps,convertCouponLog,mobileList);
                //调用发送短信
                if ("1".equals(couponGroup.getIsSendMessage())&&(couponGroup.getMessageContent().trim().length()>0)) {
                   new SmsThread(couponGroup.getMessageContent(),mobileList,"03").start();
                }
            }
            if ("all".equals(couponGroup.getHolderType())) {
                new CouponSendThread(couponGroup,sql).start();
            }
        
        return flag;
    }
    //获取模板  发送短信 
    public void sealSmsData(String mobileList,String messageContent){
        Map<String,Object> mapObjct = new HashMap<String,Object>();
        try {
            mapObjct.put("receiver", "3");      
            mapObjct.put("message_type", "10"); 
            Map<String,Object> messageObject = messageModulesService.getMessage(mapObjct);
            if(messageObject!=null){
                String  message_content = (String)messageObject.get("message_content");
                String  message_status = (String)messageObject.get("message_status");
                if("1".equals(message_status)){
                    String  name = "张三";
                    String  sex = "1";
                    if("0".equals(sex)) sex = "女士";
                    if("1".equals(sex)) sex = "先生";
                    message_content = message_content.replace("[title]", "".replace("[name]", name+sex));
                    SendSms.sendSmsMesage(message_content, mobileList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发送短信异常："+e.getMessage());
        }
    }
    /**
     * 发送给所有用户优惠券组的线程
     * @author xq
     *
     */
    class CouponSendThread extends Thread{
        TYxCouponGroup couponGroup;
        String sql;
        public CouponSendThread(TYxCouponGroup couponGroup,String sql){
            this.couponGroup=couponGroup;
            this.sql=sql;
        }
        @Override
        public void run() {
            Date begin= new Date();
            logger.info("优惠券组所有用户发送开始……time：{}",begin);
            //批量保存 t_yx_coupon_log
            Map<String, Integer> parMap=new HashMap<String, Integer>();
            Integer pageSize=50000;//每次提交手机号量
            parMap.put("pageSize", pageSize);
            try {
                CouponSendService couponSendService= new CouponSendService();
                List<String> mobileList =null;
                List<String> allMobileList =new ArrayList<String>();
                TYxCouponLog convertCouponLog = couponSendService.convertCouponLog(couponGroup);
                //获取mobile， 查询用户表次数
                Long pageCount = couponSendDao.findUserCount().get("totalUser")/pageSize;
                for (int i = 0; i < pageCount+1; i++) {
                    parMap.put("pageIndex", i*pageSize);
                    mobileList = couponSendDao.findUserMobile(parMap);
                    new insertLogThread(sql,convertCouponLog,mobileList).start();
                   allMobileList.addAll(mobileList);
                }
                if ("1".equals(couponGroup.getIsSendMessage())&&(couponGroup.getMessageContent().trim().length()>0)) {
                    logger.info("发送短信  "+allMobileList.size()+" 条");
                   new SmsThread(couponGroup.getMessageContent(),allMobileList,"03").start();
                }
            } catch (Exception e) {
                logger.info("优惠券组所有用户发送异常："+e.getMessage());
                e.printStackTrace();
            }
            Long spendTime =(System.currentTimeMillis()-begin.getTime())/1000;
            logger.info("优惠券组所有用户发送结束……time：{}",new Date(),"……共用时："+spendTime+" 秒");
        }
    }//class CouponSendThread 
    /**
     * 插入t_yx_coupon_log线程
     * @author xq
     *
     */
    class insertLogThread extends Thread{
        String sql;
        TYxCouponLog convertCouponLog;
        List<String> mobileList;
        public insertLogThread(String sql,TYxCouponLog convertCouponLog,List<String> mobileList){
            this.sql=sql;
            this.convertCouponLog=convertCouponLog;
            this.mobileList=mobileList;
        }
        @Override
        public void run() {
            JDBCUtil jdbc = new JDBCUtil(sql);
            try {
                jdbc.batchInsertCouponLog(jdbc.conn,jdbc.pst,convertCouponLog,mobileList);
            } catch (SQLException e) {
                logger.info("线程"+Thread.currentThread().getName()+"异常;数据从");
                e.printStackTrace();
            }finally{
                JDBCUtil.releaseDB(jdbc.conn,jdbc.pst, null);
            }
        }
        
    }
    
}
