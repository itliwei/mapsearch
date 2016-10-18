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
import com.yimayhd.mapsearch.manager.helper.CarLocHelper;

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

	public ResultSupport saveCarLoc(CarLocDTO carLocDTO) {

		ResultSupport result = new ResultSupport();

		try {
			if (!CarLocHelper.checkCarLoc(carLocDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				carLocManager.saveCarLoc(carLocDTO);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	public ResultSupport updateCarLoc(CarLocDTO carLocDTO) {

		ResultSupport result = new ResultSupport();

		try {
			if (carLocDTO == null) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				carLocManager.updateCarLoc(carLocDTO);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	public BatchQueryResult nearSearch(CarLocQueryDTO carLocSearchDTO) {

		BatchQueryResult result = new BatchQueryResult();

		try {
			if (carLocSearchDTO == null) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				result.setList(carLocManager.nearSearch(carLocSearchDTO));
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	public BatchQueryResult areaSearch(CarLocQueryDTO carLocSearchDTO) {

		BatchQueryResult result = new BatchQueryResult();

		try {
			if (carLocSearchDTO == null) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				result.setList(carLocManager.areaSearch(carLocSearchDTO));
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	@Override
	public ResultSupport updateCarStatus(CarStatusDTO carStatusDTO) {
		ResultSupport result = new ResultSupport();

		try {
			if (carStatusDTO == null || carStatusDTO.getCarId()<1 || carStatusDTO.getStatus()<1) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				carLocManager.updateCarStatus(carStatusDTO.getCarId(),carStatusDTO.getStatus());
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	@Override
	public SingleQueryResult getCarByCarId(CarLocDTO carLocDTO) {
		
		SingleQueryResult result = new SingleQueryResult();

		try {
			if (carLocDTO == null || carLocDTO.getCarId()<1) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				result.setValue(carLocManager.getCarByCarId(carLocDTO.getCarId()));
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}
}
