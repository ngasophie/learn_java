package org.example.learn_java_1.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD}) // validate ở đâu
@Retention(RetentionPolicy.RUNTIME) // chạy lúc nào
@Constraint(
        validatedBy = { DobValidator.class }
)
public @interface DobConstraint {
    String message() default "Invalid date of birth";
    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
