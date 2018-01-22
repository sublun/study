package com.itheima.inter;

@FunctionalInterface
public interface MyInterface<T,U,R> {

	public R exec(T a, U b);
}
