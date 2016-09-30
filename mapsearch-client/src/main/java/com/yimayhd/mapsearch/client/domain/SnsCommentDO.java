package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.Date;

public class SnsCommentDO implements Serializable {
    private static final long serialVersionUID = 8484491818848063300L;
    private long id;

    private String outId;

    private long categoryId;

    private long communityId;

    private long subjectId;

    private int type;

    private long userId;

    private long replyToUserId;

    private int status;

    private String textContent;

    private Date gmtCreated;

    private Date gmtModified;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(long communityId) {
        this.communityId = communityId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getReplyToUserId() {
        return replyToUserId;
    }

    public void setReplyToUserId(long replyToUserId) {
        this.replyToUserId = replyToUserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "SnsCommentDO{" +
                "id=" + id +
                ", outId='" + outId + '\'' +
                ", categoryId=" + categoryId +
                ", communityId=" + communityId +
                ", subjectId=" + subjectId +
                ", type=" + type +
                ", userId=" + userId +
                ", replyToUserId=" + replyToUserId +
                ", status=" + status +
                ", textContent='" + textContent + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}