package com.alamin.webFluxExercise.services;



import com.alamin.webFluxExercise.dto.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    public Mono<EmployeeDto>  saveEmployee(EmployeeDto employeeDto);

    public Mono<EmployeeDto> getEmployeeById(String empId);
    Flux<EmployeeDto> getAllEmployee();

    Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId);

    Mono<Void> deleteEmployee(String employeeId);

}

