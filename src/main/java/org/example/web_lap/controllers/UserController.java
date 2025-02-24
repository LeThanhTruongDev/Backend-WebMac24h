package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.fillter.RoleParam;
import org.example.web_lap.dtos.fillter.UserParam;
import org.example.web_lap.dtos.request.LoginRequest;
import org.example.web_lap.dtos.request.UserRequest;
import org.example.web_lap.dtos.response.ApiResponse;
import org.example.web_lap.entities.Roles;
import org.example.web_lap.entities.User;
import org.example.web_lap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @GetMapping
    public Page<User> filter(UserParam param , Pageable pageable) {
        return userService.filter(param, pageable);
    }

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

}
