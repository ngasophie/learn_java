package org.example.learn_java_1.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.learn_java_1.entity.User;
import org.example.learn_java_1.response.ApiResponse;
import org.example.learn_java_1.request.UserCreationRequest;
import org.example.learn_java_1.request.UserUpdateRequest;
import org.example.learn_java_1.response.UserResponse;
import org.example.learn_java_1.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private UserService userService;
    @PostMapping()
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        return response;
    }
    @GetMapping()
    ApiResponse<List<UserResponse>> getListUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getUsers());
        return response;
    }
    @GetMapping("/{userId}")
    ApiResponse<User> getUser(
            @PathVariable String userId
    ) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(userService.getUserById(userId));
        return response;
    }
    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(
            @RequestBody UserUpdateRequest request,
            @PathVariable String userId
    ) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(userService.updateUser(userId, request));
        return response;
    }
    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(
            @PathVariable String userId
    ) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("Success");
        return response;
    }
}
