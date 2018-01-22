package com.itheima.mapdb;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.HTreeMap.KeySet;
import org.mapdb.Serializer;

public class MapDBDiskCacheTest {

	private static DB db;
	private static HTreeMap<String , String> userMap;
	
	static {
		db = DBMaker
			//文件缓存，将缓存数据保存到文件中
			.fileDB("D:\\temp\\mapdb\\user.db")
			//在java程序结束之前先关闭db，保证数据文件的完整性
			.closeOnJvmShutdown()
			.transactionEnable()
			.make();
		userMap = db.hashMap("userMap", Serializer.STRING, Serializer.STRING)
			// create() 创建新的集合。 如果集合存在，将扔出异常。
			// open() 打开存在的集合。 如果集合不存在，将扔出异常。
			// createOrOpen() 如果存在就打开, 否则创建。
			.createOrOpen();
	}
	
	/**
	 * 磁盘缓存测试
	 */
	@Test
	public void testFileCache() throws Exception {
		
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
		/*userMap.put("hello", "world!");
		Thread.sleep(1000);
		userMap.put("hello1", "100000");
		Thread.sleep(1000);
		userMap.put("hello2", "abcdefg");
		db.commit();*/
		Thread.sleep(2000);
				
	}
}
