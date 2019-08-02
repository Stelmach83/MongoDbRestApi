package dev.stelmach.homeworkapi.service;

import dev.stelmach.homeworkapi.exception.ResourceException;
import dev.stelmach.homeworkapi.model.Employee;
import dev.stelmach.homeworkapi.model.EmployeeSequence;
import dev.stelmach.homeworkapi.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private EmployeeRepository employeeRepository;
    private MongoOperations mongoOperations;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MongoOperations mongoOperations) {
        this.employeeRepository = employeeRepository;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(long id) {
        return employeeRepository.findById(id);
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

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        if (null != updatedEmployee) {
            if (log.isInfoEnabled()) {
                log.info(String.format("Updated Employee: %n%s", updatedEmployee.toString()));
            }
        } else {
            String msg = String.format("Error updating Employee: %n%s", employee.toString());
            log.error(msg);
            throw new ResourceException(msg);
        }
        return updatedEmployee;
    }

    @Override
    public long generateIdSequence(String seq) {
        EmployeeSequence counter = mongoOperations.findAndModify(query(where("_id").is(seq)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                EmployeeSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    @Override
    public boolean isEmailUnique(String email) {
        return employeeRepository.countEmployeesByEmail(email) <= 0;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.getDistinctByEmail(email);
    }

    @Override
    public boolean hasEmailChangedForEmployee(Employee employee, String email) {
        return false;
    }

    @Override
    public boolean isEmployeesEmailCurrent(long id, String email) {
        Employee employee = findEmployeeById(id)
                .orElseThrow(() -> new ResourceException(String.format("Could not find Employee by the id: %s", id)));
        String currentEmail = employee.getEmail();
        return email.equals(currentEmail);
    }
}
