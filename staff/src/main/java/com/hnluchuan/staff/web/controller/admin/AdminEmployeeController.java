package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

import cn.jpush.api.report.UsersResult;
import com.hnluchuan.staff.dto.*;
import com.hnluchuan.staff.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.staff.model.Education;
import com.hnluchuan.staff.model.EmergencyContact;
import com.hnluchuan.staff.model.Experience;
import com.hnluchuan.staff.model.Family;
import com.hnluchuan.staff.model.Employee;
import com.hnluchuan.staff.model.User;

import com.hnluchuan.staff.dto.ExperienceDTO;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/employee", produces="text/html;charset=UTF-8")
public class AdminEmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EducationService educationService;
	@Autowired
	private EmergencyContactService emergencyContactService;
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private FamilyService familyService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			EmployeeDTO dto = employeeService.load(id);
			//查询教育信息
			EducationDTO e_q = new EducationDTO();
			e_q.setEmployee(dto);
			List<EducationDTO> dto_education = educationService.findAll(e_q);
			//查询紧急联系人信息
			EmergencyContactDTO em_q = new EmergencyContactDTO();
			em_q.setEmployee(dto);
			List<EmergencyContactDTO> dto_emergency = emergencyContactService.findAll(em_q);
			//查询经验信息
			ExperienceDTO ex_q = new ExperienceDTO();
			ex_q.setEmployee(dto);
			List<ExperienceDTO> dto_expericnce = experienceService.findAll(ex_q);
			//查询家庭信息
			FamilyDTO f_q = new FamilyDTO();
			f_q.setEmployee(dto);
			List<FamilyDTO> dto_family = familyService.findAll(f_q);

			map.put("n", dto);
			map.put("n_e", dto_education);
			map.put("n_em", dto_emergency);
			map.put("n_ex", dto_expericnce);
			map.put("n_f", dto_family);
		}
		return "admin/employee/employee_detail";
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(EmployeeInffoDTO dto) throws Exception {
		if (dto.getEmployee().getId() == null) {
			employeeService.create((EmployeeDTO) dto.getEmployee());
		} else {
			//取得input值
			EmployeeDTO old = employeeService.load(dto.getEmployee().getId());
			UserDTO old_user = userService.load(dto.getUser().getId());
			List<Education> old_s_e = dto.getEducations();
			List<EmergencyContact> old_s_em = dto.getEmergencys();
			List<Experience> old_s_ex = dto.getExperiences();
			List<Family> old_s_f = dto.getFamilies();

			//修改user表
			old_user.setUsername(dto.getUser().getUsername());
			old_user.setEmail(dto.getEmployee().getEmail());
			userService.updateAllFields(old_user);

			//设置员工信息表数据
			old.setId(dto.getEmployee().getId());
			old.setUser(dto.getEmployee().getUser());
			old.setPosition(dto.getEmployee().getPosition());
			old.setName(dto.getEmployee().getName());
			old.setBirth(dto.getEmployee().getBirth());
			old.setSex(dto.getEmployee().getSex());
			old.setHeight(dto.getEmployee().getHeight());
			old.setWeight(dto.getEmployee().getWeight());
			old.setPlace(dto.getEmployee().getPlace());
			old.setNation(dto.getEmployee().getNation());
			old.setPhone(dto.getEmployee().getPhone());
			old.setiDCard(dto.getEmployee().getiDCard());
			old.setSchool(dto.getEmployee().getSchool());
			old.setMajor(dto.getEmployee().getMajor());
			old.setEducation(dto.getEmployee().getEducation());
			old.setLocation(dto.getEmployee().getLocation());
			old.setAddress(dto.getEmployee().getAddress());
			old.setWechat(dto.getEmployee().getWechat());
			old.setEmail(dto.getEmployee().getEmail());
			old.setMarry(dto.getEmployee().getMarry());
			old.setInTime(dto.getEmployee().getInTime());
			old.setStatus(dto.getEmployee().getStatus());
			employeeService.updateAllFields(old);

			//紧急联系人信息
			Iterator it_em = old_s_em.iterator();		//用于插入数据
			//插入前先删除数据
			List<EmergencyContactDTO> del_q_em = emergencyContactService.findAll();
			if(del_q_em.size() > 0){
				for (EmergencyContactDTO del_dd : del_q_em) {
					if(del_dd.getEmployee().getId() == old.getId())
						emergencyContactService.deleteById(del_dd.getId());
				}
			}

			//插入数据
			while(it_em.hasNext()){
				EmergencyContact em = (EmergencyContact) it_em.next();
				em.setEmployee(old);
				EmergencyContactDTO emDTO = emergencyContactService.toDTO(em);
				if(em.getName() != null && !em.getName().equals("") )
					emergencyContactService.create(emDTO);
			}

			//工作经验信息
			Iterator it_ex = old_s_ex.iterator();		//用于插入数据
			//插入前先删除数据
			List<ExperienceDTO> del_q = experienceService.findAll();
			if(del_q.size() > 0){
				for (ExperienceDTO del_dd : del_q) {
					if(del_dd.getEmployee().getId() == old.getId())
						experienceService.deleteById(del_dd.getId());
				}
			}

			//插入数据
			while(it_ex.hasNext()){
				Experience ex = (Experience) it_ex.next();
				ex.setEmployee(old);
				ExperienceDTO exDTO = experienceService.toDTO(ex);
				if(ex.getReason() != null && !ex.getReason().equals(""))
					experienceService.create(exDTO);
			}

			//家庭成员信息
			Iterator it_f = old_s_f.iterator();		//用于插入数据
			//插入前先删除数据
			List<FamilyDTO> del_q_f = familyService.findAll();
			if(del_q_f.size() > 0){
				for (FamilyDTO del_dd : del_q_f) {
					if(del_dd.getEmployee().getId() == old.getId())
						familyService.deleteById(del_dd.getId());
				}
			}

			//插入数据
			while(it_f.hasNext()){
				Family f = (Family) it_f.next();
				f.setEmployee(old);
				FamilyDTO fDTO = familyService.toDTO(f);
				if(f.getName() != null && !f.getName().equals(""))
					familyService.create(fDTO);
			}

			//教育经历信息
			Iterator it_e = old_s_e.iterator();		//用于插入数据
			//插入前先删除数据
			List<EducationDTO> del_q_e = educationService.findAll();
			for (EducationDTO del_dd : del_q_e) {
				if(del_dd.getEmployee().getId() == old.getId())
					educationService.deleteById(del_dd.getId());
			}
			//插入数据
			while(it_e.hasNext()){
				Education e = (Education) it_e.next();
				e.setEmployee(old);
				EducationDTO eDTO = educationService.toDTO(e);
				if(e.getMajor() != null && !e.getMajor().equals(""))
					educationService.create(eDTO);
			}
		}
		return ok();
	}

	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		//先删除子表数据，再删除主表数据

		for(int i = 0 ; i < ids.length ; i++){
			EmployeeDTO employeeDTO = employeeService.load(ids[i]);  //用于删除user表

			//删除emergency
			EmergencyContactDTO em_del = new EmergencyContactDTO();
			em_del.setEmployee(employeeDTO);
			List<EmergencyContactDTO> emergencys = emergencyContactService.find(em_del , null);
			if(emergencys.size() > 0){
				for (EmergencyContactDTO em : emergencys) {
					if(em.getEmployee().getId() == ids[i])
						emergencyContactService.deleteById(em.getId());
				}
			}

			//删除experience
			ExperienceDTO ex_del = new ExperienceDTO();
			ex_del.setEmployee(employeeDTO);
			List<ExperienceDTO> experiences = experienceService.find(ex_del , null);
			if(experiences.size() > 0){
				for (ExperienceDTO ex : experiences) {
					if(ex.getEmployee().getId() == ids[i])
						experienceService.deleteById(ex.getId());
				}
			}

			//删除family
			FamilyDTO f_del = new FamilyDTO();
			f_del.setEmployee(employeeDTO);
			List<FamilyDTO> familys = familyService.find(f_del , null);
			if(familys.size() > 0){
				for (FamilyDTO f : familys) {
					if(f.getEmployee().getId() == ids[i])
						familyService.deleteById(f.getId());
				}
			}

			//删除education
			EducationDTO e_del = new EducationDTO();
			e_del.setEmployee(employeeDTO);
			List<EducationDTO> educations = educationService.find(e_del , null);
			if(educations.size() > 0){
				for (EducationDTO e : educations) {
					if(e.getEmployee().getId() == ids[i])
						educationService.deleteById(e.getId());
				}
			}

			//删除employee
			employeeService.deleteById(ids[i]);

			//删除user
			userService.deleteById(employeeDTO.getUser().getId());
		}

		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(EmployeeDTO dto, Boolean search, Map<String, Object> map, Page page) {
		List<EmployeeDTO> dtos = employeeService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		map.put("search", search);
		return "admin/employee/employee_list";
	}
	
	@RequestMapping(value = "/search")
	public String search(EmployeeDTO dto, Map<String, Object> map, Page page) {
		List<EmployeeDTO> dtos = employeeService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/employee/employee_search";
	}

	@RequestMapping(value = "/add")
	public String add(){
		return "admin/employee/employee_add";
	}

	@ResponseBody
	@RequestMapping(value = "/add_employee")
	public String add_employee(EmployeeInffoDTO dto){
		//新建user
		User user = dto.getUser();
		user.setType(0);
		List<User> user_list = new ArrayList<User>();
		user_list.add(user);
		List<UserDTO> userDTO = userService.toDTOs(user_list);
		long id = userService.create(userDTO.get(0));
		if(id > 0){
			//新建employee
			Employee employee = dto.getEmployee();
			user.setId(id);
			if(employee.getName() != null && !employee.getName().equals("")){
				employee.setUser(user);
				employee.setEmail(user.getEmail());
				List<Employee> employees_list = new ArrayList<Employee>();
				employees_list.add(employee);
				List<EmployeeDTO> employeeDTO = employeeService.toDTOs(employees_list);
				if(employees_list.size() >= 1){
					long create_id = employeeService.create(employeeDTO.get(0));
					if(create_id > 0){
						employee.setId(create_id);
						//新建education
						List<Education> education = dto.getEducations();
						if(education.size() > 0){
							for (Education e : education) {
								if(e.getMajor() != null && !e.getMajor().equals("")){
									e.setEmployee(employee);
									EducationDTO educationDTO = educationService.toDTO(e);
									educationService.create(educationDTO);
								}
							}
						}

						//新建experience
						List<Experience> experiences = dto.getExperiences();
						if(experiences.size() > 0){
							for (Experience ex : experiences) {
								if(ex.getReason() != null && !ex.getReason().equals("")){
									ex.setEmployee(employee);
									ExperienceDTO experienceDTO = experienceService.toDTO(ex);
									experienceService.create(experienceDTO);
								}
							}
						}

						//新建emergency
						List<EmergencyContact> emergencyContacts = dto.getEmergencys();
						if(emergencyContacts.size() > 0){
							for (EmergencyContact em : emergencyContacts) {
								if(em.getName() != null && !em.getName().equals("")){
									em.setEmployee(employee);
									EmergencyContactDTO emergencyContactDTO = emergencyContactService.toDTO(em);
									emergencyContactService.create(emergencyContactDTO);
								}
							}
						}

						//新建family
						List<Family> families = dto.getFamilies();
						if(families.size() > 0){
							for (Family f : families) {
								if(f.getName() != null && !f.getName().equals("")){
									f.setEmployee(employee);
									FamilyDTO familyDTO = familyService.toDTO(f);
									familyService.create(familyDTO);
								}
							}
						}
					}
				}
			}
		}
		return ok();
	}
}
