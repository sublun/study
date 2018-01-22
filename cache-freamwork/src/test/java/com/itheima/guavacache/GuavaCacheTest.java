package com.itheima.guavacache;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCacheTest {

	private final static Cache<String, String> cache = CacheBuilder.newBuilder()
			// 设置cache的初始大小为10，要合理设置该值
			.initialCapacity(10)
			// 设置并发数为5，即同一时间可以有5个线程往cache执行写入操作数值设置越高并发能力越强
			.concurrencyLevel(5)
			// 设置cache中的数据在写入之后的存活时间为10秒
			.expireAfterWrite(10, TimeUnit.SECONDS)
			// 缓存这保存的最大key数量
			.maximumSize(10000)
			// 构建cache实例
			.build();
	@Test
	public void testGuavaCache() throws Exception {
		for(int i=0;i<20;i++) {
			String result = sayHello("张三");
			System.out.println(result);
			Thread.sleep(1000);
		}
	}
	
	public String sayHello(String name) {
		//从cache中取值
		String result = cache.getIfPresent(name);
		//如果有值直接返回
		if (result != null && !"".equals(result)) {
			return result;
		}
		//如果没有值在查询数据
		result = getResult(name);
		//把数据添加到缓存
		cache.put(name, result);
		//返回结果
		return result;
	}
	
	private String getResult(String key) {
		System.out.println("getResult is executed!");
		return "hello:" + key;
	}
}
