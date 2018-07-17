package com.hnluchuan.staff.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.hnluchuan.core.model.Event;

@Component
public class SimpleEventListener {

	@Async
	@TransactionalEventListener(value = Event.class)
	public void onEvent() {
		
	}
}
