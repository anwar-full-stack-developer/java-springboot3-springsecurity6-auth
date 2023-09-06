package com.anwar.authspringsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.anwar.authspringsecurity.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}