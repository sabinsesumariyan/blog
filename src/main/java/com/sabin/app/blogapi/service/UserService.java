package com.sabin.app.blogapi.service;

import com.sabin.app.blogapi.payload.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO,Integer userId);
    UserDTO getByUserId(Integer userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);
}
