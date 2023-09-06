package com.anwar.authspringsecurity.service;


import java.util.List;

import com.anwar.authspringsecurity.dto.UserDto;
import com.anwar.authspringsecurity.model.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}