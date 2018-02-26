package org.wzp.singleton;
/**
 * 饿汉模式
 * 实现方式：1.构造函数 private 
 * 2. instance 是  private,保证不会被轻易修改，因为 getInstance 是 static，所以 instance 必须是 static
 * 3. getInstance() 是static
 * 不足：静态成员 instance 会在类第一次初始化的时候就被创建，这个时刻并不一定是 getInstance() 第一次被调用的时候
 * @author wzp
 * @date: 2018年2月26日 下午11:32:16 
 *
 */
public class Singleton {
	public static int value = 1;
	private Singleton() {
		System.out.println("--Singleton is create");
	}
	private static Singleton instance = new Singleton();
	public static Singleton getSingleton() {
		return instance;
	}
	
	public static void main(String[] args) {
//		int i = Singleton.value; // 调用 静态成员 value时，就会初始化Singleton 类了
		Singleton s1 = getSingleton();
		Singleton s2 = getSingleton();
		if(s1 == s2) {
			System.out.println("--引用相同");
		}
		
	}
}
