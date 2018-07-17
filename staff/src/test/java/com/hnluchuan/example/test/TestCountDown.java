package com.hnluchuan.example.test;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class TestCountDown {

	@Test
	public void test() {
		CountDownLatch latch = new CountDownLatch(2); // 2就是计数。
		
		new Thread() {
			@Override
			public void run() {
				try {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} finally {
					latch.countDown(); // 做完自己的事后将计算数减1
				}
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				try {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} finally {
					latch.countDown(); // 做完自己的事后将计算数减1
				}
			}
		}.start();
		
		try {
			latch.await(); // 在这里会等待所有的线程都执行完，才会继续往下走
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
}
