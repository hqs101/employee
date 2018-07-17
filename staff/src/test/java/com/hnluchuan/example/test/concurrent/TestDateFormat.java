package com.hnluchuan.example.test.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class TestDateFormat {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * SimpleDateFormat是线程不安全的，会出现这样的情况：
	 *  1: 2018-02-07 09:33:35
		2: 2018-02-08 09:33:35   （2有两种值了)
		2: 2018-02-07 09:33:35
	 */
	@Test
	public void test() {
		int threads = 10;
		int times = 100;
		Date date1 = new Date();
		Date date2 = DateUtils.addDays(date1, 1);
		for (int i = 0; i < threads; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < times; j++) {
						System.out.println("1: " + dateFormat.format(date1));
						System.out.println("2: " + dateFormat.format(date2));
					}
				};
			}.start();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
