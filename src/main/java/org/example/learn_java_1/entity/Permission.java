package org.example.learn_java_1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    private String name;
    private String description;
}
