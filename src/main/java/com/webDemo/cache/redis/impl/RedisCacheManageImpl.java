package com.webDemo.cache.redis.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.webDemo.cache.redis.RedisCacheManage;

public class RedisCacheManageImpl<K, V> implements RedisCacheManage<K, V> {

	static final Logger log = LoggerFactory.getLogger(RedisCacheManageImpl.class);

	@Resource
	private RedisTemplate<K, V> jedisTemplate;

	public boolean put(K key, V obj) {
		try {
			jedisTemplate.opsForValue().set(key, obj);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public V get(K key) {
		try {
			return jedisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(K key) {
		try {
			jedisTemplate.delete(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 判断是否存在
	public boolean exists(final String key) {
		return (Boolean) jedisTemplate.execute(new RedisCallback<Object>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(key.getBytes());
			}
		});
	}

	// 删除当前选择数据库中的所有key
	public boolean clearFlush() {
		String result = (String) jedisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
		return "ok".equals(result) ? true : false;
	}

	/**
	 * 刷新缓存
	 */
	public boolean refresh(K key, V value) {
		if (exists((String) key)) {
			delete(key);
			return put(key, value);
		} else {
			return put(key, value);
		}
	}

	// 当前数据库的 key 的数量
	public long getSize() {
		return (Long) jedisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	// 检查是否服务器正在运行
	public String ping() {
		return (String) jedisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.ping();
			}
		});
	}
}
