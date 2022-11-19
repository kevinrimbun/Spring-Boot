package net.javaSpring.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaSpring.springBoot.model.dto.ResponseData;
import net.javaSpring.springBoot.model.dto.UserDto;
import net.javaSpring.springBoot.model.entity.DetailUser;
import net.javaSpring.springBoot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
  
    private ResponseData<Object> responseData;
  
    @PostMapping
    public ResponseEntity<Object> signUp(@RequestBody UserDto request) {
      responseData = userService.register(request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  
    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody UserDto request) {
      responseData = userService.login(request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    // Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody UserDto request) {
      responseData = userService.updateUser(id, request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
