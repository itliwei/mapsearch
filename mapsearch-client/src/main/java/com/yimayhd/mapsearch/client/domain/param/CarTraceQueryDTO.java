package com.yimayhd.mapsearch.client.domain.param;

import com.yimayhd.mapsearch.client.query.PageQuery;

public class CarTraceQueryDTO extends PageQuery {

	private static final long serialVersionUID = 1L;

	private long orderId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}
