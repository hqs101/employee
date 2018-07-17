package com.hnluchuan.example.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.core.dao.EventDAO;
import com.hnluchuan.core.model.Event;
import com.hnluchuan.utils.common.SerializeUtils;
import com.hnluchuan.staff.test.BaseTest;

public class TestSimpleEvent extends BaseTest {

	@Autowired
	private EventDAO eventDAO;
	
	@Test
	public void test() {
		Event e = new Event();
		e.setData(SerializeUtils.serialize("he"));
		eventDAO.create(e);
		
		e = eventDAO.findOne("from Event", null);
		Object obj = SerializeUtils.deSerialize(e.getData());
		Assert.assertTrue(obj instanceof String);
	}
}
