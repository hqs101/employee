package com.hnluchuan.staff.dto.api.info;

import java.util.List;

import com.hnluchuan.staff.dto.api.ProvinceAPIDTO;

public class ProvinceInfoAPIDTO extends BaseInfoAPIDTO {
    
    public ProvinceInfoAPIDTO(List<ProvinceAPIDTO> provinceInfos) {
        super();
        this.provinceInfos = provinceInfos;
    }

    private List<ProvinceAPIDTO> provinceInfos;

    /**
     * @return the provinceInfos
     */
    public List<ProvinceAPIDTO> getProvinceInfos() {
        return provinceInfos;
    }

    /**
     * @param provinceInfos the provinceInfos to set
     */
    public void setProvinceInfos(List<ProvinceAPIDTO> provinceInfos) {
        this.provinceInfos = provinceInfos;
    }
    
}
