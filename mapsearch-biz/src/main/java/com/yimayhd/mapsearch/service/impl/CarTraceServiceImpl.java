package com.yimayhd.mapsearch.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.mapsearch.client.domain.param.CarTraceDTO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceQueryDTO;
import com.yimayhd.mapsearch.client.errorcode.ErrorCode;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.TraceQueryResult;
import com.yimayhd.mapsearch.client.service.CarTraceService;
import com.yimayhd.mapsearch.manager.CarTraceManager;

public class CarTraceServiceImpl implements CarTraceService {

	private static final Logger logger = LoggerFactory.getLogger(CarTraceServiceImpl.class);

	@Autowired
	private CarTraceManager carTraceManager;

	@Override
	public ResultSupport saveCarTrace(CarTraceDTO carTraceDTO) {
		try {
			return carTraceManager.saveCarTrace(carTraceDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResultSupport result = new ResultSupport();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public TraceQueryResult queryCarTrace(CarTraceQueryDTO carTraceQueryDTO) {
		try {
			return carTraceManager.queryCarTrace(carTraceQueryDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			TraceQueryResult result = new TraceQueryResult();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
	}
}
