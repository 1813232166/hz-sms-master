package com.hzdjr.hzwd.modules.borrowlist.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;
import com.hzdjr.hzwd.modules.borrow.entity.Borrow;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
/**
 * 散标集实体
 * @author xq
 *
 */
public class TBorrowList extends DataEntity<TBorrowList> implements Comparable<Object>{
    private static final long serialVersionUID = 1L;

    private String borrowListId; //散标集ID
    private String borrowListCode; //散标集编号
    private String borrowListTitle; //散标集名称
    private String borrowListNo;	//散标集序号（拼散标集编号、散标集名称使用）
    private BigDecimal borrowListAmount;        // 借款总额
    private String anualRate;       // 年利率
    private String deadLine;        // 借款期限
    private String payMethod;       // 还款方式(debx等额本息 xxhb先息后本ychbx一次性还本付息)
    private String payMethodMemo;
    private Date createTime;//散标集创建时间
    private Date openTime;//散标集发布时间
    private String borrowListStatus;//散标集状态
    private String borrowListRemarks;//散标集备注
    private BigDecimal minTenderSum; //起投金额
    private String raiseDay;    //募集期
    private String riskWarning; //风险提示
    private String projectBrief;//项目介绍
    private String  borrowWay; // 借款方式(debx等额本息，ycx一次性还款，dqhb到期还本)
    private String  statusMemo;  //散标集状态说明
    private Date startOpenTime; //开始发布时间
    private Date endOpenTime;
    
    private Set<TBorrow> tBorrow; //包含多个标的
    private List<Borrow> borrows; // 标的集合
    
    public TBorrowList() {
    }

    public String getBorrowListId() {
        return borrowListId;
    }

    public void setBorrowListId(String borrowListId) {
        this.borrowListId = borrowListId;
    }
    @ExcelField(title="标的编号",type=1,align=2,sort=1)
    public String getBorrowListCode() {
        return borrowListCode;
    }

    public void setBorrowListCode(String borrowListCode) {
        this.borrowListCode = borrowListCode;
    }
    @ExcelField(title="标的名称",type=1,align=2,sort=2)
    public String getBorrowListTitle() {
        return borrowListTitle;
    }

    public void setBorrowListTitle(String borrowListTitle) {
        this.borrowListTitle = borrowListTitle;
    }
    @ExcelField(title="借款金额（元）",type=1,align=2,sort=3)
    public BigDecimal getBorrowListAmount() {
        return borrowListAmount;
    }

    public void setBorrowListAmount(BigDecimal borrowListAmount) {
        this.borrowListAmount = borrowListAmount;
    }
    @ExcelField(title="出借年化利率",type=1,align=2,sort=4)
    public String getAnualRate() {
        return anualRate;
    }

    public void setAnualRate(String anualRate) {
        this.anualRate = anualRate;
    }
    @ExcelField(title="出借期限",type=1,align=2,sort=5)
    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }
    
    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
            this.payMethod = payMethod;
    }
    
    @ExcelField(title="还款方式",type=1,align=2,sort=6)
    public String getPayMethodMemo() {
      //debx等额本息  xxhb 先息后本 ychbx 一次性还本付息
        if ("debx".equals(payMethod)) {
            return "等额本息";
        }
        if ("xxhb".equals(payMethod)) {
            return "先息后本";
        }
        if ("ychbx".equals(payMethod)) {
            return "一次性还本付息";
        }
        return "";
    }

    public void setPayMethodMemo(String payMethodMemo) {
        this.payMethodMemo = payMethodMemo;
    }

    @ExcelField(title="创建时间",type=1,align=2,sort=7)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @ExcelField(title="发布时间",type=1,align=2,sort=8)
    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }
    
    public String getBorrowListStatus() {
        return borrowListStatus;
    }

    public void setBorrowListStatus(String borrowListStatus) {
        this.borrowListStatus = borrowListStatus;
    }
    @ExcelField(title="备注",type=1,align=2,sort=10)
    public String getBorrowListRemarks() {
        return borrowListRemarks;
    }

    public void setBorrowListRemarks(String borrowListRemarks) {
        this.borrowListRemarks = borrowListRemarks;
    }

    public BigDecimal getMinTenderSum() {
        return minTenderSum;
    }

    public void setMinTenderSum(BigDecimal minTenderSum) {
        this.minTenderSum = minTenderSum;
    }

    public String getRaiseDay() {
        return raiseDay;
    }

    public void setRaiseDay(String raiseDay) {
        this.raiseDay = raiseDay;
    }

    public String getRiskWarning() {
        return riskWarning;
    }

    public void setRiskWarning(String riskWarning) {
        this.riskWarning = riskWarning;
    }

    public String getProjectBrief() {
        return projectBrief;
    }

    public void setProjectBrief(String projectBrief) {
        this.projectBrief = projectBrief;
    }

    public String getBorrowWay() {
        return borrowWay;
    }

    public void setBorrowWay(String borrowWay) {
        this.borrowWay = borrowWay;
    }
    @ExcelField(title="状态",type=1,align=2,sort=9)
    public String getStatusMemo() {
        return statusMemo;
    }

    public void setStatusMemo(String statusMemo) {
        this.statusMemo = statusMemo;
    }

    public Date getStartOpenTime() {
        return startOpenTime;
    }

    public void setStartOpenTime(Date startOpenTime) {
        this.startOpenTime = startOpenTime;
    }

    public Date getEndOpenTime() {
        return endOpenTime;
    }

    public void setEndOpenTime(Date endOpenTime) {
        this.endOpenTime = endOpenTime;
    }

    public Set<TBorrow> gettBorrow() {
        return tBorrow;
    }

    public void settBorrow(Set<TBorrow> tBorrow) {
        this.tBorrow = tBorrow;
    }
    
    public List<Borrow> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<Borrow> borrows) {
		this.borrows = borrows;
	}
	
	public String getBorrowListNo() {
		return borrowListNo;
	}

	public void setBorrowListNo(String borrowListNo) {
		this.borrowListNo = borrowListNo;
	}

	@Override
    public String toString() {
        return "TBorrowList [borrowListId=" + borrowListId + ", borrowListCode=" + borrowListCode
                + ", borrowListTitle=" + borrowListTitle + ", borrowListAmount=" + borrowListAmount + ", anualRate="
                + anualRate + ", deadLine=" + deadLine + ", payMethod=" + payMethod + ", createTime=" + createTime
                + ", openTime=" + openTime + ", borrowListStatus=" + borrowListStatus + ", borrowListRemarks="
                + borrowListRemarks + ", minTenderSum=" + minTenderSum + ", raiseDay=" + raiseDay + ", riskWarning="
                + riskWarning + ", projectBrief=" + projectBrief + ", borrowWay=" + borrowWay + ", startOpenTime="
                + startOpenTime + ", endOpenTime=" + endOpenTime + "]";
    }

    @Override
    public int compareTo(Object o) {
        TBorrowList param=(TBorrowList) o;
        Integer statusInt = Integer.parseInt(this.borrowListStatus);
        return statusInt.compareTo(Integer.parseInt(param.getBorrowListStatus()));
    }
    
    
    
}
