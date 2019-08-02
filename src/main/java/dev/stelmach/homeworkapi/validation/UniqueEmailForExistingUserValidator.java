package dev.stelmach.homeworkapi.validation;

import dev.stelmach.homeworkapi.service.EmployeeService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailForExistingUserValidator implements ConstraintValidator<UniqueEmailForExistingUser, String> {

    private EmployeeService employeeService;

    private long EmployeeId;

    public UniqueEmailForExistingUserValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void initialize(UniqueEmailForExistingUser constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return employeeService.isEmailUnique(email) || false;
    }
}
