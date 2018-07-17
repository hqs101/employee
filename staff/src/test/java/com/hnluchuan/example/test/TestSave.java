package com.hnluchuan.example.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.staff.dao.CityDAO;
import com.hnluchuan.staff.model.City;
import com.hnluchuan.staff.model.Province;
import com.hnluchuan.staff.test.BaseTest;

public class TestSave extends BaseTest {

	@Autowired
	private CityDAO cityDAO;

	
	@Test
	public void test() throws Exception {
		City city = new City();
		city.setProvince(new Province(18L));
		cityDAO.create(city);
	}
}
