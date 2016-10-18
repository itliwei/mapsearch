package com.yimayhd.mapsearch.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.domain.param.CarStatusDTO;
import com.yimayhd.mapsearch.client.errorcode.ErrorCode;
import com.yimayhd.mapsearch.client.result.BatchQueryResult;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.SingleQueryResult;
import com.yimayhd.mapsearch.client.service.CarLocService;
import com.yimayhd.mapsearch.manager.CarLocManager;

/**
 * mysql版本的地理位置检索
 * 
 * @author gaotingping
 *
 *         2016年10月10日 下午3:19:28
 */
public class CarLocServiceImpl implements CarLocService {

	private static final Logger logger = LoggerFactory.getLogger(CarLocServiceImpl.class);

	@Autowired
	private CarLocManager carLocManager;

	@Override
	public ResultSupport saveCarLoc(CarLocDTO carLocDTO) {
		try {
			return carLocManager.saveCarLoc(carLocDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResultSupport result = new ResultSupport();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public ResultSupport updateCarLoc(CarLocDTO carLocDTO) {
		try {
			return carLocManager.updateCarLoc(carLocDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResultSupport result = new ResultSupport();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public ResultSupport updateCarStatus(CarStatusDTO carStatusDTO) {
		try {
			return carLocManager.updateCarStatus(carStatusDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResultSupport result = new ResultSupport();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public SingleQueryResult getCarByCarId(CarLocDTO carLocDTO) {
		try {
			return carLocManager.getCarByCarId(carLocDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			SingleQueryResult result = new SingleQueryResult();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public BatchQueryResult nearSearch(CarLocQueryDTO carLocSearchDTO) {
		try {
			return carLocManager.nearSearch(carLocSearchDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			BatchQueryResult result = new BatchQueryResult();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public BatchQueryResult areaSearch(CarLocQueryDTO carLocSearchDTO) {
		try {
			return carLocManager.areaSearch(carLocSearchDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			BatchQueryResult result = new BatchQueryResult();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}
}
