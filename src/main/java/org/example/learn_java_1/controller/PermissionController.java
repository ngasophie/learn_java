package org.example.learn_java_1.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.learn_java_1.entity.User;
import org.example.learn_java_1.request.PermissionRequest;
import org.example.learn_java_1.request.UserCreationRequest;
import org.example.learn_java_1.request.UserUpdateRequest;
import org.example.learn_java_1.response.ApiResponse;
import org.example.learn_java_1.response.PermissionResponse;
import org.example.learn_java_1.response.UserResponse;
import org.example.learn_java_1.service.PermissionService;
import org.example.learn_java_1.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    private PermissionService permissionService;
    @PostMapping()
    ApiResponse<PermissionResponse> create(@RequestBody @Valid PermissionRequest request) {
        ApiResponse<PermissionResponse> response = new ApiResponse<>();
        response.setResult(permissionService.create(request));
        return response;
    }
    @GetMapping()
    ApiResponse<List<PermissionResponse>> getAll() {
        ApiResponse<List<PermissionResponse>> response = new ApiResponse<>();
        response.setResult(permissionService.getAll());
        return response;
    }
    @DeleteMapping("/{permissionId}")
    ApiResponse<String> deleteUser(
            @PathVariable String permissionId
    ) {
        ApiResponse<String> response = new ApiResponse<>();
        permissionService.delete(permissionId);
        response.setResult("Success");
        return response;
    }
}
