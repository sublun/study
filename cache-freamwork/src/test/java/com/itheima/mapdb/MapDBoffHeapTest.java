package com.itheima.mapdb;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.HTreeMap.KeySet;
import org.mapdb.Serializer;

public class MapDBoffHeapTest {

	private static DB db;
	private static HTreeMap<String , String> userMap;
	
	static {
		db = DBMaker
			//堆内内存保存数据，不对数据进行序列化，速度最快
			//.heapDB()
			//同样是使用堆内存保存数据，会把数据序列化成byte[]，保存的数据不受GC影响
			//.memoryDB()
			//堆外内存，不受GC影响，不受JVM内存影响，不受GC影响
			//可以使用JVM的参数 -XX:MaxDirectMemorySize=10G 设置堆外内存的大小
			.memoryDirectDB()
			.make();
		userMap = db.hashMap("userMap", Serializer.STRING, Serializer.STRING)
			// create() 创建新的集合。 如果集合存在，将扔出异常。
			// open() 打开存在的集合。 如果集合不存在，将扔出异常。
			// createOrOpen() 如果存在就打开, 否则创建。
			.createOrOpen();
	}
	
	/**
	 * 堆外内存测试
	 */
	@Test
	public void testOffHeap() throws Exception {
		
		new Thread() {

			@Override
			public void run() {
				super.run();
				while(true) {
					System.out.println("userMap size:" + userMap.size());
					KeySet<String> keys = userMap.getKeys();
					for (Object key : keys) {
						System.out.print("key:" + key);
						System.out.print("\tvlaue:" + userMap.get(key));
					}
					System.out.println();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}.start();
		
		userMap.put("hello", "world!");
		Thread.sleep(1000);
		userMap.put("hello1", "100000");
		Thread.sleep(1000);
		userMap.put("hello2", "abcdefg");
		Thread.sleep(2000);
				
				
	}
	
	
	
}
