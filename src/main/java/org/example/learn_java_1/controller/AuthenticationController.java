package org.example.learn_java_1.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.learn_java_1.request.AuthenticationRequest;
import org.example.learn_java_1.response.ApiResponse;
import org.example.learn_java_1.response.AuthenticationResponse;
import org.example.learn_java_1.service.AuthenticationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService service;
    @RequestMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        response.setResult(service.authenticate(request));
        return response;
    }
}
