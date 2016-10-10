package com.yimayhd.mapsearch.mongo.base;
import java.util.List;

import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 
 * @author Administrator
 *
 */
 public interface BaseMongoDao<T> {
	
	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	 List<T> find(Query query, String collectionName);
	/**
	 * 通过一定的条件查询一个实体
	 * 
	 * @param query
	 * @return
	 */
	 T findOne(Query query, String collectionName);
	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	 void update(Query query, Update update, String collectionName) ;
	
	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	 void findAndModify(Query query, Update update, String collectionName) ;
	
	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param collectionName
	 * @return
	 */
	 void findAndRemove(Query query, String collectionName) ;
	
	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param bean
	 * @return
	 */
	 int insert(final T bean, String collectionName);

	/**
	 * 保存批量对象到mongodb
	 *
	 * @param batchToSaveList
	 * @param collectionName String
	 * @return
	 */
	int batchInsert(final List<T> batchToSaveList, String collectionName);
	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param bean
	 * @return
	 */
	 int save(final T bean, String collectionName);
	
	/**
	 * 通过ID获取记录
	 * 
	 * @param id
	 * @return
	 */
	 T get(String id);
	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	 T get(String id, String collectionName);
	
	/**
	 * 通过ID删除记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	 int delete(String id, String collectionName);
	
	/**
	 * 通过ID删除记录,并且指定了集合名(表的意思)
	 * 
	 * @param query
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	 boolean exists(Query query, String collectionName);
	
	
	 GeoResults<T> geoNear(NearQuery near, Class<T> clazz, String collectionName);
}