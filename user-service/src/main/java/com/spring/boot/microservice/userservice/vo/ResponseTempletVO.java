package com.spring.boot.microservice.userservice.vo;

import com.spring.boot.microservice.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTempletVO {
    private User user;
    private Department department;
}
