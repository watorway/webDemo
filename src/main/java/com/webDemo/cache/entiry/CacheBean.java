package com.webDemo.cache.entiry;

import java.util.Map;

/**
 * 通用缓存实体抽象类
 */
public abstract class CacheBean {
	
	private Object objectData;
	private Map<String,Object> mapData;
	
	private String cacheKey;
	private String cacheCode;
	private String cacheType;
	
	public Object getObjectData() {
		return objectData;
	}
	public void setObjectData(Object objectData) {
		this.objectData = objectData;
	}
	public Map<String, Object> getMapData() {
		return mapData;
	}
	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}
	public String getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	public String getCacheCode() {
		return cacheCode;
	}
	public void setCacheCode(String cacheCode) {
		this.cacheCode = cacheCode;
	}
	public String getCacheType() {
		return cacheType;
	}
	public void setCacheType(String cacheType) {
		this.cacheType = cacheType;
	}
	
}
