package com.yimayhd.mapsearch.es.car;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.mapsearch.client.domain.es.CarVo;
import com.yimayhd.mapsearch.client.query.PageQuery;
import com.yimayhd.mapsearch.constant.EsBasicEnum;
import com.yimayhd.mapsearch.es.core.EsBase;
import com.yimayhd.mapsearch.es.result.EsSearchResult;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.geoDistanceQuery;
import static org.elasticsearch.index.query.QueryBuilders.geoDistanceRangeQuery;
import static org.elasticsearch.search.sort.SortBuilders.geoDistanceSort;

/**
 * Car
 *
 * @author lilin
 * @date 16/9/27
 */
@Component
public class CarClient extends EsBase {

    public boolean bulkInsertCar(List<CarVo> carVoList) {
        List<IndexRequestBuilder> indexRequestBuilders = new ArrayList<IndexRequestBuilder>(carVoList.size());
        for (CarVo carVo : carVoList) {
            indexRequestBuilders.add(getIndexRequestBuilder(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), String.valueOf(carVo.getId()), JSONObject.toJSONString(carVo)));
        }

        return bulkInsert(indexRequestBuilders);
    }

    public List<CarVo> geoSearch(double lat, double lon, double distance, PageQuery pageQuery) {

        geoDistanceQuery("locationPoint")
                .distance(distance,DistanceUnit.KILOMETERS)
                .point(lat,lon)
                .optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);


        QueryBuilder qb = geoDistanceRangeQuery("locationPoint")
                .point(lat, lon)
                .from("0km")
                .to(distance + "km")
                .includeLower(true)
                .includeUpper(true)
                .optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);

        SortBuilder sortBuilder = geoDistanceSort("locationPoint")
                .point(lat, lon)
                .order(SortOrder.ASC)
                .unit(DistanceUnit.KILOMETERS)
                .sortMode("min")
                .geoDistance(GeoDistance.ARC);


        EsSearchResult<CarVo> search = search(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), CarVo.class, pageQuery.getPageSize() * (pageQuery.getPageNo() - 1), pageQuery.getPageSize() * pageQuery.getPageNo(), qb, sortBuilder);
        return search.getResults();

    }


    public List<CarVo> geoSearch2(double lat, double lon, double distance, PageQuery pageQuery) {

        QueryBuilder qb = geoDistanceQuery("locationPoint")
                .distance(distance,DistanceUnit.KILOMETERS)
                .point(lat,lon)
                .optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);


        SortBuilder sortBuilder = geoDistanceSort("locationPoint")
                .point(lat, lon)
                .order(SortOrder.ASC)
                .unit(DistanceUnit.KILOMETERS)
                .sortMode("min")
                .geoDistance(GeoDistance.ARC);


        EsSearchResult<CarVo> search = search(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), CarVo.class, pageQuery.getPageSize() * (pageQuery.getPageNo() - 1), pageQuery.getPageSize() * pageQuery.getPageNo(), qb, sortBuilder);
        return search.getResults();

    }

    public boolean updateCar(CarVo carVo) {
        return this.upsert(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), String.valueOf(carVo.getId()), JSONObject.toJSONString(carVo));
    }

    public CarVo searchCarById(long id){
        return this.searchById(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), String.valueOf(id),CarVo.class);
    }
}
