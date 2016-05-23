package cn.sam.test.service.impl;

import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.sam.test.service.CacheService;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Redis服务
 * 
 */
public class RedisCacheService implements CacheService {

	private static Logger logger = LoggerFactory.getLogger(RedisCacheService.class);

	private ShardedJedisPool shardedJedisPool;

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	/**
	 * key对应的Value加1 原子性操作
	 * 
	 * @param key
	 */
	public Long incr(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis.incr(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return null;
	}

	/**
	 * key对应的Value减1 原子性操作
	 * 
	 * @param key
	 */
	public Long decr(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis.decr(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return null;
	}

	/**
	 * key对应的Value加 increment 原子性操作
	 * 
	 * @param key
	 */
	public Long incrBy(String key, long increment) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis.incrBy(key, increment);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return null;
	}

	/**
	 * key对应的Value减decrement 原子性操作
	 * 
	 * @param key
	 */
	public Long decrBy(String key, long decrement) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis.decrBy(key, decrement);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return null;
	}

	/**
	 * Hash 操作 HSET key field value将哈希表key中的域field的值设为value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key, String field, String value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			shardedJedis.hset(key, field, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
	}

	/**
	 * Hash 操作 HSET key field value将哈希表key中的域field的值设为value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param expire
	 *            有效期 单位是ms
	 */
	public void hset(String key, String field, String value, long expire) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			shardedJedis.hset(key, field, value);
			shardedJedis.expire(key, Long.valueOf(expire / 1000).intValue());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
	}

	/**
	 * HDEL key field [field ...]删除哈希表key中的一个或多个指定域。
	 * 
	 * @param key
	 * @param fields
	 */
	public void hdel(String key, String... fields) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			shardedJedis.hdel(key, fields);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
	}

	/**
	 * GET key field返回哈希表key中给定域field的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {
		ShardedJedis shardedJedis = null;
		String value = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			value = shardedJedis.hget(key, field);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	/**
	 * 得到复杂对象的值
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key) {
		ShardedJedis shardedJedis = null;
		Map<String, String> value = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			value = shardedJedis.hgetAll(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	/**
	 * GET key field返回哈希表key中给定域field的值
	 * 
	 * @param key
	 * @param field
	 * @param refreshSource
	 * @param expiry
	 *            有效期
	 * @return
	 */
	public String hget(String key, String field, Callable<String> refreshSource, long expiry) {
		String value = hget(key, field);
		if (null == value) {
			try {
				value = refreshSource.call();
				if (null != value) {
					hset(key, field, value, expiry);
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return value;
	}

	/**
	 * GET key field返回哈希表key中给定域field的值
	 * 
	 * @param key
	 * @param field
	 * @param refreshSource
	 * @return
	 */
	public String hget(String key, String field, Callable<String> refreshSource) {
		String value = hget(key, field);
		if (null == value) {
			try {
				value = refreshSource.call();
				if (null != value) {
					hset(key, field, value);
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return value;
	}

	/**
	 * 设值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			shardedJedis.set(key, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
	}

	/**
	 * 设值
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 *            有效期 单位是ms
	 */
	public void set(String key, String value, long expire) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			shardedJedis.set(key, value);
			shardedJedis.expire(key, Long.valueOf(expire / 1000).intValue());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
	}

	/**
	 * 删除值。
	 * 
	 * @param key
	 */
	public void del(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			shardedJedis.del(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
	}

	/**
	 * 得到值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		ShardedJedis shardedJedis = null;
		String value = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			value = shardedJedis.get(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	/**
	 * 得到值
	 * 
	 * @param key
	 * @param refreshSource
	 * @param expiry
	 *            有效期
	 * @return
	 */
	public String get(String key, Callable<String> refreshSource, long expiry) {
		String value = get(key);
		if (null == value) {
			try {
				value = refreshSource.call();
				if (null != value) {
					set(key, value, expiry);
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return value;
	}

	/**
	 * 得到值
	 * 
	 * @param key
	 * @param refreshSource
	 * @return
	 */
	public String get(String key, Callable<String> refreshSource) {
		String value = get(key);
		if (null == value) {
			try {
				value = refreshSource.call();
				if (null != value) {
					set(key, value);
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return value;
	}

	/**
	 * 在key对应list的头部添加字符串元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long lpush(String key, String... values) {
		ShardedJedis shardedJedis = null;
		Long value = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			value = shardedJedis.lpush(key, values);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	/**
	 * 从list的头部删除元素，并返回删除元素
	 * 
	 * @param key
	 * @return
	 */
	public String lpop(String key) {
		ShardedJedis shardedJedis = null;
		String value = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			value = shardedJedis.lpop(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	/**
	 * 在key对应list的尾部添加字符串元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long rpush(String key, String... values) {
		ShardedJedis shardedJedis = null;
		Long value = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			value = shardedJedis.rpush(key, values);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	/**
	 * 从list的尾部删除元素，并返回删除元素
	 * 
	 * @param key
	 * @return
	 */
	public String rpop(String key) {
		ShardedJedis shardedJedis = null;
		String value = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			value = shardedJedis.rpop(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

}
