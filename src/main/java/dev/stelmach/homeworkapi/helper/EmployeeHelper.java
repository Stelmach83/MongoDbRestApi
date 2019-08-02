package dev.stelmach.homeworkapi.helper;

import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.model.dto.EmployeeDTO;
import dev.stelmach.homeworkapi.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component
public class EmployeeHelper {

    private EmployeeService employeeService;

    public EmployeeHelper(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee convertEmployeeDTOtoEmployeeAndGenerateId(EmployeeDTO dto) {
        Employee newEmployee = new Employee();
        newEmployee.setId(employeeService.generateIdSequence(Employee.SEQUENCE_NAME));
        newEmployee.setFirstName(dto.getFirstName());
        newEmployee.setLastName(dto.getLastName());
        newEmployee.setEmail(dto.getEmail());
        newEmployee.setPhoneNumber(dto.getPhoneNumber());
        return newEmployee;
    }

    public EmployeeDTO convertDbEmployeeToEmployeeDTO(Employee db) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(db.getId());
        dto.setFirstName(db.getFirstName());
        dto.setLastName(db.getLastName());
        dto.setEmail(db.getEmail());
        dto.setPhoneNumber(db.getPhoneNumber());
        return dto;
    }

    public void updatedEmployee(Employee db, EmployeeDTO dto) {
        db.setFirstName(dto.getFirstName());
        db.setLastName(dto.getLastName());
        db.setEmail(dto.getEmail());
        db.setPhoneNumber(dto.getPhoneNumber());
    }

}
