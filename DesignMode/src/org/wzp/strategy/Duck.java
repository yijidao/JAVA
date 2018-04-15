package org.wzp.strategy;
/**
 * 
 * @author wzp
 * @date: 2018年4月15日 下午9:52:21 
 *
 */
public abstract class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	public void swim() {
		System.out.println("--All duck can swim");
	};
	
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	
	public void getFlyBehavior(){
		flyBehavior.fly();
	};
	
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
	
	public void getQuackBehavior() {
		quackBehavior.quack();
	}
	
	public abstract void display();
}
