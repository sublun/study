package com.itheima;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class Java8Stream {

	@Test
	public void testCollect() throws Exception {
		List<String> strList = Stream.of("a","b","c").collect(Collectors.toList());
		for (String string : strList) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testMap() throws Exception {
		List<String> strList = Stream.of("a","b","hello")
				.map(str->str.toUpperCase())
				.collect(Collectors.toList());
		for (String string : strList) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFilter() throws Exception {
		List<String> strList = Stream.of("abc","123","aaa","1ase","222a")
				.filter(str->str.contains("a"))
				.collect(Collectors.toList());
		for (String string : strList) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFlatMap() throws Exception {
		List<String> strList = Stream.of(Arrays.asList("1","aa","1b","1c","dd","ff"),
				Arrays.asList("2","2aa","1b","2c","d2d","f2f"))
				.flatMap(list->{
					//System.out.println(list.getClass().getName());
					//System.out.println(list);
					return list.stream().filter(str->str.contains("f"));
				})
				.collect(Collectors.toList());
		for (String string : strList) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testMin() throws Exception {
		List<Integer> numList = Stream.of(1,2,3,4,5,6).collect(Collectors.toList());
		//Integer integer = numList.stream().min(Comparator.comparing(num->num)).get();
		Integer integer = numList.stream().min((a,b)->a<b?-1:1).get();
		System.out.println(integer);
		
	}
	@Test
	public void testMax() throws Exception {
		List<Integer> numList = Stream.of(1,2,3,4,5,6).collect(Collectors.toList());
		Integer integer = numList.stream().max(Comparator.comparing(num->num)).get();
		//Integer integer = numList.stream().min((a,b)->a<b?1:-1).get();
		System.out.println(integer);
		
	}
	
	@Test
	public void testReduce() throws Exception {
		//Integer sum = Stream.of(1,2,3,4,5,6,7,8,9).reduce(10, (acc,ele)->acc + ele);
		Integer sum = Stream.of(1,2,3,4,5,6,7,8,9).reduce(0, Integer::sum);
		System.out.println(sum);
	}
	@Test
	public void testMap2() throws Exception {
		List<Integer> collect = Stream.of(1,2,3,4,5,6,7,8)
			
			.map(ele->{
				Map<String, Integer> map = new HashMap<>();
				map.put("id", ele);
				return map;
			}).map(map->map.get("id"))
			.collect(Collectors.toList());
		collect.forEach(ele->{
			System.out.println(ele);
		});
	}
	
	
}
