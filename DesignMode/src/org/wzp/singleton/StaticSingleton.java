package org.wzp.singleton;
/**
 * 懒汉模式
 * 这个方法巧妙地利用了内部类和类的初始化方式，使用虚拟机的类初始化机制创建单例
 * @author wzp
 * @date: 2018年2月26日 下午11:53:16 
 *
 */
public class StaticSingleton {
	public static int value = 1;
	private StaticSingleton() {
		System.out.println("--StaticSingleton is create");
	}
	private static class SingletonHolder{ // 修饰符为 private 保证了安全性，static 是因为 getInstance 就是静态的
		private static StaticSingleton instance = new StaticSingleton();
	}
	public static StaticSingleton getInstance() {
		return SingletonHolder.instance;
	}
	
	public static void main(String[] args) {
		//int i = StaticSingleton.value;
		StaticSingleton s1 = StaticSingleton.getInstance();
		StaticSingleton s2 = StaticSingleton.getInstance();
		if(s1 == s2) {
			System.out.println("--引用相同");
		}
	}
}
