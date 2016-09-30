package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.Date;

/*
 * 俱乐部
 */
public class ClubInfoDO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6843703550134192071L;

	private long id;

    private String clubName;

    private String logoUrl;

    private String clubDes;
    
    private int memberCount;

    private long createId;

    private Date createTime;

    private Date modifyTime;

    private String backImg;
    
    private int type;
    
    private int state;

    private int score;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getClubDes() {
		return clubDes;
	}

	public void setClubDes(String clubDes) {
		this.clubDes = clubDes;
	}

	public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }
    public long getCreateId() {
		return createId;
	}

	public void setCreateId(long createId) {
		this.createId = createId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}