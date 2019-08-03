package dev.stelmach.homeworkapi.repository;

import dev.stelmach.homeworkapi.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

    long countEmployeesByEmail(String email);
    List<Employee> getAllByIdGreaterThan(long id);

}
