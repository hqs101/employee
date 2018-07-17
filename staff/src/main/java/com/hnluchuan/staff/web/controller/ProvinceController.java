package com.hnluchuan.staff.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnluchuan.staff.dto.CityDTO;
import com.hnluchuan.staff.dto.DistrictDTO;
import com.hnluchuan.staff.model.City;
import com.hnluchuan.staff.model.Province;

@Controller
@RequestMapping("province")
public class ProvinceController extends BaseController {

	@ResponseBody
    @RequestMapping("findArea")
    public String findArea(Long provinceId, Long cityId) throws Exception {
		if (cityId != null) {
			DistrictDTO query = new DistrictDTO();
			query.setCity(new City());
			query.getCity().setId(cityId);
			List<DistrictDTO> districts = districtService.find(query, null);
			JSONArray array = new JSONArray();
			for (DistrictDTO d : districts) {
				JSONObject json = new JSONObject();
				json.put("id", d.getId());
				json.put("name", d.getName());
				array.add(json);
			}
			return array.toJSONString();
		} else if (provinceId != null) {
			CityDTO query = new CityDTO();
			query.setProvince(new Province());
			query.getProvince().setId(provinceId);
			List<CityDTO> cities = cityService.find(query, null);
			JSONArray array = new JSONArray();
			for (CityDTO d : cities) {
				JSONObject json = new JSONObject();
				json.put("id", d.getId());
				json.put("name", d.getName());
				array.add(json);
			}
			return array.toJSONString();
		}
		return null;
    }
	
	
}	
