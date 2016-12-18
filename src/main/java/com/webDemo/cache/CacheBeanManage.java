package com.webDemo.cache;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.webDemo.cache.entiry.CacheBean;



/**
 * 如果不实用Redis的话，使用该管理类处理缓存数据，静态Map
 */
@SuppressWarnings("all")
public class CacheBeanManage {
	
	//默认长度为100
	private static Integer cacheSize = 100;
	
	private static Map<String, CacheBean> cacheBeanMap = Collections
			.synchronizedMap(new LinkedHashMap<String, CacheBean>(cacheSize));

	// 单实例构造方法
	private CacheBeanManage() {
		super();
	}
	
	private CacheBeanManage(Integer cacheSize) {
		super();
		this.cacheSize = cacheSize;
	}
		
	private static CacheBeanManage cacheBeanManage;
	
	public static CacheBeanManage getSetSizeInstance(Integer cacheSize){
		if(cacheBeanManage==null)
			return new CacheBeanManage(cacheSize);
		return cacheBeanManage;
	}
	
	public static CacheBeanManage getDefaultSizeInstance(){
		if(cacheBeanManage==null)
			return new CacheBeanManage();
		return cacheBeanManage;
	}
	
	
	/**********************************************************************************/
	
	
	
	

	
	/**
	 * 根据缓存key获得缓存对象
	 */
	public static CacheBean getCache(String cacheKey) {
		return (CacheBean)cacheBeanMap.get(cacheKey);
	}
	
	
	
	/**
	 * 获得缓存数据 根据关键字返回指定类型的缓存数据
	 */
	public static <T> T getDict(String cacheKey) {
		T t = null;
		Object obj = null;
		try {
			obj = getCache(cacheKey).getObjectData();
			t = (T) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (t);
	}
	
	
	
	
	
}
