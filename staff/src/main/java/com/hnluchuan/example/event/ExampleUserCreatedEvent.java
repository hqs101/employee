package com.hnluchuan.example.event;

import org.springframework.context.ApplicationEvent;

public class ExampleUserCreatedEvent extends ApplicationEvent {

	public ExampleUserCreatedEvent(Long userId) {
		super(userId);
	}

	private static final long serialVersionUID = 1L;

}
