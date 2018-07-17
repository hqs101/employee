package com.hnluchuan.example.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.hnluchuan.example.service.ExampleConcurrentService3;
import com.hnluchuan.example.service.ExampleConcurrentService4;
import com.hnluchuan.staff.test.BaseTest;

public class TestAddMoneyOk3 extends BaseTest {

	@Autowired
	private ExampleConcurrentService4 service4;
	
	@Autowired
	private DataSource dataSource;
	
	/** 
	 * 有bug，可能会导致余额<0
	 * @throws Exception
	 */
	@Rollback(false)
	@Test
	public void test() throws Exception {
		final Long id = 1L;
		final int amount = 10;
		new Thread() {
			public void run() {
				service4.addMoney(id, amount);
			};
		}.start();
		service4.addMoney(id, amount);
		
		Thread.sleep(5000);
	}
}
