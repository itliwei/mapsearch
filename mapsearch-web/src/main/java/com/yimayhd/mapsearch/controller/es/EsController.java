package com.yimayhd.mapsearch.controller.es;

import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.client.domain.es.CarVo;
import com.yimayhd.mapsearch.client.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * mongo测试
 */
@Controller
@RequestMapping("/es")
public class EsController {

    @Autowired
    private CarService carService;

    //查询
    @ResponseBody
    @RequestMapping(value = "/geoNearN", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String testQuery() {
        List<CarVo> carPoints = carService.testSearch(10d);
        return JSON.toJSONString(carPoints);
    }


    //随机插2000入个点
    @ResponseBody
    @RequestMapping(value = "/batchInsert", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String testBatchInsert() {
        return carService.testInitIndex() ? "ok" : "error";
    }

    //查询
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String testUpdateCarPoint() {
        return JSON.toJSONString(carService.testUpdateIndex());
    }
}
