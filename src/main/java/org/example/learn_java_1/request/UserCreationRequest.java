package org.example.learn_java_1.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    private String username;
    @Size(min = 4, message = "MIN_PASSWORD")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
