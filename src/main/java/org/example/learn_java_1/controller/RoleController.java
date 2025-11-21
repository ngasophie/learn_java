package org.example.learn_java_1.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.learn_java_1.request.PermissionRequest;
import org.example.learn_java_1.request.RoleRequest;
import org.example.learn_java_1.response.ApiResponse;
import org.example.learn_java_1.response.PermissionResponse;
import org.example.learn_java_1.response.RoleResponse;
import org.example.learn_java_1.service.PermissionService;
import org.example.learn_java_1.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    private RoleService roleService;
    @PostMapping()
    ApiResponse<RoleResponse> create(@RequestBody @Valid RoleRequest request) {
        ApiResponse<RoleResponse> response = new ApiResponse<>();
        response.setResult(roleService.create(request));
        return response;
    }
    @GetMapping()
    ApiResponse<List<RoleResponse>> getAll() {
        ApiResponse<List<RoleResponse>> response = new ApiResponse<>();
        response.setResult(roleService.getAll());
        return response;
    }
    @DeleteMapping("/{roleId}")
    ApiResponse<String> deleteRole(
            @PathVariable String roleId
    ) {
        ApiResponse<String> response = new ApiResponse<>();
        roleService.delete(roleId);
        response.setResult("Success");
        return response;
    }
}
