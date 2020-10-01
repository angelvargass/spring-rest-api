package com.example.springrestapi.api;

import com.example.springrestapi.model.User;
import com.example.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

     @PostMapping("api/user/add")
    public ResponseEntity addUser(@NonNull @RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("api/user/getall")
    public List<User> getAllUsers() {
        return userService.retrieveAll();
    }

    @GetMapping("api/user/getbyid")
    public User getUserById(@NonNull @RequestParam UUID id) {
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping("api/user/delete")
    public void deleteUserById(@NonNull @RequestParam UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping("api/user/update")
    public ResponseEntity updateUser(@NonNull @RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
