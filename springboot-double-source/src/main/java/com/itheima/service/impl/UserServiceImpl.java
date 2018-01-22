package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.itheima.mapper.TbUserMapper;
import com.itheima.mapper2.UserMapper2;
import com.itheima.pojo.TbUser;
import com.itheima.service.UserService;
import com.itheima.utils.JsonUtils;

/**
 * 用户管理Service
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private UserMapper2 userMapper2;
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	//@Transactional
	public List<TbUser> getUserList() {
		//查询数据库之前先查询缓存
		try {
			String json = redisTemplate.opsForValue().get("user-list");
			//如果缓存中有数据直接返回
			if (json != null && !"".equals(json)) {
				List<TbUser> list = JsonUtils.jsonToList(json, TbUser.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果没有查询数据库
		List<TbUser> list = userMapper.selectByExample(null);
		//把查询结果添加到缓存
		redisTemplate.opsForValue().set("user-list", JsonUtils.objectToJson(list));
		//返回结果
		return list;
	}

	@Override
	public List<TbUser> getUserList(int db) {
		List<TbUser> list = null;
		if (db == 1) {
			list = userMapper.selectByExample(null);
		} else if(db == 2) {
			list = userMapper2.selectByExample(null);
		}
		return list;
	}

}
