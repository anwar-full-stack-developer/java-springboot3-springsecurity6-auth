package com.anwar.authspringsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.anwar.authspringsecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}