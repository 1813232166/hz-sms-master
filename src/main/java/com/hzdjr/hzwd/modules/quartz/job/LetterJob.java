package com.hzdjr.hzwd.modules.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.borrow.service.TBorrowService;
import com.hzdjr.hzwd.modules.common.BussinessPayHx;
import com.hzdjr.hzwd.modules.common.ReqUrlPreUUID;
import com.hzdjr.hzwd.modules.fk.service.FkuanService;


/**
 * 对募集期进行判断，然后查询日志表，调用华兴请求流标动作请求接口
 * @author luwei
 *
 */
public class LetterJob extends QuartzJobBean {
	private final Logger logger = LoggerFactory.getLogger(LetterJob.class);

    @Autowired
    private TBorrowService tBorrowService;
    @Autowired
	private FkuanService fkuanService;

	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("开始定时器调用流标动作请求接口，time：{}",new Date());
		//取出系统中的   OPENBORROWDATE（开标时间）   RAISETERM (筹标期限(募集期))   RAISETERMUNIT（筹标期限单位（0小时 1天））
		//根据当前时间减去开标时间大于零。同时状态是9的情况，如果得到的结果大于募集期，置为流标16
		List<TBorrow> borrowList = tBorrowService.findBorrowByJob();
		for(int i = 0; borrowList!=null && i < borrowList.size() ; i++ ){
			int hour = 0 ;
			String raiseterm = borrowList.get(i).getRaiseterm();
			String raisetermunit = borrowList.get(i).getRaisetermunit();
			if("0".equals(raisetermunit)){//小时
				hour = Integer.parseInt(raiseterm);
			}else{//天
				hour = Integer.parseInt(raiseterm)*24;
			}
			String  getTime = getCurrentTime(hour);//当前时间减去募集期时间得到一个时间值
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String  openTime = sdf.format(borrowList.get(i).getOpenborrowdate());//募集时间
			long timeDistince = getDistanceSeconds(openTime,getTime);
			if(timeDistince>0){//时间差满足业务条件
//				String borrowId = borrowList.get(i).getBorrowId();
				
//				String borrowCode = borrowList.get(i).getBorrowcode();
//				String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000));//UUIDUtil.genUUIDString();//每回生成新的请求流水号
				HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
				String borrowCode = borrowList.get(i).getBorrowcode();
				int isExist = fkuanService.isExistBorrow(borrowCode);
				if(isExist==0){//数据库无记录进行处理
					String code="R2039";
					ReqUrlPreUUID reqUrl = new ReqUrlPreUUID();
					String requestNo = reqUrl.GetRequestNo("2039");
					objectHashMapObject.put("LOANNO", borrowCode);
					objectHashMapObject.put("requestNo", requestNo);
					String jsonToStr = JSONArray.toJSONString(objectHashMapObject);
					logger.debug("定时器流标动作请求接口数据=======>"+jsonToStr);
					HashMap<String,Object> reqMapCb = BussinessPayHx.reqObject("R2039", "v0001", jsonToStr, "PC");
					String resString = "";
					try {
						resString = BussinessPayHx.getLb(reqMapCb);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						logger.debug("定时器调用流标动作请求接口异常：{}",borrowCode);
					}
					try {
					logger.debug("定时器流标动作请求接口返回数据=======>"+resString);
					JSONObject  mapLbObject = JSONObject.parseObject(resString);
					 String errorCode= (String)mapLbObject.get("errorCode");
					 if(errorCode!=null && !"0".equals(errorCode)){//失败
						    //fkuanService.deleteFkQuery(borrowList.get(i).getUserid(), requestNo, code, borrowCode);
							logger.debug("定时器调用流标动作请求接口失败");
					 }
					 if("0".equals(errorCode)){//(成功的情况)如果成功,更新数据库为1,如果处理中不做处理，如果处理失败删除数据库
							HashMap<String,Object> map = new HashMap<String,Object>();
							map.put("code", "R2039");
							map.put("userId", borrowList.get(i).getUserid());
							map.put("requestNo", requestNo);
							map.put("status", "0");
							Date date = new Date();
							String cdate = sdf.format(date);
							map.put("create_date", cdate);
							map.put("borrowCode", borrowCode);
							tBorrowService.updateLbIng(borrowCode);//更新流标中14的状态
							fkuanService.insertFkQuery(map);
						}
						//调用接口
//						BalancePay.fkCancelXS(borrowList.get(i).getBorrowcode());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						logger.debug("定时器调用流标动作请求接口失败：{}",borrowCode);
					}
					//tBorrowService.changeBorrowstatus("9", borrowCode);
					logger.debug("定时器更新流标动作请求接口,标的编号：{}",borrowCode);
					
				}
				
/*				List<Invest> investObject = investService.findInvestById(borrowId);
//				List<HashMap<String,Object>> listObject = new ArrayList<HashMap<String,Object>>();
				//投标流水号bizid修改flag=3
				if(investObject!=null&&investObject.size()>0){
					for (int j = 0; j < investObject.size(); j++) {
//						HashMap<String,Object> hashMap = new HashMap<String,Object>();
						String bizId = investObject.get(j).getBizid();
						investService.updateInvestFlag(bizId);
//						String amount = investObject.get(j).getInvestamount();
//						String investor = investObject.get(j).getInvestor();
//						hashMap.put("preTransactionNo", id);
//						hashMap.put("amount", amount);
//						hashMap.put("userId", investor);
//						listObject.add(hashMap);
					}
				}
//				objectHashMapObject.put("preTransactionDetails", listObject);
*/				
			}
		}
		logger.debug("定时器结束调用流标动作请求接口，time：{}",new Date());
	}
	
	
	/**
	 * 
	 * @param hour
	 * @return
	 */
	public static String getCurrentTime(int hour){
		String reStr = "";
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date dt=sdf.parse(sdf.format(new Date()));
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(dt);
	        rightNow.add(Calendar.HOUR_OF_DAY,-hour);//当前时间减去多少小时
	        Date dt1=rightNow.getTime();
	        reStr = sdf.format(dt1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reStr;
	}
	
	
	/** 
     * 两个时间之间相差距离多少秒 
     * @param one 时间参数 1： 
     * @param two 时间参数 2： 
     * @return 相差秒数
     */  
    public static long getDistanceSeconds(String str1, String str2){  
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        long senonds = 0;
        try {  
            long diff = df.parse(str2).getTime() - df.parse(str1).getTime();  
            senonds = diff / 1000;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return senonds;  
    }
	
	public static void main(String[] args) {
		System.out.println(getDistanceSeconds("2009-09-09 09:09:09","2009-09-09 09:09:08"));
	}

}
