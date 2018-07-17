package com.hnluchuan.example.test.lock;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class TestAtomic {

	private int a = 0;
	private AtomicInteger b = new AtomicInteger(0);
	
	@Test
	public void test() {
		int threads = 10;
		int times = 100;
		for (int i = 0; i < threads; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < times; j++) {
						a = a + 1;
						b.addAndGet(1);
					}
				};
			}.start();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("a: " + a);
		System.out.println("b: " + b.get());
		
	}
}
