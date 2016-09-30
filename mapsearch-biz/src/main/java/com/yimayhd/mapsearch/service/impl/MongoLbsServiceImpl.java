package com.yimayhd.mapsearch.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yimayhd.mapsearch.client.domain.mongo.*;
import com.yimayhd.mapsearch.client.service.MongoLbsService;
import com.yimayhd.mapsearch.util.MongoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.IndexOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 实现类
 */
@Service
public class MongoLbsServiceImpl implements MongoLbsService {
    private static final Logger logger = LoggerFactory.getLogger(MongoLbsServiceImpl.class);
    private final double maxLat = 40.017472;
    private final double minLat = 39.828952;

    private final double maxLon = 116.542984;
    private final double minLon = 116.216141;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public UpdatePersonResult batchUpdatePerson(UpdatePersonDTO updatePersonDTO) {
        UpdatePersonResult updatePersonResult = new UpdatePersonResult();
        Query query = new Query();
        query.limit(updatePersonDTO.getCount());
        List<Person> findList = mongoTemplate.find(query, Person.class, "person");
        Random random = new Random();
        long t1 = System.currentTimeMillis();
        for(Person p : findList){
            int nextLon = random.nextInt((int) ((maxLon-minLon)*10000));
            int nextLat = random.nextInt((int) ((maxLat-minLat)*10000));
            double lon = MongoUtil.add(minLon, 0.00001 * nextLon);
            double lat = MongoUtil.add(minLat, 0.00001 * nextLat);
            double[] arr1={lon,lat};

            p.setLongitude(lon);
            p.setLatitude(lat);
            p.setLocation(arr1);
            mongoTemplate.save(p, "person");
        }
        long t2 = System.currentTimeMillis();
        updatePersonResult.setCount(findList.size());
        updatePersonResult.setCostTime(t2-t1);
        return updatePersonResult;
    }

    @Override
    public PersonResult getPersonList(PersonQuery personQuery) {
      /*  IndexOperations io=mongoTemplate.indexOps("person");
//	     io.dropIndex("location_2dsphere");
	     GeospatialIndex index =new GeospatialIndex("location");
		 index.typed(GeoSpatialIndexType.GEO_2DSPHERE);
	     index.named("location_2dsphere");
	     index.withBits(26);
	     index.withMax(900);
	     index.withMin(0);
	     io.ensureIndex(index); */


        Point point =new Point(personQuery.getLongitude(), personQuery.getLatitude());

        NearQuery near =NearQuery.near(point, Metrics.KILOMETERS);
        Query query =new Query();
        //	       query.addCriteria(Criteria.where("name").is("liwei"));
        query.limit(personQuery.getCount()==0?100:personQuery.getCount());
        near.query(query);
        near.maxDistance(new Distance(personQuery.getDistance(), Metrics.KILOMETERS));
        near.spherical(true);

        long t1 = System.currentTimeMillis();
        GeoResults<Person> geoResults = mongoTemplate.geoNear(near, Person.class,"person");
        long t2 = System.currentTimeMillis();
        logger.info("耗时："+(t2-t1)+" 记录数"+geoResults.getContent().size());


        PersonResult personResult = new PersonResult();
        personQuery.setCount(geoResults.getContent().size());
        personQuery.setDistance(near.getMaxDistance().getValue());
        personResult.setPersonQuery(personQuery);

        List<GeoResult<Person>> geoResultsContent = geoResults.getContent();
        List<PersonInfo> personList = new ArrayList<PersonInfo>();
        for (GeoResult<Person> geoResult : geoResultsContent){
            PersonInfo personInfo = new PersonInfo();
            personInfo.setDistance(geoResult.getDistance().getValue());
            personInfo.setPerson(geoResult.getContent());
            personList.add(personInfo);
        }
        personResult.setPersonList(personList);
        return personResult;
    }
}
