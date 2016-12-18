package com.webDemo.cache.redis;

import com.webDemo.cache.CacheManage;

public interface RedisCacheManage<K,V> extends CacheManage<K,V> {
	
	/**
	 * 判断是否存在
	 */
	boolean exists(final String key);
	
	/**
	 * 检查服务器是否正在运行
	 */
	String ping();
	
	/**
	 * 清空数据
	 */
	boolean clearFlush();
	
}
