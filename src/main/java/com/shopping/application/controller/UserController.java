package com.shopping.application.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.application.dto.UserDto;
import com.shopping.application.mapper.UserMapper;
import com.shopping.application.models.User;
import com.shopping.application.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService,UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    
    @GetMapping
    ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userMapper.usersToUserDtos(userService.getAll()));
    }
    
    @PostMapping
    ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        
        User user = userMapper.userDtoToUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToUserDto(userService.createUser(user)));
    }
    
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getById(@PathVariable String id){
        return ResponseEntity.ok(userMapper.userToUserDto(userService.getById(id)));
    }
    
    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable String id, @RequestBody UserDto userDto){
        if(!id.equals(userDto.getId())) {
            return ResponseEntity.badRequest().body("The Id in parameter must be the same in the body of the request");
        }
        User user = userMapper.userDtoToUser(userDto);
        return ResponseEntity.ok(userMapper.userToUserDto(userService.updateUser(user)));
    }
        
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable String id, @RequestBody UserDto userDto){
        if(id.equals(userDto.getId())) {
            return ResponseEntity.badRequest().body("The Id in parameter must be the same in the body of the request");
        }
        userService.deleteUser(userMapper.userDtoToUser(userDto));
        return ResponseEntity.ok().build();
    }
}
