package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.Date;

public class SnsSupportDO implements Serializable{
    private Long id;

    private long outId;

    private long supportId;

    private String supportName;

    private String supportPhoto;

    private int outType;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getOutId() {
        return outId;
    }

    public void setOutId(long outId) {
        this.outId = outId;
    }

    public long getSupportId() {
        return supportId;
    }

    public void setSupportId(long supportId) {
        this.supportId = supportId;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getSupportPhoto() {
        return supportPhoto;
    }

    public void setSupportPhoto(String supportPhoto) {
        this.supportPhoto = supportPhoto;
    }

    public int getOutType() {
        return outType;
    }

    public void setOutType(int outType) {
        this.outType = outType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}