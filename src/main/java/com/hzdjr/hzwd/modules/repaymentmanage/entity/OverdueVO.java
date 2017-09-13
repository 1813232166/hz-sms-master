package com.hzdjr.hzwd.modules.repaymentmanage.entity;

import java.math.BigDecimal;

/**
 * 逾期展示对象
 * 
 * @author xuchenglin
 * @since 2017-04
 */
public class OverdueVO extends OverdueDTO {
  private static final long serialVersionUID = 1L;

  private BigDecimal totalAmount; // 总额
  
  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

}
