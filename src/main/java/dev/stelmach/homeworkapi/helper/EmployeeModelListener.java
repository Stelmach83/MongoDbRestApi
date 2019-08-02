package dev.stelmach.homeworkapi.helper;

import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.service.EmployeeService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class EmployeeModelListener extends AbstractMongoEventListener<Employee> {

    private EmployeeService employeeService;

    public EmployeeModelListener(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Employee> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(employeeService.generateIdSequence(Employee.SEQUENCE_NAME));
        }
    }
}
