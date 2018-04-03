package org.wzp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.wzp.model.User;

public interface UserMapper {
	@Select("SELECT * FROM users ORDER BY id")
	@Results({
		@Result(property = "username",  column = "username"),
		@Result(property = "password", column = "password")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
	@Results({
		@Result(property = "username",  column = "username"),
		@Result(property = "password", column = "password")
	})
	User getOne(User user);

	@Insert("INSERT INTO users(username, password) VALUES(#{username}, #{password})")
	void insert(User user);

	@Update("UPDATE users SET password=#{password} WHERE username =#{username}")
	void update(User user);

	@Delete("DELETE FROM users WHERE username =#{username}")
	void delete(User user);
}
