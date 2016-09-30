package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.Date;

/*
 * 俱乐部成员
 */
public class SnsClubMemberDO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4897922138800879928L;

	private long id;

    private long clubId;

    private long userId;

    private int liveness;

    private int state;

    private Date createTime;

    private Date modifyTime;

    //用来记录成员数量
    private int count;
    

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getLiveness() {
        return liveness;
    }

    public void setLiveness(int liveness) {
        this.liveness = liveness;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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