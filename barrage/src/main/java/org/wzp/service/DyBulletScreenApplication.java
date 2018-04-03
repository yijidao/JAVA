package org.wzp.service;

import org.wzp.utils.KeepAlive;
import org.wzp.utils.KeepGetMsg;

/**
 * @summary: 弹幕Demo程序启动类 
 * @author: FerroD     
 * @date:   2016-3-12   
 * @version V1.0
 */
public class DyBulletScreenApplication
{
	//弹幕池分组号，海量模式使用-9999
	private static final int groupId = -9999;
	
	//设置需要访问的房间ID信息
	public void listen(int roomId)
	{

		//初始化弹幕Client
        DyBulletScreenClient danmuClient = DyBulletScreenClient.getInstance();
        //设置需要连接和访问的房间ID，以及弹幕池分组号
        danmuClient.init(roomId, groupId);
        
        //保持弹幕服务器心跳
        KeepAlive keepAlive = new KeepAlive();
        keepAlive.start();
        
        //获取弹幕服务器发送的所有信息
        KeepGetMsg keepGetMsg = new KeepGetMsg();
        keepGetMsg.start();
	}
}