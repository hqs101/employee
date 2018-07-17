package com.hnluchuan.example.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.hnluchuan.example.service.ExampleConcurrentService3;
import com.hnluchuan.staff.test.BaseTest;

public class TestAddMoneyOk2 extends BaseTest {

	@Autowired
	private ExampleConcurrentService3 service3;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * 参考 ExampleQueueInitEventListener
	 * @throws Exception
	 */
	@Rollback(false)
	@Test
	public void test() throws Exception {
		final int amount = 10;
		new Thread() {
			public void run() {
				service3.getQueue().add(amount);
			};
		}.start();
		service3.getQueue().add(amount);
		
		Thread.sleep(5000);
	}
}
