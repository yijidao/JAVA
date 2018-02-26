package org.wzp.singleton;

/**
 * 懒汉模式
 * 使用 synchronized 加锁，防止并发条件下，对象被多次创建
 * 缺点：使用了 synchronized 加锁，在并发条件下，对性能有一定影响
 * @author wzp
 * @date: 2018年2月26日 下午11:40:40 
 *
 */
public class LazySingleton {
	public static int value = 1;
	private LazySingleton(){
		System.out.println("--LazySingleton is create");
	}
	private static LazySingleton instance = null;
	public static synchronized LazySingleton getInstance() {
		if(instance == null){
			instance = new LazySingleton();
		}
		return instance;
	}
	
	public static void main(String[] args) {
//		int i = LazySingleton.value; // 不会初始化成员变量 instance
		LazySingleton s1 = getInstance();
		LazySingleton s2 = getInstance();
		if(s1 == s2) {
			System.out.println("--引用相同");
		}
	}
}
