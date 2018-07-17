package com.hnluchuan.example.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.hnluchuan.example.service.ExampleTransactionService;
import com.hnluchuan.staff.test.BaseTest;

public class TestNewTransaction extends BaseTest {

	@Autowired
	private ExampleTransactionService transactionExampleService;
	
	@Autowired
	private DataSource dataSource;
	
	@Rollback(false)
	@Test
	public void test() throws Exception {
		System.out.println("support: " + dataSource.getConnection().getMetaData().supportsSavepoints());
		transactionExampleService.a_new();
	}
}
