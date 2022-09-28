package com.springboot.blog.repository;

import com.springboot.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//extends jpa repo has first input as table name and the second as the datatype of primary key of the table
public interface UserRepository extends JpaRepository<User, Long> {
    ///generic method to find by email
    Optional<User> findByEmail(String email);
    ///method to get User by their username or email
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
