package com.hnluchuan.example.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.hnluchuan.staff.model.User;

public class TestLock {

	private static Lock lock = new ReentrantLock();
	
	private User user = null;
	
	public void test1() {
		// 
		synchronized (TestLock.class) { // 没法知道当前锁的状态，第一个线程进入后，第二个线程会一直等待
			// do something
		}
		
	}
	
	public void test2() {
		try {
			/// thread2
			lock.lock();
			//  
		} finally {
			lock.unlock(); // 防止发生异常
		}
	}
	
	public void test3() {
		if (lock.tryLock()) { // 如果其它线程没有在执行
			// boolean tryLock(long time, TimeUnit unit) // 等待指定时间
			try {
				lock.lock();
			} finally {
				lock.unlock(); // 防止发生异常
			}
		} else {
			// 如果其它线程在执行了
		}
	}
	
	public void test4() {
		if (user == null) {
			synchronized (TestLock.class) {
				if (user == null) {
					user = new User();
				}
			}
		}
		
		if (lock.tryLock()) {
			if (user == null) {
				user = new User();
			}
		}
		
		// 错误1
		if (user == null) {
			///
			synchronized (TestLock.class) {
				///
				user = new User();
			}
		}
		
		// 错误2
		if (user == null) {
			
			try {
				lock.lock();
				user = new User();
			} finally {
				lock.unlock();
			}
		}
	
 	}
}
