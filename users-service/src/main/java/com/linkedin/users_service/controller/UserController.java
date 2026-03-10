package com.linkedin.users_service.controller;

import com.linkedin.users_service.dto.UserProfileDto;
import com.linkedin.users_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<UserProfileDto> updateProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileDto dto) {
        return ResponseEntity.ok(userService.updateProfile(userId, dto));
    }

    @PostMapping("/profile")    
    public ResponseEntity<UserProfileDto> createProfile(@RequestBody UserProfileDto dto) {
        return ResponseEntity.ok(userService.createProfile(dto));
    }
}