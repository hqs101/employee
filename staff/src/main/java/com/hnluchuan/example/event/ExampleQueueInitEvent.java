package com.hnluchuan.example.event;

import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.context.ApplicationEvent;

public class ExampleQueueInitEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	public ExampleQueueInitEvent(ArrayBlockingQueue<Integer> queue) {
		super(queue);
	}

}
