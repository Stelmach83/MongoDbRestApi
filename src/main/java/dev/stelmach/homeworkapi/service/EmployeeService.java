package dev.stelmach.homeworkapi.service;

import dev.stelmach.homeworkapi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Optional<Employee> findEmployeeById(long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    long generateIdSequence(String seq);
    boolean isEmailUnique(String email);
    boolean hasEmailChangedForEmployee(Employee employee, String email);
    Employee getEmployeeByEmail(String email);
    boolean isEmployeesEmailCurrent(long id, String email);

}
