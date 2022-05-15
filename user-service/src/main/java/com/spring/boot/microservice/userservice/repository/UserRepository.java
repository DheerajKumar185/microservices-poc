package com.spring.boot.microservice.userservice.repository;

import com.spring.boot.microservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findUserById(Long userId);
}
