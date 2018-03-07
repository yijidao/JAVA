package org.wzp.test;

import java.util.Scanner;

public class TestScanner {
	static Scanner sc = new Scanner(System.in);
	static String name;
	static int age;
	static float salary;
	public static void main(String[] args) {
		
	/*	System.err.println("--请输入姓名：");
		name = sc.nextLine();
		System.out.println("--请输入年龄：");
		age = sc.nextInt();
		System.out.println("--请输入工资：");
		salary = sc.nextFloat();
		System.out.println("--输入的信息如下：");
		System.out.println("--姓名:" + name + "，年龄：" + age + "，工资：" + salary);*/
		
		System.out.println("--请输入年龄：");
		age = sc.nextInt(); // 空格会被 nextline() 识别到，所以这里加一个nextLine() 来完成正常输入
		sc.nextLine();
		System.err.println("--请输入姓名：");
		name = sc.nextLine();
		System.out.println("--请输入工资：");
		salary = sc.nextFloat();
		System.out.println("--输入的信息如下：");
		System.out.println("--姓名:" + name + "，年龄：" + age + "，工资：" + salary);
		
	}
}
