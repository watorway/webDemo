package com.webDemo.cache;

public interface CacheManage<K,V> {
	
	/**
	 * 添加缓存
	 */
	boolean put(K key, V value);
	
	/**
	 * 获取缓存值
	 */
	V get(K key);
	
	/**
	 * 删除缓存数据
	 */
	boolean delete(K key);
	
	/**
	 * 刷新缓存数据
	 */
	boolean refresh(K key, V value);
	
	/**
	 * 得到缓存中对象个数
	 */
	long getSize();
	
	
	
}
