package com.hnluchuan.staff.test.misc;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.hnluchuan.staff.dao.UserDAO;
import com.hnluchuan.staff.test.BaseTest;

public class ExampleThreadPool extends BaseTest {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void test() {
		userDAO.load(1L);
		ExecutorService pool = Executors.newFixedThreadPool(20);
		ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);
		List<ListenableFuture<String>> futures = Lists.newArrayList();
		for (int i = 0; i < 50; i++) {
			final int j = i;
			ListenableFuture<String> f = service.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.out.println("call " + j + ", " + Thread.currentThread().getName());
					return "";
				}
			});
			futures.add(f);
		}
		try {
			Futures.successfulAsList(futures).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
		System.out.println("done...");
	
	}

}
