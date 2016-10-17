package com.yimayhd.mapsearch.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.errorcode.ErrorCode;
import com.yimayhd.mapsearch.client.result.NearSearchResult;
import com.yimayhd.mapsearch.client.result.ResultSupport;
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

	public ResultSupport saveCarLoc(CarLocDTO carLocDTO) {

		ResultSupport result = new ResultSupport();

		try {
			if (carLocDTO == null) {
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

	public NearSearchResult nearSearch(CarLocQueryDTO carLocSearchDTO) {

		NearSearchResult result = new NearSearchResult();

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

	public NearSearchResult areaSearch(CarLocQueryDTO carLocSearchDTO) {

		NearSearchResult result = new NearSearchResult();

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
}
