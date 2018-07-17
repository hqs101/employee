package com.hnluchuan.example.test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

public class TestWaitNotify {

	@Test
	public void test() throws Exception {
		int cap = 10;
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(cap);
		
		// 第一个线程，生产者，给queue里丢数据
		new Thread() {
			public void run() {
				while (true) {
					synchronized (queue) { // 两个线程一定要锁同一个对象
						while (queue.size() == cap) { // 如果已经满了，就不生产了，等消费者。 // 这个是条件，满足这个条件本线程就会等
							try {
								System.out.println("生产满了，坐等消费者");
								queue.wait(); // 等其它线程的通知了，收到了notify通知就不再阻塞了
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						queue.add(new Random().nextInt(10) + "");
						System.out.println("生产者产生了一个");
						queue.notifyAll(); // 一定要在synchroized里面调wait和notifyAll。 notify只会通知一个线程，notifyAll会通知所有线程。
					}
					
				}
			};
		}.start();
		
		// 第二个线程，消费者，从queue里取数据
		new Thread() {
			public void run() {
				while (true) {
					synchronized (queue) {
						while (queue.size() == 0) { // 如果为0了就不要现消费了，等生产者
							try {
								System.out.println("全部消费完了，坐等生产者");
								queue.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						String str = queue.poll();
						System.out.println("消费取出一个来了: " + str);
						queue.notifyAll();
					}
				}
			};
		}.start();
		
		Thread.sleep(5000);
	}
}
