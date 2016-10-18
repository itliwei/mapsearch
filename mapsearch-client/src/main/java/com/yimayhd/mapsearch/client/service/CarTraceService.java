package com.yimayhd.mapsearch.client.service;

import com.yimayhd.mapsearch.client.domain.param.CarTraceDTO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceQueryDTO;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.TraceQueryResult;

/**
 * 行车轨迹
 * 
 * @author gaotingping
 *
 * 2016年10月17日 下午3:12:06
 */
public interface CarTraceService {

	/**
	 * 保存行车轨迹
	 *
	 * @param carLocDTO
	 * @return
	 */
	public ResultSupport saveCarTrace(CarTraceDTO carTraceDTO);
	
	/**
	 * 更具订单号，查询行车轨迹
	 *
	 * @param carTraceQueryDTO
	 * @return
	 */
	public TraceQueryResult queryCarTrace(CarTraceQueryDTO carTraceQueryDTO);
}
