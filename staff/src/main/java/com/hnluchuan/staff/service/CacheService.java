package com.hnluchuan.staff.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.service.DevService;
import com.hnluchuan.utils.common.SerializeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class CacheService {
	private static Logger logger = Logger.getLogger(CacheService.class);
	// 用于本地运行
	private static Map<String, Object> map = new HashMap<>();
	private JedisPool pool = null;
	
	@Autowired
	private DevService devService;
	@Value("${redis.enable:false}")
	private boolean enableRedis;
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private Integer port;
	
	@PostConstruct
	public void init() {
		try {
			Properties props = new Properties();
			pool = new JedisPool(host, port);
			System.err.print(host);
		} catch (Exception e) {
			logger.error("init redis failed", e);
		}
	}
	
	public <T> T get(String key) {
		if (!devService.isServer()) {
			return (T) map.get(key);
		}
		Jedis jedis = null;
		try {
			if (pool == null) {
				return null;
			}
			jedis = pool.getResource();
			if (jedis != null) {
				byte[] bytes = jedis.get(SerializeUtils.serialize(key));
				if (bytes != null) {
					logger.debug("hit cache, " + key);
					return (T) SerializeUtils.deSerialize(bytes);
					
				}
			}
			return null;
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	
	public void set(String key, Object value) {
		if (!devService.isServer()) {
			map.put(key, value);
			return;
		}
		if (pool == null) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			if (jedis != null) {
				jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(value));
			}
			if (logger.isDebugEnabled()) {
				logger.debug("set cache ok ,key: " + key);
			}
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	public void set(String key, Object value, int expireSeconds) {
		if (!devService.isServer()) {
			map.put(key, value);
			return;
		}
		if (pool == null) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			if (jedis != null) {
				byte[] byteKey = SerializeUtils.serialize(key);
				jedis.set(byteKey, SerializeUtils.serialize(value));
				jedis.expire(byteKey, expireSeconds);
				if (logger.isDebugEnabled()) {
					logger.debug("set cache ok ,key: " + key + ", expireSeconds: " + expireSeconds);
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void remove(String key) {
		if (!devService.isServer()) {
			map.remove(key);
			return;
		}
		if (pool == null) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			if (jedis != null) {
				byte[] byteKey = SerializeUtils.serialize(key);
				jedis.del(byteKey);
				if (logger.isDebugEnabled()) {
					logger.debug("remove cache ok ,key: " + key);
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	public void clear() {
		map.clear();
		
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			if (jedis != null) {
				jedis.flushAll();
			}
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
