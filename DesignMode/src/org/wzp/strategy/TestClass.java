package org.wzp.strategy;
/**
 * 测试类
 * @author wzp
 * @date: 2018年4月15日 下午10:11:56 
 *
 */
public class TestClass {
	public static void main(String[] args) {
		Duck green = new GreenHeadDuck();
		Duck red = new RedHeadDuck();
		Duck scream = new ScreamDuck();
		
		green.setFlyBehavior(new FlyWithWings());
		green.setQuackBehavior(new Quack());
		green.display();
		green.getFlyBehavior();
		green.getQuackBehavior();
		
		red.setFlyBehavior(new FlyWithWings());
		red.setQuackBehavior(new Quack());
		red.display();
		red.getFlyBehavior();
		red.getQuackBehavior();
		
		scream.setFlyBehavior(new FlyNoWay());
		scream.setQuackBehavior(new ScreamQuack());
		scream.display();
		scream.getFlyBehavior();
		scream.getQuackBehavior();
	}
}
