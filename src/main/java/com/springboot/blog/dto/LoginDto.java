package com.springboot.blog.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String userNameorEmail;
    private String password;
}
