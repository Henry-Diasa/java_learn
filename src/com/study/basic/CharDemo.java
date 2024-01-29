package com.study.basic;

public class CharDemo {

	public static void main(String[] args) {
		char a = 'a';
		char ch = 65; // ASCII码
		char ch1 = (char)65536; // 超出强制类型转换
		char c = '\u005d'; // unicode 编码
		
		String s1 = "";
		String s2 = "Hello";
		String s3 = "\u005d\u005fB";
		System.out.println("a=" + a);
		System.out.println("ch=" + ch);
		System.out.println("ch1=" + ch1);
		System.out.println("c=" + c);
		
		System.out.println("s1=" + s1);
		System.out.println("s2=" + s2);
		System.out.println("s3=" + s3);
	}
}
