package org.example.learn_java_1.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.learn_java_1.entity.User;
import org.example.learn_java_1.exception.AppException;
import org.example.learn_java_1.exception.ErrorCode;
import org.example.learn_java_1.mapper.UserMapper;
import org.example.learn_java_1.repository.UserRepository;
import org.example.learn_java_1.request.UserCreationRequest;
import org.example.learn_java_1.request.UserUpdateRequest;
import org.example.learn_java_1.response.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User existed");
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return this.userRepository.save(user);
    }

    public List<UserResponse> getUsers() {
        return this.userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

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
}
