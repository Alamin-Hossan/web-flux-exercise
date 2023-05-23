package com.alamin.webFluxExercise.repository;

import com.alamin.webFluxExercise.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String> {

    Mono<Employee> findByEmail(String email);
}
