package org.example.learn_java_1.controller;

import org.example.learn_java_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;
//    @GetMapping("/hello")
//    String sayHello() {
//        return this.userService.createUser("1212").getFirstName();
//    }
}
