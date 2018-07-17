package com.hnluchuan.staff.web.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnluchuan.staff.dto.CityDTO;
import com.hnluchuan.staff.dto.ProvinceDTO;
import com.hnluchuan.staff.model.Province;

@Controller
@RequestMapping(value = "/common", produces="text/html;charset=UTF-8")
public class CommonController extends BaseController {
	
	private static String basePackge = "com.xxx.common.common";

	@ResponseBody
	@RequestMapping("getProvinceList")
	public String getProvinceList() throws Exception {
		List<ProvinceDTO> provinces = provinceService.findAll();
		JSONArray array = new JSONArray();
		for (ProvinceDTO p : provinces) {
			JSONObject json = new JSONObject();
			json.put("id", p.getId());
			json.put("name", p.getName());
			array.add(json);
		}
		return array.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("getCityList")
	public String getCityList(Long provinceId) throws Exception {
		if (provinceId == null) {
			return "[]";
		}
		CityDTO query = new CityDTO();
		query.setProvince(new Province(provinceId));
		
		List<CityDTO> dtos = cityService.findAll(query);
		JSONArray array = new JSONArray();
		for (CityDTO d : dtos) {
			JSONObject json = new JSONObject();
			json.put("id", d.getId());
			json.put("name", d.getName());
			array.add(json);
		}
		return array.toJSONString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping("getEnum")
	public String getEnum(String name) throws Exception {
		String className = basePackge + "." + name;
		JSONArray array = new JSONArray();
		Class clz = Class.forName(className);
        Method values = clz.getMethod("values");
        Object[] enums = (Object[]) values.invoke(null);
        for (Object e : enums) {
            int eValue = (Integer) e.getClass().getMethod("getValue").invoke(e);
            String remark = (String) e.getClass().getMethod("getRemark").invoke(e);
            JSONObject json = new JSONObject();
			json.put("id", eValue);
			json.put("name", remark);
			array.add(json);
        }
        
		return array.toJSONString();
	}
}
