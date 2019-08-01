package dev.stelmach.homeworkapi.controller;

import dev.stelmach.homeworkapi.helper.EmployeeHelper;
import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.model.dto.EmployeeDTO;
import dev.stelmach.homeworkapi.response.ApiResponse;
import dev.stelmach.homeworkapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/${employeeApi.version}")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeHelper employeeHelper;

    public EmployeeController(EmployeeService employeeService, EmployeeHelper employeeHelper) {
        this.employeeService = employeeService;
        this.employeeHelper = employeeHelper;
    }

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeHelper.convertEmployeeDTOtoEmployee(employeeDTO);
        employeeService.createEmployee(newEmployee);
        ApiResponse response = new ApiResponse(200, "OK", newEmployee);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ApiResponse> getEmployee(@PathVariable(name = "id") int id) {
        Employee employee = employeeService.findEmployeeById(id);
        ApiResponse response = new ApiResponse(200, "OK", employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
