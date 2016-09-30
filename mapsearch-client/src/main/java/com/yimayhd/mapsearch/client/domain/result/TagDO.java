package com.yimayhd.mapsearch.client.domain.result;

import java.io.Serializable;

public class TagDO implements Serializable {
    private long id;

    private String name;

    private int status;

    private int score;

    private int outType;
    
    private int type;
    
    private long gmtCreated;

    private long gmtModified;
    
    private long createId;
    
    
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCreateId() {
		return createId;
	}

	public void setCreateId(long createId) {
		this.createId = createId;
	}

	private static final long serialVersionUID = 1L;

    public int getOutType() {
		return outType;
	}

	public void setOutType(int outType) {
		this.outType = outType;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(long gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public long getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(long gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
    public String toString() {
        return "SnsTagDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", score=" + score +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}