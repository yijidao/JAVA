package org.wzp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzp.mapper.UserMapper;
import org.wzp.model.User;

/**
 * 用户 controller
 * @author wzp
 * @date: 2018年4月2日 下午5:13:12 
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/login")
	public User Login(String username, String password) {
		return userMapper.getOne(new User(username, password));
	}
	
}
