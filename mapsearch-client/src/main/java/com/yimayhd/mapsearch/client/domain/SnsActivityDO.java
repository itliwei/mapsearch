package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * 活动
 */
public class SnsActivityDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2530136078961637833L;

	private long id;

	private long outId;

	private String title;

	private String clubName;

	private int memberCount;

	private long originalPrice;

	private long preferentialPrice;

	private int status;

	private String image;

	private Date gmtCreated;

	private Date gmtModified;

	private int outType;

	private Date startDate;

	private Date endDate;

	private Date activityDate;
	
	private SnsPOI snsPOI;
	
	private String poiContent;
	
	private String serviceTel;

	private long productId;
	
	private int isStatus;
	
	private String content;
	
	private int createId;
	
	private String clubImg;
	
	private String activeJson;
	
	private long version;
	
	private int score;
	
	private List<ActivityJsonDO> activityJsonArray;
	
	public String getActiveJson() {
		return activeJson;
	}

	public void setActiveJson(String activeJson) {
		this.activeJson = activeJson;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPoiContentoiContent() {
	    return SnsPOI.toJsonString(snsPOI);
	}

	public void setPoiContent(String poiContent) {
		this.poiContent = poiContent;
	}

	public SnsPOI getSnsPOI() {
		return SnsPOI.toObject(poiContent);
	}

	public void setSnsPOI(SnsPOI snsPOI) {
		this.snsPOI = snsPOI;
	}

	public long getOutId() {
		return outId;
	}

	public void setOutId(long outId) {
		this.outId = outId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public long getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(long preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getOutType() {
		return outType;
	}

	public void setOutType(int outType) {
		this.outType = outType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}

	public String getClubImg() {
		return clubImg;
	}

	public void setClubImg(String clubImg) {
		this.clubImg = clubImg;
	}

	public String getPoiContent() {
		return poiContent;
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

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<ActivityJsonDO> getActivityJsonArray() {
		return activityJsonArray;
	}

	public void setActivityJsonArray(List<ActivityJsonDO> activityJsonArray) {
		this.activityJsonArray = activityJsonArray;
	}

	
}