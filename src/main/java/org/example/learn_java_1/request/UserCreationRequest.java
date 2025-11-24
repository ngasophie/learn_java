package org.example.learn_java_1.request;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.learn_java_1.Validator.DobConstraint;

import java.time.LocalDate;
import java.util.List;

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
    @DobConstraint(min = 2, message = "INVALID_DOB")
    private LocalDate dob;
    private List<String> roleIds;
}
