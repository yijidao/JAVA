package org.wzp.model;
/**
 * 用户类
 * @author wzp
 * @date: 2018年4月2日 下午4:35:23 
 *
 */
public class User {
	private String username;
	private String password;
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(Long id, String username, String password) {
		this.username = username;
		this.password = password;
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
