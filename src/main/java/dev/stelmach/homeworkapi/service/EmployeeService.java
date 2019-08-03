package dev.stelmach.homeworkapi.service;

import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.model.dto.EmployeeDTO;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Optional<Employee> findEmployeeById(long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    long generateIdSequence(String seq);
    boolean isEmailUnique(String email);
    boolean validateUpdatedEmployee(Employee employee, EmployeeDTO employeeDTO, BindingResult bindingResult);

}
