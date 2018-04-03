package org.wzp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wzp.mapper.UserMapper;
import org.wzp.model.User;

/**
 * 用户 controller
 * @author wzp
 * @date: 2018年4月2日 下午5:13:12 
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value = "/login")
	public String Login(String username, String password) {
		User user = userMapper.getOne(new User(username, password));
		if(user == null) {
			return "index.html";
		}
		
		if(username.equals("root"))
			return "root.html";
		else
			return "user.html";
	}
}
