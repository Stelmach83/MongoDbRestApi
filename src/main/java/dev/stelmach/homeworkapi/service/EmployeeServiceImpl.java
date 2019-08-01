package dev.stelmach.homeworkapi.service;

import dev.stelmach.homeworkapi.exception.ResourceException;
import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceException(String.format("Didn't find Employee by the id: %s", id)));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        if (null != newEmployee) {
            if (log.isInfoEnabled()) {
                log.info(String.format("Created Employee: %n%s", newEmployee.toString()));
            }
        } else {
            String msg = String.format("Error creating Employee: %n%s", employee.toString());
            log.error(msg);
            throw new ResourceException(msg);
        }
        return newEmployee;
    }
}
