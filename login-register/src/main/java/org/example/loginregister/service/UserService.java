package org.example.loginregister.service;

import org.example.loginregister.dto.UserDto;
import org.example.loginregister.entity.User;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);
    Boolean checkPasswordUser(String email,String password);
    Boolean checkUserbyEmail(String email);
    User getUserbyEmail(String email);
}
