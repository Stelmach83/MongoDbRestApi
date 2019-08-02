package dev.stelmach.homeworkapi.controller;

import dev.stelmach.homeworkapi.exception.EmployeeModelException;
import dev.stelmach.homeworkapi.exception.ResourceException;
import dev.stelmach.homeworkapi.helper.EmployeeHelper;
import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.model.dto.EmployeeDTO;
import dev.stelmach.homeworkapi.response.ApiResponse;
import dev.stelmach.homeworkapi.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/${employeeController.version}")
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private EmployeeService employeeService;
    private EmployeeHelper employeeHelper;

    public EmployeeController(EmployeeService employeeService, EmployeeHelper employeeHelper) {
        this.employeeService = employeeService;
        this.employeeHelper = employeeHelper;
    }

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse> saveEmployee(
            @Validated(EmployeeDTO.New.class) @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new EmployeeModelException(bindingResult);
        } else {
            Employee newEmployee = employeeHelper.convertEmployeeDTOtoEmployeeAndGenerateId(employeeDTO);
            employeeService.createEmployee(newEmployee);
            ApiResponse response = new ApiResponse(201, "CREATED", newEmployee);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(
            @PathVariable(name = "id") long id, @Validated(EmployeeDTO.Existing.class) @RequestBody EmployeeDTO employeeDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new EmployeeModelException(bindingResult);
        } else {
            Employee employee = employeeService.findEmployeeById(id)
                    .orElseThrow(() -> new ResourceException(String.format("Could not find Employee by the id: %s", id)));
            employeeHelper.updatedEmployee(employee, employeeDTO);
            employeeService.updateEmployee(employee);
            ApiResponse response = new ApiResponse(200, "UPDATED EMPLOYEE", employee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ApiResponse> getEmployee(@PathVariable(name = "id") long id) {
        Employee employee = employeeService.findEmployeeById(id)
                .orElseThrow(() -> new ResourceException(String.format("Didn't find Employee by the id: %s", id)));
        ApiResponse response = new ApiResponse(200, "LOADED EMPLOYEE", employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<ApiResponse> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(db -> employeeDTOS.add(employeeHelper.convertDbEmployeeToEmployeeDTO(db)));
        ApiResponse response = new ApiResponse(200, "LOADED EMPLOYEES", employeeDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
