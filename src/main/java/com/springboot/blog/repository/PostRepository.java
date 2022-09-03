package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    //no code here as this will have all the crud implementation
    //no need to add @epository as jpa repository has implementation of simplejparepository
//    which has the @repository
}
