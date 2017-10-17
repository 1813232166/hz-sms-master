package com.hzwealth.sms.modules.salesupport.entity;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;


/**
 *返佣设置Entity
 * @author 
 * @version 
 */
public class Rakeback extends DataEntity<Rakeback> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String  type;  //'邀请人类型  1理财师2推荐人',
	private String refferStatus; //'返佣方式  0一次性返佣',
	private String  jsStatus ;//'0 线下返佣 1 线上返佣',
	private String commonMoney;  //' 普通推荐人',
	private String  silverMoney;////'银牌推荐人',
	private String goldMoney ;//'金牌推荐人',
	private java.math.BigDecimal   commonOneRate; // '普通一级返佣利率',
	private java.math.BigDecimal  commonTwoRate; // '普通二级返佣利率',
	private java.math.BigDecimal  silverOneRate; /// '银牌一级返佣利率',
	private java.math.BigDecimal  silverTwoRate;// '银牌二级返佣利率',
	private java.math.BigDecimal  goldOneRate;  //'金牌一级返佣利率',
	private java.math.BigDecimal  goldTwoRate;  //'金牌二级返佣利率',
	private java.math.BigDecimal commonamount;
	private java.math.BigDecimal sliveramount;
	private java.math.BigDecimal goldamount;
	private String  comOneRate; // '普通一级返佣利率',
	private String comTwoRate; // '普通二级返佣利率',
	private String silOneRate; /// '银牌一级返佣利率',
	private String silTwoRate;// '银牌二级返佣利率',
	private String golOneRate;  //'金牌一级返佣利率',
	private String golTwoRate;  //'金牌二级返佣利率',
	private String comamount;
	private String sliamount;
	private String golamount;
	private String status;  //'0 禁用 1 启用',
	/*private Date createTime; // '创建时间',
*/
	
	private Date beginTime; 
	
	/*private Date modifyTime; //'最近一次修改时间',
*///	private String  creatorId;  //'添加人',
//	private String  modifierId; // '修改人',

	private String amount;
	

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Rakeback() {
		super();
	}

	public java.math.BigDecimal getCommonamount() {
		return commonamount;
	}

	public void setCommonamount(java.math.BigDecimal commonamount) {
		this.commonamount = commonamount;
	}

	public java.math.BigDecimal getSliveramount() {
		return sliveramount;
	}

	public void setSliveramount(java.math.BigDecimal sliveramount) {
		this.sliveramount = sliveramount;
	}

	public java.math.BigDecimal getGoldamount() {
		return goldamount;
	}

	public void setGoldamount(java.math.BigDecimal goldamount) {
		this.goldamount = goldamount;
	}

	public String getComOneRate() {
		return comOneRate;
	}

	public void setComOneRate(String comOneRate) {
		this.comOneRate = comOneRate;
	}

	public String getComTwoRate() {
		return comTwoRate;
	}

	public void setComTwoRate(String comTwoRate) {
		this.comTwoRate = comTwoRate;
	}

	public String getSilOneRate() {
		return silOneRate;
	}

	public void setSilOneRate(String silOneRate) {
		this.silOneRate = silOneRate;
	}

	public String getSilTwoRate() {
		return silTwoRate;
	}

	public void setSilTwoRate(String silTwoRate) {
		this.silTwoRate = silTwoRate;
	}

	public String getGolOneRate() {
		return golOneRate;
	}

	public void setGolOneRate(String golOneRate) {
		this.golOneRate = golOneRate;
	}

	public String getGolTwoRate() {
		return golTwoRate;
	}

	public void setGolTwoRate(String golTwoRate) {
		this.golTwoRate = golTwoRate;
	}

	public String getComamount() {
		return comamount;
	}

	public void setComamount(String comamount) {
		this.comamount = comamount;
	}

	public String getSliamount() {
		return sliamount;
	}

	public void setSliamount(String sliamount) {
		this.sliamount = sliamount;
	}

	public String getGolamount() {
		return golamount;
	}

	public void setGolamount(String golamount) {
		this.golamount = golamount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCommonOneRate(java.math.BigDecimal commonOneRate) {
		this.commonOneRate = commonOneRate;
	}

	public void setCommonTwoRate(java.math.BigDecimal commonTwoRate) {
		this.commonTwoRate = commonTwoRate;
	}

	public void setSilverOneRate(java.math.BigDecimal silverOneRate) {
		this.silverOneRate = silverOneRate;
	}

	public void setSilverTwoRate(java.math.BigDecimal silverTwoRate) {
		this.silverTwoRate = silverTwoRate;
	}

	public void setGoldOneRate(java.math.BigDecimal goldOneRate) {
		this.goldOneRate = goldOneRate;
	}

	public void setGoldTwoRate(java.math.BigDecimal goldTwoRate) {
		this.goldTwoRate = goldTwoRate;
	}

	public Rakeback(String id){
		super(id);
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefferStatus() {
		return refferStatus;
	}

	public void setRefferStatus(String refferStatus) {
		this.refferStatus = refferStatus;
	}

	public String getJsStatus() {
		return jsStatus;
	}

	

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public String getCommonMoney() {
		return commonMoney;
	}

	public void setCommonMoney(String commonMoney) {
		this.commonMoney = commonMoney;
	}

	public String getSilverMoney() {
		return silverMoney;
	}

	public void setSilverMoney(String silverMoney) {
		this.silverMoney = silverMoney;
	}

	public String getGoldMoney() {
		return goldMoney;
	}

	public void setGoldMoney(String goldMoney) {
		this.goldMoney = goldMoney;
	}

	

	public java.math.BigDecimal getCommonOneRate() {
		return commonOneRate;
	}

	public java.math.BigDecimal getCommonTwoRate() {
		return commonTwoRate;
	}

	public java.math.BigDecimal getSilverOneRate() {
		return silverOneRate;
	}

	public java.math.BigDecimal getSilverTwoRate() {
		return silverTwoRate;
	}

	public java.math.BigDecimal getGoldOneRate() {
		return goldOneRate;
	}

	public java.math.BigDecimal getGoldTwoRate() {
		return goldTwoRate;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJsStatus(String jsStatus) {
		this.jsStatus = jsStatus;
	}


}