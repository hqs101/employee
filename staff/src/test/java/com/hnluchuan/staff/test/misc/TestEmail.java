package com.hnluchuan.staff.test.misc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.staff.service.EmailService;
import com.hnluchuan.staff.test.BaseTest;

public class TestEmail extends BaseTest {
	
	@Autowired
	private EmailService emailService;
	
	@Test
	public void test() {
		emailService.sendMail("JUnit Test", "Unit Test Content", "441868461@qq.com");
	}
}
