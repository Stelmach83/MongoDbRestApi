package dev.stelmach.homeworkapi.helper;

import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.model.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeHelper {

    public Employee convertEmployeeDTOtoEmployee(EmployeeDTO dto) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(dto.getFirstName());
        newEmployee.setLastName(dto.getLastName());
        newEmployee.setEmail(dto.getEmail());
        newEmployee.setPhoneNumber(dto.getPhoneNumber());
        return newEmployee;
    }
}
