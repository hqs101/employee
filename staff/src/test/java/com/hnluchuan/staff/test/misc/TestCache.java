package com.hnluchuan.staff.test.misc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.staff.service.CacheService;
import com.hnluchuan.staff.test.BaseTest;

public class TestCache extends BaseTest {
	
	@Autowired
	private CacheService cacheService;
	
	@Test
	public void test() {
		String key = "testkey";
		Integer value = 100;
		cacheService.set(key, value);
		Integer value2 = cacheService.get(key);
		Assert.assertTrue(value.intValue() == value2);
	}
}
