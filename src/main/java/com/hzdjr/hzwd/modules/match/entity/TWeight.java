package com.hzdjr.hzwd.modules.match.entity;

import java.util.Date;

/**
 * 业务表字段Entity
 * @author huangdegui
 * @version 2017-06-19
 */
public class TWeight {
    private String id;
    private String type;//CAPITAL:资金策略，ASSET:资产策略
    private String filed;//属性名称
    private String value;//属性值
    private Integer weight;//权重
    private String description;//描述
    private Integer status;//权重状态   1:启用，2:停用
    private Date updateTime;//权重更新时间

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

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }
}