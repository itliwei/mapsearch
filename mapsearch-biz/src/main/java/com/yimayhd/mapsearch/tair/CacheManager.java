package com.yimayhd.mapsearch.tair;

import java.io.Serializable;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;
import org.springframework.util.CollectionUtils;

/**  
 * @author: wuzhengfei@pajk.cn  
 * @date: 2014年6月22日 下午11:02:16
 * 
 */
public class CacheManager {
	private static final Logger log = LoggerFactory.getLogger(CacheManager.class);
	
	private TairManager tairManager ;
	
	private int namespace ;

	public boolean addToTair(String key, Serializable serializable){
		int expireTime = 0 ;
		int version = 0 ;
		return addToTair(key, serializable, version, expireTime);
	}
	/**
	 * 插入到tair中
	 * @param serializable
	 * @param key
	 * @param expireTime
	 * @return
	 */
	public boolean addToTair(String key, Serializable serializable , int expireTime){
		int version = 0 ;
		return addToTair(key, serializable, version, expireTime);
	}
	/**
	 * 插入到tair中
	 * @param key
	 * @param serializable
	 * @param version
	 * @param expireTime
	 * @return
	 */
	public boolean addToTair(String key, Serializable serializable , int version, int expireTime){
		ResultCode putRc = tairManager.put(namespace, key, serializable, version, expireTime);
		if( putRc.isSuccess() ){
			log.info("add data to tair success! namespace="+namespace+"    key="+key+"   expireTime="+expireTime+" version="+version);
			return true;
		}else{
			log.error("add data to tair fail! namespace=" + namespace + "    key=" + key + "   expireTime=" + expireTime + " version=" + version);
			return false;
		}
	}
	
	
	
	public Object getFormTair(String key){
		Result<DataEntry> result = null ;
    	try {
    		result = tairManager.get(namespace, key);
		} catch (Exception e) {
			log.error("get data from tair fail!    key="+key +"; \t\t msg="+e.getMessage());
		}
		if(result != null ) {
			if (ResultCode.SUCCESS == result.getRc() && result.getValue() != null) {
				return result.getValue().getValue();
			}
			log.warn("get data from tair null, key:{} ,resultCode:{}", key, result.getRc());
		}
		return null ;
}

	public int getIntFromTair(String key) {
		Object ob = getFormTair(key);
		if (ob == null) {
			return 0;
		}
		int ret = 0;
		try {
			ret = (Integer)ob;
		} catch (Exception e) {
			log.error("value is not int,key:{},value:{},errorMsg:{}",key,ob,e.getMessage());
		}
		return ret;
	}

	public Map<String,Integer> batchGetIntFromTair(List<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return null;
		}
		Result<List<DataEntry>> result = tairManager.mget(namespace, keys);
		if (result == null || result.getValue() == null) {
			return null;
		}
		Map<String,Integer> resultMap = new HashMap<String, Integer>();
		for (DataEntry dataEntry : result.getValue()) {
			if (dataEntry == null || dataEntry.getValue() == null) {
				continue;
			}
			resultMap.put((String)(dataEntry.getKey()),(Integer)dataEntry.getValue());
		}
		return resultMap;
	}
	
	public boolean isExist(String key){
		Result<DataEntry> result = null ;
    	try {
    		result = tairManager.get(namespace, key);
		} catch (Exception e) {
			log.error("get data from tair fail!    key="+key +"; \t\t msg="+e.getMessage());
			return false;
		}
		ResultCode rc = result.getRc();
		if( ResultCode.SUCCESS.equals(rc) ){
			return true;
		}
		return false ;
	}
	
	/**
	 * 从tair中删除
	 * @param key
	 * @return
	 */
	public boolean deleteFromTair(String key){
		ResultCode rc = tairManager.delete(namespace, key);
		if( ResultCode.SUCCESS == rc ){
			log.debug("delete data from tair success !    key="+key);
			return true ;
		}else{
			log.error("delete data from tair fail !    key="+key+"   rc="+rc.getCode()+"   msg="+rc.getMessage());
			return false;
		}
	}

	/**
	 * mget 从tair中获取多个value值
	 * @param keys
	 * @return
	 */
	public Map<String,Object> batchGetObjectFromTair(List<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return null;
		}
		Result<List<DataEntry>> result = tairManager.mget(namespace, keys);
		if (result == null || result.getValue() == null) {
			return null;
		}
		Map<String,Object> resultMap = new HashMap<String, Object>();
		for (DataEntry dataEntry : result.getValue()) {
			if (dataEntry == null || dataEntry.getValue() == null) {
				continue;
			}
			resultMap.put((String)(dataEntry.getKey()), dataEntry.getValue());
		}
		return resultMap;
	}

	/**
	 * mget 顺序从tair中获取多个value值
	 * @param keys
	 * @return
	 */
	public List<Object> batchGetObjectFromTairByKeyOrder(List<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return null;
		}
		Result<List<DataEntry>> result = tairManager.mget(namespace, keys);
		if (result == null || result.getValue() == null) {
			return null;
		}
		List<Object> listObject = new ArrayList<Object>();
		for (DataEntry dataEntry : result.getValue()) {
			if (dataEntry == null || dataEntry.getValue() == null) {
				continue;
			}
			listObject.add(dataEntry.getValue());
		}
		return listObject;
	}
	
	/**
	 * 计数器功能
	 * @param key 
	 * @param delta ： 每次增长的值，非法（<=0）时，设为1
	 * @return
	 */
	public boolean inc(String key, int delta){
		if( delta <= 0 ){
			delta = 1 ;
		}
		Result<Integer> rs = tairManager.incr(namespace, key, delta, 0, 0);
		ResultCode rc = rs.getRc();
		if( ResultCode.SUCCESS == rc ){
//			log.debug("tair inc success !    key="+key);
			return true ;
		}else{
			log.error("tair inc fail !    key="+key+"    delta="+delta+"  rc="+rc.getCode()+"   msg="+rc.getMessage());
			return false;
		}
	}
	
	/**
	 * 原子性计数器减功能
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean dec(String key, int value){
		Result<Integer> result = tairManager.decr(namespace, key, value, 0, 0);
		return result != null && result.isSuccess();
	}

    /**
     * 计数器功能
     * @param key
     * @param delta ： 每次增长的值，非法（<=0）时，设为1
     * @return
     */

    public int inc(String key,int delta,int defaultValue){
        if( delta <= 0 ){
            delta = 1 ;
        }
        Result<Integer> rs = tairManager.incr(namespace, key, delta, defaultValue, 0);
        ResultCode rc = rs.getRc();
        if( ResultCode.SUCCESS == rc ){
            return rs.getValue();
        }else{
            log.error("tair inc fail !    key="+key+"    delta="+delta+"  rc="+rc.getCode()+"   msg="+rc.getMessage());
            return 0;
        }
    }

    /**
     * 计数器功能
     * @param key
     * @param delta ： 每次增长的值，非法（允许delta<0）时 （ps: 与上面的方法区别 允许delta为0）
     * @return
     */
    public int incexp(String key,int delta,int defaultValue){
        if( delta < 0 ){
            delta = 0 ;
        }
        Result<Integer> rs = tairManager.incr(namespace, key, delta, defaultValue, 0);
        ResultCode rc = rs.getRc();
        if( ResultCode.SUCCESS == rc ){
            return rs.getValue();
        }else{
            log.error("tair inc fail !    key="+key+"    delta="+delta+"  rc="+rc.getCode()+"   msg="+rc.getMessage());
            return 0;
        }
    }
	


	public TairManager getTairManager() {
		return tairManager;
	}

    public void setTairManager(TairManager tairManager) {
		this.tairManager = tairManager;
	}

	public int getNamespace() {
		return namespace;
	}

	public void setNamespace(int namespace) {
		this.namespace = namespace;
	}

}
