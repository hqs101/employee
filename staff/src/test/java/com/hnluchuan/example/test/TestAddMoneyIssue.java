package com.hnluchuan.example.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.example.service.ExampleConcurrentService;
import com.hnluchuan.staff.test.BaseTest;

public class TestAddMoneyIssue extends BaseTest {

	@Autowired
	private ExampleConcurrentService service;
	
	/*
	 * 运行这个测试之后，虽然用户的amount被加了两次10，但最终保存到数据库的只是增加了一个10。这是有问题的。
	 * TestAddMoneyOk1和TestAddMoneyOk2都不会有这种问题
	 */
	@Test
	public void test() throws Exception {
		final Long id = 1L;
		final int amount = 10;
		new Thread() {
			public void run() {
				service.addMoney_issue(id, amount);
			};
		}.start();
		service.addMoney_issue(id, amount);
		
		Thread.sleep(3000);
	}
}
