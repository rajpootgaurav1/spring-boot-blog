package com.springboot.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))//if not given JPA will create the table with the name as class

public class Post {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    private String description;
    private  String content;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)

    private Set<Comment> comments = new HashSet<>();

}
