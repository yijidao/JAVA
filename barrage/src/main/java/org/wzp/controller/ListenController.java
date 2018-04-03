package org.wzp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzp.mapper.UserMapper;
import org.wzp.model.User;
import org.wzp.service.DyBulletScreenClient;
import org.wzp.utils.KeepAlive;
import org.wzp.utils.KeepGetMsg;

/**
 * 
 * @author wzp
 * @date: 2018年4月3日 上午10:11:45 
 *
 */
@RestController
public class ListenController {
	private DyBulletScreenClient danmuClient = DyBulletScreenClient.getInstance();
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/root/listen")
	public void listen(String port) {
		danmuClient.init(Integer.parseInt(port), -9999);
		
        KeepAlive keepAlive = new KeepAlive();
        keepAlive.start();
        
        KeepGetMsg keepGetMsg = new KeepGetMsg();
        keepGetMsg.start();
	}
	@RequestMapping("/root/cancle")
	public void cancle() {
		danmuClient.setReadyFlag(false);
	}
	@RequestMapping("/root/pull")
	public String push() {
		while(true) {
			if(!danmuClient.queue.isEmpty()) {
				String msg = danmuClient.queue.poll();
				if(msg.indexOf("注册") != -1) {
					int start = msg.indexOf(">");
					int end = msg.indexOf("：");
					String username = msg.substring(start+1, end);
					try {
						userMapper.insert(new User(username, "000000"));
					}catch(Exception e) {
						msg = "异常信息===》" + username + "重复注册";
					}
				}
				return msg;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
