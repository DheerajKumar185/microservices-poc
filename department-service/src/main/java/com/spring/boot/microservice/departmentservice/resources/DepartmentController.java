package com.spring.boot.microservice.departmentservice.resources;

import com.spring.boot.microservice.departmentservice.entity.Department;
import com.spring.boot.microservice.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return departmentService.getDepartment(id);
    }

    @GetMapping("/")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }
}
