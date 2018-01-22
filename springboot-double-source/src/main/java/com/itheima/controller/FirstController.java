package com.itheima.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.pojo.TbUser;
import com.itheima.service.UserService;

/**
 * 第一个Springboot的controller
 * 请求一个url响应一个json数据
 * <p>Title: FirstController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
//@Controller
@RestController
public class FirstController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/user/{id}")
	//@ResponseBody
	public Map getUserById(@PathVariable int id) {
		Map user = new HashMap<>();
		user.put("id", id);
		user.put("name", "张三");
		user.put("age", 15);
		return user;
	}
	
	@RequestMapping("/user/list")
	public List<TbUser> getUserList() {
		return userService.getUserList();
	}
}
