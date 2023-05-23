package com.alamin.webFluxExercise.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
}
