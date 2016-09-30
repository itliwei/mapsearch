package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *活动成员
 * @author lyl
 *
 */
public class SnsActiveMemberDO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8228838568223683094L;

	private long id;

    private long actityId;

    private long createId;

    private String remark;

    private int liveness;
    
    private int state;

    private Date gmtCreated;

    private Date gmtModified;

    //定义count  用来记录成员数量
    private int count;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getActityId() {
        return actityId;
    }

    public void setActityId(long actityId) {
        this.actityId = actityId;
    }

    public long getCreateId() {
        return createId;
    }

    public void setCreateId(long createId) {
        this.createId = createId;
    }

    public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLiveness() {
        return liveness;
    }

    public void setLiveness(int liveness) {
        this.liveness = liveness;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    
}