package com.spring.boot.microservice.userservice.resources;

import com.spring.boot.microservice.userservice.entity.User;
import com.spring.boot.microservice.userservice.service.UserService;
import com.spring.boot.microservice.userservice.vo.ResponseTempletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControler {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTempletVO getByUserId(@PathVariable("id") Long userId) {
        return userService.getByUserId(userId);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
