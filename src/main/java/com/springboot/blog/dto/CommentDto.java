package com.springboot.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private Long id;
    @NotEmpty(message = "name should not be null or empty")
    private String name;
    @NotEmpty(message = "email should not be empty or null")
    @Email(message = "email is not correct.")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "comment should not be less than 10 characters.")
    private String body;
}
