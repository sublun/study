package com.itheima;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class Java8Collectors {

	@Test
	public void testMinBy() {
		Optional<Integer> collect = Stream.of(1,2,3,4,5,6,7,8).collect(Collectors.minBy(Comparator.comparing(num->num)));
		System.out.println(collect.isPresent()?collect.get():"");
	}
	
	@Test
	public void testPartitioningBy() {
		//数据分块，例如奇数，偶数
		Map<Boolean, List<Integer>> collect = Stream.of(1,2,3,4,5,6,7,8)
			.collect(Collectors.partitioningBy(num->num%2==0));
		List<Integer> list1 = collect.get(true);
		System.out.println(list1);
		List<Integer> list2 = collect.get(false);
		System.out.println(list2);
	}
	@Test
	public void testGroupBy() throws Exception {
		Map<String, Long> collect = Stream.of("aa","aa","aa","aa","bb","bb","bb","bb")
				.collect(Collectors.groupingBy(ele->ele,Collectors.counting()));
		System.out.println(collect);
	}
}
