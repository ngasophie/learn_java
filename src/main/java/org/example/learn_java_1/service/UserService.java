package org.example.learn_java_1.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.learn_java_1.entity.User;
import org.example.learn_java_1.enums.Role;
import org.example.learn_java_1.exception.AppException;
import org.example.learn_java_1.exception.ErrorCode;
import org.example.learn_java_1.mapper.UserMapper;
import org.example.learn_java_1.repository.UserRepository;
import org.example.learn_java_1.request.UserCreationRequest;
import org.example.learn_java_1.request.UserUpdateRequest;
import org.example.learn_java_1.response.UserResponse;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);
        return this.userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')") // check quyen trc khi method goi

    public List<UserResponse> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username" + authentication.getName());
        log.info("role" + authentication.getAuthorities().stream().findFirst());
        return this.userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }
    @PostAuthorize("returnObject.username == authentication.name") // check quyeenf sau khi method chay

    public User getUserById(String id) {
        return this.userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = this.getUserById(userId);
        userMapper.updateUser(user, request);
        return this.userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = this.getUserById(userId);
        this.userRepository.delete(user);
    }

    public UserResponse getProfile() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = this.userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
}
