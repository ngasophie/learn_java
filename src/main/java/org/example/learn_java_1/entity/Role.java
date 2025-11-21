package org.example.learn_java_1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    private String name;
    private String description;
    @ManyToMany
    Set<Permission> permissions;
}
