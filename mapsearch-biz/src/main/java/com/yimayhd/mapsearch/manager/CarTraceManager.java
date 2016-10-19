package com.yimayhd.mapsearch.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.client.domain.CarTraceDO;
import com.yimayhd.mapsearch.client.domain.param.CarLocTO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceDTO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceQueryDTO;
import com.yimayhd.mapsearch.client.errorcode.ErrorCode;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.TraceQueryResult;
import com.yimayhd.mapsearch.dao.CarTraceDOMapper;
import com.yimayhd.mapsearch.manager.helper.CarLocToHelper;
import com.yimayhd.mapsearch.manager.helper.CarTraceHelper;

public class CarTraceManager {
	
	private static final Logger logger = LoggerFactory.getLogger(CarTraceManager.class);
	
	@Autowired
	private CarTraceDOMapper carTraceDOMapper;
	
	public ResultSupport saveCarTrace(CarTraceDTO carTraceDTO) {
		
		ResultSupport result = new ResultSupport();

		try {
			if (!CarTraceHelper.checkCarTrace(carTraceDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				CarTraceDO carTraceDO=CarTraceHelper.fullCarTrace(carTraceDTO);
				carTraceDOMapper.insert(carTraceDO);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	public TraceQueryResult queryCarTrace(CarTraceQueryDTO carTraceQueryDTO) {
		
		TraceQueryResult result = new TraceQueryResult();

		try {
			if (!CarTraceHelper.checkQueryCarTrace(carTraceQueryDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				List<CarTraceDO> list = carTraceDOMapper.getCarTrace(carTraceQueryDTO);
				result.setList(list);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	public boolean saveCarTrace(CarLocTO carLocTO) {
		if(!CarLocToHelper.checkParam(carLocTO)){
			logger.error("参数错误",JSON.toJSON(carLocTO));
		}else{
			try{
				CarTraceDO carTraceDO=CarLocToHelper.fullCarTrace(carLocTO);
				carTraceDOMapper.insert(carTraceDO);
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				return false;//重试
			}
		}
		return true;
	}
}
