package com.hnluchuan.example.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.example.service.ExampleConcurrentService;
import com.hnluchuan.staff.test.BaseTest;

public class TestAddMoneyOk1 extends BaseTest {

	@Autowired
	private ExampleConcurrentService service;
	
	//@Rollback(false)
	@Test
	public void test() throws Exception {
		final Long id = 1L;
		final int amount = 10;
		new Thread() {
			public void run() {
				service.addMoney(id, amount);
			};
		}.start();
		service.addMoney(id, amount);
		
		Thread.sleep(3000);
	}
}
