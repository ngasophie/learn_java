package org.example.learn_java_1.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserCreationRequest {
    private String username;
    @Size(min = 4, message = "MIN_PASSWORD")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
