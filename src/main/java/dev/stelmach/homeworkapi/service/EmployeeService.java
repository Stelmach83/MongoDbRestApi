package dev.stelmach.homeworkapi.service;

import dev.stelmach.homeworkapi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Employee findEmployeeById(int id);
    Employee createEmployee(Employee employee);

}
