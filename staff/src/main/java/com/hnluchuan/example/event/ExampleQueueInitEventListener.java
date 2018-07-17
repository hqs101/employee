package com.hnluchuan.example.event;

import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.hnluchuan.example.service.ExampleConcurrentService3;

@Component
public class ExampleQueueInitEventListener implements ApplicationListener<ExampleQueueInitEvent> {

	@Autowired
	private ExampleConcurrentService3 service3;
	
	// 异步，不要配置@transactional
	@SuppressWarnings("unchecked")
	@Async
	@Override
	public void onApplicationEvent(ExampleQueueInitEvent event) {
		final ArrayBlockingQueue<Integer> queue = (ArrayBlockingQueue<Integer>) event.getSource();
		new Thread() {
			public void run() {
				while (true) {
					try {
						Integer amount = queue.take();
						service3.addMoney(1L, amount);
						//break;
					} catch (Exception e) {
						e.printStackTrace();
					};
					
				}
			};
		}.start();
	}

}
