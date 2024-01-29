package com.study.basic;

import java.util.Scanner;

public class ConditionDemo {

	public static void main(String[] args) {
		System.out.println("请输入");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		if(n % 2 == 0) {
			System.out.println(n + "是偶数");
		}else {
			System.out.println(n + "是奇数");
		}

	}

}
