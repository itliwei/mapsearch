package com.yimayhd.mapsearch.mongo.base.impl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.yimayhd.mapsearch.mongo.base.BaseMongoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;



/**
 * @author Administrator
 *
 */
@Repository
public  class BaseDaoImpl<T> implements BaseMongoDao<T> {
    private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;
 
    /**
	 * Entity的类型
	 */
	protected Class<T> entityClass;

    public BaseDaoImpl(){  
        //getClass() 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class。  
        this.entityClass=(Class<T>)getSuperClassGenricType(getClass(), 0);  
    }  
	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	public List<T> find(Query query,String collectionName) {
		return mongoTemplate.find(query, entityClass,collectionName);
	}

	/**
	 * 通过一定的条件查询一个实体
	 * 
	 * @param query
	 * @return
	 */
	public T findOne(Query query,String collectionName) {
		return (T) mongoTemplate.findOne(query, entityClass,collectionName);
	}

	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public void update(Query query, Update update,String collectionName) {
		mongoTemplate.upsert(query, update, entityClass,collectionName);
	}
	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public void findAndModify(Query query, Update update,String collectionName) {
		mongoTemplate.findAndModify(query, update, entityClass,collectionName);
	}

	/**
	 * 通过条件查询删除数据
	 * 
	 * @param query
	 * @param collectionName
	 * @return
	 */
	@Override
	public void findAndRemove(Query query, String collectionName) {
		// TODO Auto-generated method stub
		mongoTemplate.findAllAndRemove(query, entityClass, collectionName);
	}

	/**
	 * 保存一个对象到mongodb
	 *
	 * @param bean
	 * @return
	 */
	@Override
	public int insert(T bean, String collectionName) {
		mongoTemplate.insert(bean,collectionName);
		return 1;
	}

	/**
	 * 保存批量对象到mongodb
	 *
	 * @param batchToSave
	 * @param collectionName
	 * @return int
	 */
	@Override
	public int batchInsert(List<T> batchToSave, String collectionName) {
		mongoTemplate.insert(batchToSave,collectionName);
		return batchToSave.size();
	}
	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param bean
	 * @return
	 */
	public int save(T bean,String collectionName) {
		mongoTemplate.save(bean,collectionName);
		return 1;
	}

	/**
	 * 通过ID获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return (T) mongoTemplate.findById(id, entityClass);
	}

	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	public T get(String id, String collectionName) {
		return (T) mongoTemplate.findById(id, entityClass, collectionName);
	}
	/**
	 * 通过ID删除记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	@Override
	public int delete(String id, String collectionName) {
		mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), collectionName);
		return 1;
	}
	
	/**
	 * 通过ID删除记录,并且指定了集合名(表的意思)
	 * 
	 * @param query
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	@Override
	public boolean exists(Query query,String collectionName) {
		return mongoTemplate.exists(query, entityClass,collectionName);
	}
	
	/*
	 * 附近的人
	 * @see com.shangmb.application.smb.lbs.base.BaseMongoDao#geoNear(org.springframework.data.mongodb.core.query.NearQuery, java.lang.Class, java.lang.String)
	 */
	@Override
	public GeoResults<T> geoNear(NearQuery near, Class<T> clazz, String collectionName) {
		GeoResults<T>  geoResults = mongoTemplate.geoNear(near, clazz,collectionName); 
		return geoResults;
	}
	/** 
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. 
     *  
     *@param clazz 
     *            clazz The class to introspect 
     * @param index 
     *            the Index of the generic ddeclaration,start from 0. 
     * @return the index generic declaration, or Object.class if cannot be 
     *         determined 
     */  
    @SuppressWarnings("unchecked")  
    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {  
          
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。  
        Type genType = clazz.getGenericSuperclass();  
  
        if (!(genType instanceof ParameterizedType)) {  
           return Object.class;  
        }  
        //返回表示此类型实际类型参数的 Type 对象的数组。  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
  
        if (index >= params.length || index < 0) {  
                     return Object.class;  
        }  
        if (!(params[index] instanceof Class)) {  
              return Object.class;  
        }  
        return (Class) params[index];  
    }
	
}
