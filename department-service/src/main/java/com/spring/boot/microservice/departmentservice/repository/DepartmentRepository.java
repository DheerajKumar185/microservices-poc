package com.spring.boot.microservice.departmentservice.repository;

import com.spring.boot.microservice.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository <Department, Long> {

    Department getByDepartmentId(Long id);
}
