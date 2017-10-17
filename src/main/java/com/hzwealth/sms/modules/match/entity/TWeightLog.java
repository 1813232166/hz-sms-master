package com.hzwealth.sms.modules.match.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TWeightLog {
    private String id;
    private String type;//CAPITAL:资金策略，ASSET:资产策略
    private Date createTime;//操作时间
    private String name;//操作人名字
    private String accountNumber;//操作人账号
    private String filed;//属性名称
    private BigDecimal amount;//属性值
    private String value;//属性值
    private Integer weight;//权重
    private String borrowType;//借款类型（金额权重此项为必填） 1:企业;2:个人;0:出借

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }
}