package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.Date;

public class SnsTravelSpecialtyDO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6060935242513137386L;

	private long id;

    private String title;

    private long createId;
    
    private String backImg;

    private String poiContent;

    private String preface;
    
    private String imgContentJson;

    private int status;
    
	private SnsPOI snsPOI;

    private Date gmtCreated;

    private Date gmtModified;
    
    private TravelJsonDO travelJsonDO;
//    
//    private int redNum;
//    
//    private int supportNum;

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

	public SnsPOI getSnsPOI() {
		return snsPOI;
	}

	public void setSnsPOI(SnsPOI snsPOI) {
		this.snsPOI = snsPOI;
	}

	public String getPoiContent() {
		  return SnsPOI.toJsonString(snsPOI);
	 }
	
	public void setPoiContent(String poiContent) {
	  this.snsPOI = SnsPOI.toObject(poiContent);
	}
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateId() {
        return createId;
    }

    public void setCreateId(long createId) {
        this.createId = createId;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getImgContentJson() {
        return imgContentJson;
    }

    public void setImgContentJson(String imgContentJson) {
        this.imgContentJson = imgContentJson;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

	public TravelJsonDO getTravelJsonDO() {
		return travelJsonDO;
	}

	public void setTravelJsonDO(TravelJsonDO travelJsonDO) {
		this.travelJsonDO = travelJsonDO;
	}

}