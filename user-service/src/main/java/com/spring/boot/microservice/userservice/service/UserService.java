package com.spring.boot.microservice.userservice.service;

import com.spring.boot.microservice.userservice.entity.User;
import com.spring.boot.microservice.userservice.repository.UserRepository;
import com.spring.boot.microservice.userservice.vo.Department;
import com.spring.boot.microservice.userservice.vo.ResponseTempletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTempletVO getByUserId(Long userId) {
        User user = userRepository.findUserById(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
        ResponseTempletVO vo = new ResponseTempletVO();
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
