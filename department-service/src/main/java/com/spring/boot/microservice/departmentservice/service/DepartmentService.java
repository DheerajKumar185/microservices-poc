package com.spring.boot.microservice.departmentservice.service;

import com.spring.boot.microservice.departmentservice.entity.Department;
import com.spring.boot.microservice.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartment(Long id) {
        return departmentRepository.getByDepartmentId(id);
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }
}
