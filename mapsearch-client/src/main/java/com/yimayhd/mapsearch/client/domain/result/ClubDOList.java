package com.yimayhd.mapsearch.client.domain.result;

import java.io.Serializable;
import java.util.List;

/*
 * 俱乐部
 */
public class ClubDOList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -293719666308680167L;
	
	private List<ClubDO> clubDOList;

	public List<ClubDO> getClubDOList() {
		return clubDOList;
	}

	public void setClubDOList(List<ClubDO> clubDOList) {
		this.clubDOList = clubDOList;
	}
	
}