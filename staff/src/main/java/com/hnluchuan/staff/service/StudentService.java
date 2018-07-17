package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.dao.StudentDAO;
import com.hnluchuan.staff.dto.StudentDTO;
import com.hnluchuan.staff.model.Student;

@Service
public class StudentService extends BaseService {
	
	@Autowired
	private StudentDAO studentDAO;
	
	public List<StudentDTO> find(StudentDTO query, Page page) {
		List<Student> models = studentDAO.find(query, page);
		List<StudentDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<StudentDTO> findAll() {
		return this.find(new StudentDTO(), null);
	}
	
	public List<StudentDTO> findAll(StudentDTO query) {
		return this.find(query, null);
	}
	
	public List<StudentDTO> find(Page page) {
		return this.find(new StudentDTO(), page);
	}
	
	public List<StudentDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new StudentDTO(), page);
	}
	
	public Long create(StudentDTO dto) {
		Student model = new Student();
		toModel(model, dto);
		return studentDAO.create(model);
	}
	
	public StudentDTO load(Long id) {
	    Student model = studentDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(StudentDTO dto) {
		Student model = studentDAO.load(dto.getId());
		toModel(model, dto);
		studentDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		throw new BizException("默认禁止删除，请确认是物理删除还是逻辑删除");
		/*
		if (ids != null) {
			for (Long id : ids) {
				studentDAO.deleteById(id);
			}
		} */
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public StudentDTO findOne(StudentDTO query) {
		Student model = studentDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<StudentDTO> toDTOs(List<Student> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<StudentDTO>(0);
		}
		List<StudentDTO> dtos = new ArrayList<StudentDTO>(models.size());
		for (Student model : models) {
	        StudentDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private StudentDTO toDTO(Student model) {
		if (model == null) {
			return null;
		}
		StudentDTO dto = BeanMapper.map(model, StudentDTO.class);
		
		return dto;
	}
	
	private void toModel(Student model, StudentDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Student> toModels(List<StudentDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Student>(0);
		}
		List<Student> models = new ArrayList<Student>(dtos.size());
		for (StudentDTO dto : dtos) {
	        Student model = new Student();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
