package com.yimayhd.mapsearch.client.domain.result;

import java.io.Serializable;

/*
 * 俱乐部成员
 */
public class ClubMemberDO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1807256589804907942L;

    private long clubId;

    private long userId;

    private int liveness;
    //加入时间
    private long joinTime;
    
    private String mobileNo;
    //昵称
    private String nickName;
    //姓名
    private String name;
    //类别
    private String userType;
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
	public long getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(long joinTime) {
		this.joinTime = joinTime;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
    
}