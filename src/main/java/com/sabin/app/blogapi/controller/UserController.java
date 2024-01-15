package com.sabin.app.blogapi.controller;

import com.sabin.app.blogapi.payload.ApiResponse;
import com.sabin.app.blogapi.payload.UserDTO;
import com.sabin.app.blogapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable("userId") Integer userId){
        UserDTO updateUserDTO = userService.updateUser(userDTO,userId);
        return ResponseEntity.ok(updateUserDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getByUserId(@PathVariable Integer userId){
        UserDTO getUserDTO = userService.getByUserId(userId);
        return ResponseEntity.ok(getUserDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("Deleted successfully",true),HttpStatus.OK);
    }

}
