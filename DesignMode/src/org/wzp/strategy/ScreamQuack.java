package org.wzp.strategy;

public class ScreamQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("--啊啊啊啊啊~");
	}

}
