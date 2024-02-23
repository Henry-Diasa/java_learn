package com.study.basic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataManage {
	// 插入数据
	public int[] insertData() {
		int[] a = new int[10];
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < a.length - 1; i++) {
			System.out.println("请输入" +(i + 1) + "个数据");
			try {
				a[i] = sc.nextInt();
			}catch (InputMismatchException e) {
				// 捕获异常
				System.out.println("输入数据格式不对");
				sc.next();
				i--;
			}
		}
		return a;
	}
	
	public void showData(int[] a, int length) {
		for(int i = 0; i < length; i++) {
			System.out.print(a[i] + "   ");
		}
		System.out.println();
	}
	
	public void insertAtArray(int[] a, int n, int k) {
		for(int i = a.length - 1; i > k; i--) {
			a[i] = a[i - 1];
		}
		a[k] = n;
	}
	
	public void divThree(int[] a) {
		String str = "";
		int count = 0;
		for(int n:a) {
			if(n % 3 ==0) {
				str = str + n + "  ";
				count++;
			}
		}
		if(count == 0) {
			System.out.println("数组中没有被三整除的元素");
		}else {
			System.out.println("数组中被三整除的元素为" + str);
		}
	}
	
	public void notice() {
		System.out.println("********************************************");
		System.out.println("                1--插入数据");
		System.out.println("                2--显示所有数据");
		System.out.println("                3--在指定位置处插入数据");
		System.out.println("                4--查询能被3整除的数据");
		System.out.println("                0--退出");
		System.out.println("********************************************");
	}
	public static void main(String[] args) {
		// 主方法通过实例调用其他方法
		DataManage dm = new DataManage();
		Scanner sc = new Scanner(System.in);
		
		int input = 0;
		int[] a = null;
		int n = 0, k = 0;
		
		while(true) {
			dm.notice();
			System.out.println("请输入数字进行操作");
			try {				
				input = sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("输入格式有误");
				sc.next();
				continue;
			}
			
			if(input == 0) {
				System.out.println("退出");
				break;
			}
			
			switch(input) {
			case 1:
				a = dm.insertData();
				System.out.println("数组元素为：");
				dm.showData(a, a.length - 1);
				break;
			case 2:
				if(a!=null) {
					System.out.println("数组元素为：");
					if(a[a.length - 1]==0) {
						dm.showData(a, a.length - 1);
					}else {
						dm.showData(a, a.length);
					}
				}else {
					System.out.println("还未在数组中插入数据，请重新选择操作！");
				}
				break;
			case 3:
				if(a!=null) {
					System.out.println("请输入要插入的数据：");
					try {
						n = sc.nextInt();
						System.out.println("请输入要插入数据的位置：");
						k = sc.nextInt();
					}catch(InputMismatchException e) {
						System.out.println("输入的数据格式有误，不能有非数字！");
						sc.next();
						break;
					}
					dm.insertAtArray(a, n, k);
					dm.showData(a, a.length);
				}else {
					System.out.println("还未在数组中插入数据，请重新选择操作！");
				}
				break;
			case 4:
				if(a!=null) {
					dm.divThree(a);			
				}else {
					System.out.println("还未在数组中插入数据，请重新选择操作！");
				}
				break;
			}
			
			
		}
	}

}
