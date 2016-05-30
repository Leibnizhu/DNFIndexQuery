package com.turingdi.rtb.boolindex;

import redis.clients.jedis.Jedis;

/**
 *	Redis相关操作的工具类
 * @author leibniz
 */
public class RedisUtils{
	private static String redisAddr;
	private static Jedis jedis;
	
	/**
	 *	初始化Redis服务器地址和Jedis对象
	 *	可以在此扩展为Redis集群配置
	 */
	static{
		redisAddr = "172.30.16.235";
		 jedis = new Jedis(redisAddr);
		 jedis.select(1);
	}
	
	/**
	 * @return	返回一个Jedis实例
	 */
	public static Jedis getJedis() {
		//加入判断是否超时，需要重连
		if(!jedis.isConnected()){
			jedis.connect();
			jedis.select(1);
		}
		return jedis;
	}
	
	/**
	 *@return	void。测试Redis集群是否能Ping通
	 */
	public  void pingRedis(){
		System.out.println("Server is running: "+RedisUtils.getJedis().ping());
	}

}
