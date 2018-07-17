package com.hnluchuan.staff.model;

import java.util.List;

import com.hnluchuan.staff.model.*;

public class EmployeeInfo {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    private Employee employee;
    private List<Education> educations;
    private List<EmergencyContact> emergencys;
    private List<Experience> experiences;
    private List<Family> families;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<EmergencyContact> getEmergencys() {
        return emergencys;
    }

    public void setEmergencys(List<EmergencyContact> emergencys) {
        this.emergencys = emergencys;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
    }

    public EmployeeInfo(){
        super();
    }

    public EmployeeInfo(Employee employee , List<Education> educations ,
                        List<EmergencyContact> emergencys , List<Experience> experiences ,
                        List<Family> families ){
        super();
        this.employee = employee;
        this.educations = educations ;
        this.emergencys = emergencys;
        this.experiences = experiences;
        this.families = families;
    }
}
