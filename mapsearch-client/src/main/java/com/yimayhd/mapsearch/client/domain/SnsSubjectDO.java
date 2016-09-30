package com.yimayhd.mapsearch.client.domain;

import com.yimayhd.mapsearch.client.enums.BaseStatus;

import java.io.Serializable;
import java.util.Date;

public class SnsSubjectDO implements Serializable {
    private static final long serialVersionUID = -8356229408723989315L;
    private long id;
    
    //外部id，用于幂等
    private String outId;
    //公共外键id
    private long objId;
    //创建人id
    private long userId;
    //状态（1.发布 2.草稿）
    private int status = BaseStatus.AVAILABLE.getType();
    //内容
    private String textContent;
    //图片
    private String picContent;
    
	private SnsPOI snsPOI;
    //地理位置信息
    private String poiContent;
    //扩展的类型，比如分享的是个啥，是诊室还是啥
    private int extContentType;
    //扩展内容，比如分享的内容的标题
    private String extContent;
    //扩展的url，比如分享的url
    private String extUrl;
    //扩展图片，用于比如分享带的展示用图片
    private String extPic;
    
    private Date gmtCreated;

    private Date gmtModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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


    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public int getExtContentType() {
        return extContentType;
    }

    public void setExtContentType(int extContentType) {
        this.extContentType = extContentType;
    }

    public String getExtContent() {
        return extContent;
    }

    public void setExtContent(String extContent) {
        this.extContent = extContent;
    }

    public String getExtUrl() {
        return extUrl;
    }

    public void setExtUrl(String extUrl) {
        this.extUrl = extUrl;
    }

    public String getExtPic() {
        return extPic;
    }

    public void setExtPic(String extPic) {
        this.extPic = extPic;
    }
	public long getObjId() {
		return objId;
	}

	public void setObjId(long objId) {
		this.objId = objId;
	}

	public String getPicContent() {
		return picContent;
	}

	public void setPicContent(String picContent) {
		this.picContent = picContent;
	}

	
	public String getPoiContent() {
		  return SnsPOI.toJsonString(snsPOI);
	 }
	
	public void setPoiContent(String poiContent) {
	  this.snsPOI = SnsPOI.toObject(poiContent);
	}

	public SnsPOI getSnsPOI() {
		return snsPOI;
	}

	public void setSnsPOI(SnsPOI snsPOI) {
		this.snsPOI = snsPOI;
	}
}