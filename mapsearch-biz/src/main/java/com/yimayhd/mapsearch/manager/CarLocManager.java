package com.yimayhd.mapsearch.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.yimayhd.mapsearch.client.domain.CarLocDO;
import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.domain.param.CarStatusDTO;
import com.yimayhd.mapsearch.client.errorcode.ErrorCode;
import com.yimayhd.mapsearch.client.query.CarLocNearQuery;
import com.yimayhd.mapsearch.client.result.BatchQueryResult;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.SingleQueryResult;
import com.yimayhd.mapsearch.dao.CarLocDOMapper;
import com.yimayhd.mapsearch.manager.helper.CarLocHelper;
import com.yimayhd.mapsearch.util.GpsUtil;

public class CarLocManager {

	private static final Logger logger = LoggerFactory.getLogger(CarLocManager.class);

	@Autowired
	private CarLocDOMapper carLocDOMapper;

	public ResultSupport saveCarLoc(CarLocDTO carLocDTO) {

		ResultSupport result = new ResultSupport();

		try {
			if (!CarLocHelper.checkCarLoc(carLocDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				CarLocDO carLocDO = CarLocHelper.fullCarLoc(carLocDTO);
				carLocDOMapper.saveCar(carLocDO);
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
			if (!CarLocHelper.checkCarLoc(carLocDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				CarLocDO carLocDO = CarLocHelper.fullCarInfo(carLocDTO);
				carLocDOMapper.updateCarInfo(carLocDO);
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
			if (!CarLocHelper.checkLocSearchDTO(carLocSearchDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {

				List<CarLocDO> list = getAreaCarList(carLocSearchDTO);

				calculateDistanceAndSort(carLocSearchDTO.getLat(), carLocSearchDTO.getLng(), list);

				result.setList(list);
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
			if (!CarLocHelper.checkLocSearchDTO(carLocSearchDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {

				List<CarLocDO> list = getAreaCarList(carLocSearchDTO);

				result.setList(list);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	public ResultSupport updateCarStatus(CarStatusDTO carStatusDTO) {

		ResultSupport result = new ResultSupport();

		try {
			if (!CarLocHelper.checkStatusDTO(carStatusDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				carLocDOMapper.updateCarStatus(carStatusDTO.getCarId(), carStatusDTO.getStatus());
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	public SingleQueryResult getCarByCarId(CarLocDTO carLocDTO) {

		SingleQueryResult result = new SingleQueryResult();

		try {
			if (!CarLocHelper.checkCarLoc(carLocDTO)) {
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			} else {
				CarLocDO carLocDO = carLocDOMapper.getCarByCarId(carLocDTO.getCarId());
				result.setValue(carLocDO);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}

	// 计算距离，并排序
	private void calculateDistanceAndSort(double lat, double lng, List<CarLocDO> list) {
		if (!CollectionUtils.isEmpty(list)) {
			for (CarLocDO loc : list) {
				loc.setDistance(GpsUtil.getGPSDistance(lat, lng, loc.getLat(), loc.getLng()));
			}
			Collections.sort(list, new Comparator<CarLocDO>() {
				@Override
				public int compare(CarLocDO o1, CarLocDO o2) {
					return (int) (o1.getDistance() - o2.getDistance());
				}
			});
		}
	}

	// 获得区域内的车
	private List<CarLocDO> getAreaCarList(CarLocQueryDTO carLocSearchDTO) {

		double[] boxArea = GpsUtil.getBoxArea(carLocSearchDTO.getLat(), carLocSearchDTO.getLng(),
				carLocSearchDTO.getRadius());

		CarLocNearQuery query = new CarLocNearQuery();
		query.setBoxArea(boxArea);
		query.setPageSize(carLocSearchDTO.getTopN());

		return carLocDOMapper.nearSearch(query);
	}
}
