package com.yimayhd.mapsearch.client.domain.result;

import java.io.Serializable;
import java.util.List;

/*
 * 俱乐部
 */
public class ClubDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1922550618678389637L;

	private long id;

    private String clubName;

    private String logoUrl;

    private String clubDes;
    
    //俱乐部总人数
    private int memberCount;
    //俱乐部成员数量
    public int clubNum;;
    //俱乐部创建人id
    private long createId;
    //用户头像
    private String userPhoto;
    
    private String ncikName;

    private long createTime;

    private long modifyTime;

    private String backImg;
    
    private int state;
    //排序
    private int score;
    //标签
    private List<TagDO> tagList;

    public String isJoin;
    
    //活动数量
    private int activityCount;
    
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

	public String getNcikName() {
		return ncikName;
	}

	public void setNcikName(String ncikName) {
		this.ncikName = ncikName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
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

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public List<TagDO> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagDO> tagList) {
		this.tagList = tagList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getClubNum() {
		return clubNum;
	}

	public void setClubNum(int clubNum) {
		this.clubNum = clubNum;
	}

	public String getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(String isJoin) {
		this.isJoin = isJoin;
	}

	public int getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}
}