package com.yimayhd.mapsearch.client.service;

import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.domain.param.CarStatusDTO;
import com.yimayhd.mapsearch.client.result.BatchQueryResult;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.SingleQueryResult;

/**
 * 车实时位置
 * 
 * @author gaotingping
 *
 * 2016年10月17日 下午3:12:23
 */
public interface CarLocService {

	/**
	 * 保存实时位置
	 *
	 * @param carLocDTO
	 */
	public ResultSupport saveCarLoc(CarLocDTO carLocDTO);
	
	
	/**
	 * 更新实时位置
	 *
	 * @param carLocDTO
	 * @return
	 */
	public ResultSupport updateCarLoc(CarLocDTO carLocDTO);
	
	
	/**
	 * 更新车的状态
	 *
	 * @param carLocDTO
	 * @return
	 */
	public ResultSupport updateCarStatus(CarStatusDTO carStatusDTO);
	
	
	/**
	 * 更新车的状态
	 *
	 * @param carLocDTO
	 * @return
	 */
	public SingleQueryResult getCarByCarId(CarLocDTO carLocDTO);
	
	
	/**
	 * 附近搜索:计算点直接的位置，并排序
	 *
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public BatchQueryResult nearSearch(CarLocQueryDTO carLocSearchDTO);

	
	/**
	 * 区域搜索:只查询满足条件的点，不计算距离
	 *
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public BatchQueryResult areaSearch(CarLocQueryDTO carLocSearchDTO);
}
