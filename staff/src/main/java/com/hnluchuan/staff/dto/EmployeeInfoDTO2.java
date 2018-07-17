package com.hnluchuan.staff.dto;

import com.hnluchuan.staff.model.*;

import java.util.List;

public class EmployeeInfoDTO2 {

    private UserDTO userDTO;
    private EmployeeDTO employeeDTO;
    private List<EducationDTO> educationsDTO;
    private List<EmergencyContactDTO> emergencysDTO;
    private List<ExperienceDTO> experiencesDTO;
    private List<FamilyDTO> familiesDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public List<EducationDTO> getEducationsDTO() {
        return educationsDTO;
    }

    public void setEducationsDTO(List<EducationDTO> educationsDTO) {
        this.educationsDTO = educationsDTO;
    }

    public List<EmergencyContactDTO> getEmergencysDTO() {
        return emergencysDTO;
    }

    public void setEmergencysDTO(List<EmergencyContactDTO> emergencysDTO) {
        this.emergencysDTO = emergencysDTO;
    }

    public List<ExperienceDTO> getExperiencesDTO() {
        return experiencesDTO;
    }

    public void setExperiencesDTO(List<ExperienceDTO> experiencesDTO) {
        this.experiencesDTO = experiencesDTO;
    }

    public List<FamilyDTO> getFamiliesDTO() {
        return familiesDTO;
    }

    public void setFamiliesDTO(List<FamilyDTO> familiesDTO) {
        this.familiesDTO = familiesDTO;
    }



}
