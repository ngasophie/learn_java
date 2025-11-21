package org.example.learn_java_1.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.learn_java_1.entity.Permission;
import org.example.learn_java_1.entity.Role;
import org.example.learn_java_1.mapper.PermissionMapper;
import org.example.learn_java_1.mapper.RoleMapper;
import org.example.learn_java_1.repository.PermissionRepository;
import org.example.learn_java_1.repository.RoleRepository;
import org.example.learn_java_1.request.PermissionRequest;
import org.example.learn_java_1.request.RoleRequest;
import org.example.learn_java_1.response.PermissionResponse;
import org.example.learn_java_1.response.RoleResponse;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;
    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        if (!request.getPermissions().isEmpty()) {
            List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
            Set<Permission> permissionUnique = new HashSet<>();
            permissions.stream().map(permission -> permissionUnique.add(permission)).toList();
            role.setPermissions(permissionUnique);
        }
        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
