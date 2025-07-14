package com.shourov.validation_example.controller;

import com.shourov.validation_example.dto.UserRequest;
import com.shourov.validation_example.entity.User;
import com.shourov.validation_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(request));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

}
