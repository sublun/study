package com.itheima;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class Java8OptionalTest {

	@Test
	public void testGet() throws Exception {
		Optional<String> optional = Optional.of("adb");
		System.out.println(optional.get());
		Assert.assertTrue(optional.isPresent());
	}
	
	@Test
	public void testempty() throws Exception {
		Optional<Object> empty = Optional.empty();
		Assert.assertFalse(empty.isPresent());
		System.out.println(empty.orElse("hehe"));
		System.out.println(empty.orElseGet(()->"aaaaa"));
	}
}
