package com.itheima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itheima.pojo.TbUser;
import com.itheima.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/page/user/list")
	public String showUserList(Model model) {
		List<TbUser> userList = userService.getUserList();
		//把数据传递给页面
		model.addAttribute("userList", userList);
		//返回逻辑视图
		return "user";
	}
	@RequestMapping("/page/user/list/{type}")
	public String showUserList(Model model, @PathVariable int type) {
		List<TbUser> userList = userService.getUserList(type);
		//把数据传递给页面
		model.addAttribute("userList", userList);
		//返回逻辑视图
		return "user";
	}
}
