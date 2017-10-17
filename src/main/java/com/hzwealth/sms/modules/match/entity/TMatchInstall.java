package com.hzwealth.sms.modules.match.entity;

import java.util.Date;

public class TMatchInstall {
    private String id;
    private String type;//设置类型：1：匹配期限设置，2：散标集--匹配出借计划产品,3:出借计划--匹配散标集产品
    private String assetNper;//资产期限
    private String capitalNper;//资金期限
    private String whetherToOpen;//是否开启，1开启，0关闭
    private String detail;//功能描述
    private Date updateTime;//更新时间

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

    public String getWhetherToOpen() {
        return whetherToOpen;
    }

    public void setWhetherToOpen(String whetherToOpen) {
        this.whetherToOpen = whetherToOpen;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}