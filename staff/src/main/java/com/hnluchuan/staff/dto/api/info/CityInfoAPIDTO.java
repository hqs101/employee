package com.hnluchuan.staff.dto.api.info;

import java.util.List;

import com.hnluchuan.staff.dto.api.CityAPIDTO;

public class CityInfoAPIDTO extends BaseInfoAPIDTO {
    
    public CityInfoAPIDTO(List<CityAPIDTO> cityInfos) {
        super();
        this.cityInfos = cityInfos;
    }

    private List<CityAPIDTO> cityInfos;

    /**
     * @return the cityInfos
     */
    public List<CityAPIDTO> getCityInfos() {
        return cityInfos;
    }

    /**
     * @param cityInfos the cityInfos to set
     */
    public void setCityInfos(List<CityAPIDTO> cityInfos) {
        this.cityInfos = cityInfos;
    }
    
}
