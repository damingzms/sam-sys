package cn.sam.test.service;

import java.util.Map;
import java.util.concurrent.Callable;

import cn.sam.test.entity.SysDictionary;

/**
 * 缓存服务
 */
public interface CacheService {
	
	public static final String KEY_SEPARATOR = "_";
	
	public static final String KEY_DICTIONARY = "dictionary";
	
	public static final String KEY_DICTIONARY_TYPE_LEVEL = KEY_DICTIONARY + KEY_SEPARATOR + SysDictionary.TYPE_LEVEL;
	
	public static final long EXPIRY_ONE_MINUTE = 60;
	
	public static final long EXPIRY_ONE_HOUR = 3600;
	
	public static final long EXPIRY_ONE_DAY = 86400;

	/**
	 * key对应的Value加1
	 * 
	 * @param key
	 * @return
	 */
	public Long incr(String key);

	/**
	 * key对应的Value减1
	 * 
	 * @param key
	 * @return
	 */
	public Long decr(String key);

	/**
	 * key对应的Value加 increment
	 * 
	 * @param key
	 * @param increment
	 * @return
	 */
	public Long incrBy(String key, long increment);

	/**
	 * key对应的Value减decrement
	 * 
	 * @param key
	 * @param decrement
	 * @return
	 */
	public Long decrBy(String key, long decrement);

	/**
	 * Hash 操作 HSET key field value将哈希表key中的域field的值设为value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key, String field, String value);

	/**
	 * Hash 操作 HSET key field value将哈希表key中的域field的值设为value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param expire
	 */
	public void hset(String key, String field, String value, long expire);

	/**
	 * HDEL key field [field ...]删除哈希表key中的一个或多个指定域。
	 * 
	 * @param key
	 * @param fields
	 */
	public void hdel(String key, String... fields);

	/**
	 * GET key field返回哈希表key中给定域field的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field);

	/**
	 * 得到复杂对象的值
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key);

	/**
	 * GET key field返回哈希表key中给定域field的值
	 * 
	 * @param key
	 * @param field
	 * @param refreshSource
	 * @param expiry
	 * @return
	 */
	public String hget(String key, String field, Callable<String> refreshSource, long expiry);

	/**
	 * GET key field返回哈希表key中给定域field的值
	 * 
	 * @param key
	 * @param field
	 * @param refreshSource
	 * @return
	 */
	public String hget(String key, String field, Callable<String> refreshSource);

	/**
	 * 设值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);

	/**
	 * 设值
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void set(String key, String value, long expire);

	/**
	 * 删除值
	 * 
	 * @param key
	 */
	public void del(String key);

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * 获取值
	 * 
	 * @param key
	 * @param refreshSource
	 * @param expiry
	 * @return
	 */
	public String get(String key, Callable<String> refreshSource, long expiry);

	/**
	 * 获取值
	 * 
	 * @param key
	 * @param refreshSource
	 * @return
	 */
	public String get(String key, Callable<String> refreshSource);

	/**
	 * 在key对应list的头部添加字符串元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long lpush(String key, String... values);

	/**
	 * 从list的头部删除元素，并返回删除元素
	 * 
	 * @param key
	 * @return
	 */
	public String lpop(String key);

	/**
	 * 在key对应list的尾部添加字符串元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long rpush(String key, String... values);

	/**
	 * 从list的尾部删除元素，并返回删除元素
	 * 
	 * @param key
	 * @return
	 */
	public String rpop(String key);

}
