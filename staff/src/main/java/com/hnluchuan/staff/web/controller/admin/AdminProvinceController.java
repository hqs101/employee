package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.CityDTO;
import com.hnluchuan.staff.dto.DistrictDTO;
import com.hnluchuan.staff.dto.ProvinceDTO;
import com.hnluchuan.staff.model.City;
import com.hnluchuan.staff.model.Province;
import com.hnluchuan.staff.service.ProvinceService;
import com.hnluchuan.staff.web.controller.BaseController;


@Controller
@RequestMapping(value = "/admin/province", produces="text/html;charset=UTF-8")
public class AdminProvinceController extends BaseController {

	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			ProvinceDTO dto = provinceService.load(id);
			map.put("n", dto);
		}
		return "admin/province/province_detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(ProvinceDTO dto) throws Exception {
		if (dto.getId() == null) {
			provinceService.create(dto);
		} else {
			provinceService.updateAllFields(dto);
		}
		return ok();
	}

    
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		provinceService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(ProvinceDTO dto, Map<String, Object> map, Page page) {
		List<ProvinceDTO> dtos = provinceService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/province/province_list";
	}
	
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
