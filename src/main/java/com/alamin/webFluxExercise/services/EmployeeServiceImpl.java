package com.alamin.webFluxExercise.services;

import com.alamin.webFluxExercise.dto.EmployeeDto;
import com.alamin.webFluxExercise.entity.Employee;
import com.alamin.webFluxExercise.exception.UserAlreadyExistsException;
import com.alamin.webFluxExercise.mapper.EmployeeMapper;
import com.alamin.webFluxExercise.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {

        /*Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Mono<Employee> employeeMono = employeeRepository.save((employee));
        return  employeeMono.map(EmployeeMapper::mapToEmployeeDto);*/

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        // Check if the user already exists by email
        Mono<Employee> existingEmployeeMono = employeeRepository.findByEmail(employee.getEmail());

        return existingEmployeeMono.flatMap(existingEmployee -> {
                    // User already exists, throw an exception
                    return Mono.error(new UserAlreadyExistsException("User already exists"));

                }).switchIfEmpty(employeeRepository.save(employee))
                .map(savedEmployee -> EmployeeMapper.mapToEmployeeDto((Employee) savedEmployee));

    }


    @Override
    public Mono<EmployeeDto> getEmployeeById(String empId) {
        Mono<Employee> empById = employeeRepository.findById(empId);

        return empById.map(EmployeeMapper::mapToEmployeeDto);
    }

    @Override
    public Flux<EmployeeDto> getAllEmployee() {
        Flux<Employee> employeeFlux  = employeeRepository.findAll();
        return employeeFlux
                .map(EmployeeMapper::mapToEmployeeDto)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId) {
        Mono<Employee> employeeMono = employeeRepository.findById(employeeId);

        return employeeMono.flatMap((existingEmployee) -> {
            existingEmployee.setName(employeeDto.getName());
            existingEmployee.setEmail(employeeDto.getEmail());
            existingEmployee.setAddress(employeeDto.getAddress());
            return employeeRepository.save(existingEmployee);
        }).map((EmployeeMapper::mapToEmployeeDto));
    }

    @Override
    public Mono<Void> deleteEmployee(String employeeId) {
        return employeeRepository.deleteById(employeeId);
    }
}
