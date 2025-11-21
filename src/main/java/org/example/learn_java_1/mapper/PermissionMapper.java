package org.example.learn_java_1.mapper;

import org.example.learn_java_1.entity.Permission;
import org.example.learn_java_1.request.PermissionRequest;
import org.example.learn_java_1.response.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
//    void updatePermission(@MappingTarget Permission user, PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
