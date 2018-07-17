package com.hnluchuan.example.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

public class TestCyclicBarrier {

	@Test
	public void test() {
		CyclicBarrier cb = new CyclicBarrier(2);
		
		new Thread() {
			public void run() {
				// 先做自己的事
				System.out.println("线程1 随便做点什么。然后睡一觉");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					// 等所有的线程都执行到cb.await这一句话。意思就是等其它所有的线程。
					cb.await(); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				// 所有的线程都执行到了cb.await，然后就一起搞事
				System.out.println("线程1 搞事啊"); // 然后两个线程会在同一时间打印这条搞事的语句
				
			};
		}.start();
		
		new Thread() {
			public void run() {
				System.out.println("线程2 随便做点什么。然后睡一觉");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					cb.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println("线程2 搞事啊");
				
			};
		}.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
