package com.alamin.webFluxExercise.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private String id;
    private String name;
    private String email;
    private String address;
}
