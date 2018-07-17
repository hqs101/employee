package com.hnluchuan.staff.test.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	private static Logger logger = LoggerFactory.getLogger(TestLog.class);
	
	@Test
	public void test() {
		logger.error("测试slf4j环境和占位符: {}", 1);
	}
}
