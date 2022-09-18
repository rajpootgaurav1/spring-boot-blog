package com.springboot.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private long id;
    @NotBlank
    @Size(min = 2, message = "post title should have atleast 2 characters")
    private String title;
    @NotBlank
    @Size(min = 10,message = "post description should have atleast 10 characters")
    private String description;
    @NotEmpty
    private  String content;
    private Set<CommentDto> comments;
}
