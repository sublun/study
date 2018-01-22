package com.itheima.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedis() throws Exception {
		// 1、创建一个Jedis对象，需要host、port参数
		Jedis jedis = new Jedis("192.168.25.153", 6379);
		// 2、直接使用jedis操作redis，每个redis命令对应一个方法。
		String result = jedis.get("hello");
		System.out.println(result);
		String value1 = jedis.hget("hash1", "key1");
		System.out.println(value1);
		// 3、打印结果
		// 4、关闭Jedis对象
		jedis.close();
	}
	
	@Test
	public void testJedisPool() throws Exception {
		// 1、创建一个JedisPool对象，构造参数host、port。JedisPool是单例存在
		JedisPool jedisPool = new JedisPool("192.168.25.153", 6379);
		// 2、从JedisPool对象获得一个连接Jedis对象。多例的
		Jedis jedis = jedisPool.getResource();
		// 3、使用Jedis管理redis
		String string = jedis.get("hello");
		System.out.println(string);
		// 4、使用完毕之后关闭jedis对象。让连接池回收连接。
		jedis.close();
		// 5、系统结束之前关闭JedisPool。
		jedisPool.close();
	}
	
	@Test
	public void testJedisCluster() throws Exception {
		// 1、创建一个JedisCluster对象，构造参数需要一个Set类型。Set中有多个HostAndPort对象。
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.153", 7001));
		nodes.add(new HostAndPort("192.168.25.153", 7002));
		nodes.add(new HostAndPort("192.168.25.153", 7003));
		nodes.add(new HostAndPort("192.168.25.153", 7004));
		nodes.add(new HostAndPort("192.168.25.153", 7005));
		nodes.add(new HostAndPort("192.168.25.153", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		// 2、JedisCluster在系统中是单例存在
		jedisCluster.set("hello1", "123456");
		String string = jedisCluster.get("hello");
		System.out.println(string);
		// 3、如果对redis集群进行操作的话直接使用JedisCluster对象管理集群。
		// 使用完毕之后，不许要关闭JedisCluster。系统结束之前关闭即可。
		jedisCluster.close();
	}
}
