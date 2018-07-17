package com.hnluchuan.staff.security;

public class TestPasswordEncoder {
	public static void main(String[] args) {
	    System.out.println(new PasswordEncoder().encodePassword("admin", "123456"));
    }
}
