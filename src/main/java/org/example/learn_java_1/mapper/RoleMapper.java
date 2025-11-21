package org.example.learn_java_1.mapper;

import org.example.learn_java_1.entity.Permission;
import org.example.learn_java_1.entity.Role;
import org.example.learn_java_1.request.PermissionRequest;
import org.example.learn_java_1.request.RoleRequest;
import org.example.learn_java_1.response.PermissionResponse;
import org.example.learn_java_1.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface RoleMapper {
    @Mapping(target="permissions", ignore= true)
    Role toRole(RoleRequest request);
//    void updatePermission(@MappingTarget Permission user, PermissionRequest request);
    RoleResponse toRoleResponse(Role role);
}
