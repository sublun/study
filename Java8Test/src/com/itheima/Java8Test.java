package com.itheima;

import java.text.SimpleDateFormat;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import com.itheima.inter.MyInterface;

public class Java8Test {

	@Test
	public void testLamuda() {
		Runnable runnable = ()->{
			System.out.println(Thread.currentThread().getName());
			System.out.println("hello world!");
			
		};
		new Thread(runnable, "inner thread").start();
		System.out.println("main thread!");
		Runnable runnable2 = ()->System.out.println("runnable2");
		new Thread(runnable2).start();
		
		BinaryOperator<Integer> add = (x,y)-> x+y;
		BinaryOperator<Integer> add2 = (x,y)-> {
			return x+y;
		};
		System.out.println(add.apply(1, 2));
		System.out.println(add2.apply(1, 5));
	}
	
	@Test
	public void testMyInter() {
		MyInterface<Integer, Integer, Integer> my = (x,y) -> x+y;
		System.out.println(my.exec(1, 2));
	}
	
	@Test
	public void testFunction() {
		Function<String, String> obj = str-> {
			System.out.println(str);
			return "hello " + str;
		};
		System.out.println(obj.apply("micle"));
		//断言
		//Predicate<T>
		//消费者
		Consumer<String> consumer = item->System.out.println(item);
		consumer.accept("100000");
		//供应商
		Supplier<String> supplier = ()-> "1234567";
		System.out.println(supplier.get());
		
		
	}
}
