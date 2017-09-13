package com.hzdjr.hzwd.modules.match.entity;

import java.util.Date;

public class TMatchInstallLog {
    private String id;
    private String type;//设置类型：1：匹配期限设置,3:出借计划--匹配散标集产品
    private Date createTime;//操作时间
    private String name;//操作人名字
    private String accountNumber;//操作人账号
    private String assetNper;//借款期数
    private String capitalNper;//出借期数
    private String matchType;//匹配出借产品类型:出借计划--匹配散标集产品

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

    public String getAssetNper() {
        return assetNper;
    }

    public void setAssetNper(String assetNper) {
        this.assetNper = assetNper;
    }

    public String getCapitalNper() {
        return capitalNper;
    }

    public void setCapitalNper(String capitalNper) {
        this.capitalNper = capitalNper;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }
}