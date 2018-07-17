package com.hnluchuan.example.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.staff.dao.ProvinceDAO;
import com.hnluchuan.staff.dto.ProvinceDTO;
import com.hnluchuan.staff.model.City;
import com.hnluchuan.staff.model.Province;
import com.hnluchuan.staff.service.ProvinceService;
import com.hnluchuan.staff.test.BaseTest;

public class TestLazyLoading extends BaseTest {

	@Autowired
	private ProvinceDAO provinceDAO;
	@Autowired
	private ProvinceService provinceService;

	/**
	 * 测试懒加载。
	 * 为了查看效果，请配置applicationContext.xml的show_sql为true
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		Long id = 18L;
		Province province = provinceDAO.load(id);
		System.out.println("省: " + province.getName()); // 到这里只会执行一条sql
		
		Thread.sleep(5000);
		
		City city = province.getCitys().get(0);
		System.out.println("城市: " + city.getName()); // 到这里又执行了一条sql
		
		Thread.sleep(5000);
		ProvinceDTO provinceDTO = provinceService.load(id); 
		System.out.println("provinceDTO: " + provinceDTO.getName()); // 这里就会执行14条sql了(因为在toDTO里，访问了对象所有的属性）（湖南省有14个城市）
	}
}
