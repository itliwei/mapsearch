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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.search.sort.SortBuilders.geoDistanceSort;

/**
 * Car
 *
 * @author lilin
 * @date 16/9/27
 */
@Component
public class CarClient extends EsBase {

    public boolean bulkInsertCar(List<CarVo> carVoList){
        List<IndexRequestBuilder> indexRequestBuilders = new ArrayList<IndexRequestBuilder>(carVoList.size());
        for (CarVo carVo : carVoList) {
            indexRequestBuilders.add(getIndexRequestBuilder(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), String.valueOf(carVo.getId()), JSONObject.toJSONString(carVo)));
        }

        return bulkInsert(indexRequestBuilders);
    }

    public List<CarVo> geoSearch(double lat, double lon,double distance,PageQuery pageQuery) {

        QueryBuilder geoQueryBuilder = geoDistanceRangeQuery("locationPoint")
                .point(lat, lon)
                .from("0km")
                .to(distance +"km")
                .includeLower(true)
                .includeUpper(true)
                .optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);

        BoolQueryBuilder qb = boolQuery().must(geoQueryBuilder).must(matchQuery("online", "0"));

        SortBuilder sortBuilder = geoDistanceSort("locationPoint")
                .point(lat, lon)
                .order(SortOrder.ASC)
                .unit(DistanceUnit.KILOMETERS)
                .sortMode("min")
                .geoDistance(GeoDistance.ARC);


        EsSearchResult<CarVo> search = search(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), CarVo.class, pageQuery.getPageSize() * (pageQuery.getPageNo()-1), pageQuery.getPageSize() * pageQuery.getPageNo(), qb, sortBuilder);
        return search.getResults();

    }
}
