package com.anilakdemir.todoapp.controller;

import com.anilakdemir.todoapp.paylod.UserCreateRequest;
import com.anilakdemir.todoapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author anilakdemir
 * @date 14 Aralık 2021 Salı
 * @time 23:52
 */

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService usersService;

    public UsersController(UserService usersService) {
        this.usersService = usersService;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "userId") Long userId){
        this.usersService.deleteUserById(userId);
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }



}
