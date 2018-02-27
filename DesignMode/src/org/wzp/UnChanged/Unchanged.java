package org.wzp.UnChanged;
/**
 * 不变模式
 * 核心思想：对象一旦被创建，则它的内部状态永远不会改变
 * 因此，没有一个线程可以改变不变模式下创建的对象，所以它对多线程是友好的
 * 实现方法：
 * 1. 去除 setter 方法和能修改其属性的方法
 * 2. 所有属性使用 private 和 final 保证无法被外部直接修改，以及一旦初始化就无法改变
 * 3. 类修饰为 final，保证没有子类能重载其行为
 * 4. 有一个可以创建完整对象的构造函数
 * JDK 所有元数据类包装类都是使用不变模式实现的
 * java.lang.String
 * java.lang.Integer
 * java.lang.Long
 * java.lang.Short
 * java.lang.Byte
 * java.lang.Boolean
 * java.lang.Float
 * java.lang.Double
 * java.lang.Character
 * @author wzp
 * @date: 2018年2月27日 上午10:10:04 
 *
 */
public final class Unchanged {
	private final String no;
	private final String name;
	public Unchanged(String no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	public String getNo() {
		return this.no;
	}
	public String getName() {
		return this.name;
	}
}
