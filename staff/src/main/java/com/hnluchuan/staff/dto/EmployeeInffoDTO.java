package com.hnluchuan.staff.dto;

import com.hnluchuan.staff.model.*;

import java.util.List;

public class EmployeeInffoDTO{

    private UserDTO user;
    private EmployeeDTO employee;
    private List<EducationDTO> educations;
    private List<EmergencyContactDTO> emergencys;
    private List<ExperienceDTO> experiences;
    private List<FamilyDTO> families;

    public EmployeeInffoDTO(UserDTO user , EmployeeDTO employee , List<EducationDTO> educations ,
                            List<EmergencyContactDTO> emergencys , List<ExperienceDTO> experiences ,
                            List<FamilyDTO> families){
        super();
        this.user = user;
        this.employee = employee;
        this.educations = educations;
        this.experiences = experiences;
        this.emergencys = emergencys;
        this.families = families;
    }

    public EmployeeInffoDTO(){
        super();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

    public List<EmergencyContactDTO> getEmergencys() {
        return emergencys;
    }

    public void setEmergencys(List<EmergencyContactDTO> emergencys) {
        this.emergencys = emergencys;
    }

    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public List<FamilyDTO> getFamilies() {
        return families;
    }

    public void setFamilies(List<FamilyDTO> families) {
        this.families = families;
    }

}
