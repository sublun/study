package com.itheima.service;

import java.util.List;

import com.itheima.pojo.TbUser;

public interface UserService {

	List<TbUser> getUserList();
	List<TbUser> getUserList(int db);

}
