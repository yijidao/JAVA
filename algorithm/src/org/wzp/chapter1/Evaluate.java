package org.wzp.chapter1;

import java.util.Scanner;
import java.util.Stack;
/**
 * 双栈算数表达式求值
 * 两个栈，一个存操作符，一个存数字，遇到（就跳过，遇到）就弹出一个操作符和两个数字进行运算，结果重新压回数字栈
 * @author wzp
 * @date: 2018年3月7日 上午10:58:59 
 *
 */
public class Evaluate {
	public static void main(String[] args) {
		System.out.println("--输入表达式：");
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		for(int i = 0; i < expression.length(); i++)
		{
			String s = expression.substring(i, i + 1);
			if(s.equals("("))
				continue;

			else if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
				ops.push(s);
			else if(s.equals(")")) {
				String op = ops.pop();
				Double val = vals.pop();
				if(op.equals("+"))
					val = val + vals.pop();
				else if(op.equals("-"))
					val = vals.pop() - val;
				else if(op.equals("*"))
					val = val * vals.pop();
				else if(op.equals("/"))
					val = vals.pop() / val;
				vals.push(val);
			} else {
				vals.push(Double.parseDouble(s));
			}
		}
		System.out.println("--结果为：" + vals.pop());
	}
}
