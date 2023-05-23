package com.alamin.webFluxExercise.mapper;

import com.alamin.webFluxExercise.dto.EmployeeDto;
import com.alamin.webFluxExercise.entity.Employee;

public class EmployeeMapper {
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getEmail(),
                employeeDto.getAddress());
    }
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getAddress());
    }
}
