package com.hnluchuan.staff.dto.api.info;

import java.util.List;

import com.hnluchuan.staff.dto.api.DistrictAPIDTO;

public class DistrictInfoAPIDTO extends BaseInfoAPIDTO {
    
    public DistrictInfoAPIDTO(List<DistrictAPIDTO> districtInfos) {
        super();
        this.districtInfos = districtInfos;
    }

    private List<DistrictAPIDTO> districtInfos;

    /**
     * @return the districtInfos
     */
    public List<DistrictAPIDTO> getDistrictInfos() {
        return districtInfos;
    }

    /**
     * @param districtInfos the districtInfos to set
     */
    public void setDistrictInfos(List<DistrictAPIDTO> districtInfos) {
        this.districtInfos = districtInfos;
    }
    
}
