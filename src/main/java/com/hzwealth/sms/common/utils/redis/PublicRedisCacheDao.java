package com.hzwealth.sms.common.utils.redis;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.utils.ByteUtils;
import com.hzwealth.sms.common.utils.spring.SpringUtils;

import redis.clients.jedis.Protocol.Keyword;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.SafeEncoder;


public class PublicRedisCacheDao {
/*     
	
	private static final ShardedJedisPool pool = SpringUtils.getBean("publicShardedJedisPool");
	
	*//**
	 * 根据key新增或者修改内存数据
	 * @param key 数据库存储键
	 * @param value 数据库存储值
	 * @return 操作是否成功
	 *//*
	public static boolean  set(String key, String value){
		
		ShardedJedis jedis = null;
		try {
			//获取缓存连接
			jedis = pool.getResource();
			
			//增加或更改缓存数据库数值
			String result = jedis.set(key, value);
			return  Keyword.OK.name().equalsIgnoreCase(result);
		} catch (Exception ex) {
			return false;
		} finally {
			//释放缓存连接
			pool.returnResource(jedis);
		}
	}
	
	*//**
	 * 新增或者修改内存数据库中key-value并设置存活时间
	 * @param key 数据存储键
	 * @param value 数据存储值
	 * @param expireTime 失效时间
	 * @return
	 *//*
	public static boolean set(String key, String value, int expireTime){
		
		ShardedJedis jedis = null;
		try {
			//获取缓存连接
			jedis = pool.getResource();
			
			//增加或者更改设置缓存数据库数值
			String result = jedis.setex(key, expireTime, value);
			return  Keyword.OK.name().equalsIgnoreCase(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			//释放缓存连接
			pool.returnResource(jedis);
		}		
	}
	
	*//**
	 * 新增或者修改内存数据库中key-value并设置存活时间
	 * @param key 数据存储键
	 * @param objSer 对象序列化后Byte数组
	 * @param expireTime 失效时间
	 * @return
	 *//*
	public static boolean set(String key, List<Map<String,String>> value, int expireTime){
		
		ShardedJedis jedis = null;
		try {
			//获取缓存连接
			jedis = pool.getResource();			
			
			//增加或者更改设置缓存数据库数值
			String result = jedis.set(SafeEncoder.encode(key), ByteUtils.transObj2Byte(value));
			
			//失效时间
			jedis.expire(key, expireTime);
			return  Keyword.OK.name().equalsIgnoreCase(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			//释放缓存连接
			pool.returnResource(jedis);
		}		
	}
	
	*//**
	 * 新增或者修改内存数据库中key-value并设置存活时间
	 * @param key 数据存储键
	 * @param objSer 对象序列化后Byte数组
	 * @param expireTime 失效时间
	 * @return
	 *//*
	public static boolean set(String key, Object value, int expireTime){
		
		ShardedJedis jedis = null;
		try {
			//获取缓存连接
			jedis = pool.getResource();
			
			//增加或者更改设置缓存数据库数值
			String result = jedis.set(SafeEncoder.encode(key), ByteUtils.transObj2Byte(value));
			
			//失效时间
			jedis.expire(key, expireTime);
			return  Keyword.OK.name().equalsIgnoreCase(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			//释放缓存连接
			pool.returnResource(jedis);
		}		
	}
	
	*//**
	 * 根据键获取内存数据库存储值
	 * @param key 数据存储键
	 * @return
	 *//*
	public static  Object read(String key){
		
		ShardedJedis jedis = null;
		try {
			//获取缓存连接
			jedis = pool.getResource();
			
			//获得并返回缓存数据库数值
			return jedis.get(key);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			//释放缓存连接
			pool.returnResource(jedis);
		}
	}
	
	*//**
	 * 根据键获取内存数据库存储值
	 * @param key 数据存储键
	 * @return
	 *//*
	public static  Object getObjByKey(String key){
		
		ShardedJedis jedis = null;
		try {
			//获取缓存连接
			jedis = pool.getResource();
			//获得并返回缓存数据库数值
			byte [] objSer = jedis.get(SafeEncoder.encode(key));

			return ByteUtils.transByte2Obj(objSer);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			//释放缓存连接
			pool.returnResource(jedis);
		}
	}
	
	*//**
	 * 根据键删除值
	 * @param key 数据存储键
	 * @return 操作结果
	 *//*
	public static  boolean delete(String key){
		ShardedJedis jedis = null;
		try {
			//获取缓存连接
			jedis = pool.getResource();
			
			//删除缓存数据库数值
			jedis.del(key);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			//释放缓存连接
			pool.returnResource(jedis);
		}
		
	}
	
	public static Long incr(String key) {
		ShardedJedis jedis = null;
		
		try {
			//获取缓存连接
			jedis = pool.getResource();
			
			return jedis.incr(key);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0L;
		} finally {
			pool.returnResource(jedis);
		}
	}
	public static Boolean deleteIncrKey(String key) {
		ShardedJedis jedis = null;
		
		try {
			//获取缓存连接
			jedis = pool.getResource();
			
			if (jedis.exists(key)) {
				jedis.del(key);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			pool.returnResource(jedis);
		}
		
		return true;
	}
*/}
