package dev.stelmach.homeworkapi.repository;

import dev.stelmach.homeworkapi.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

    long countEmployeesByEmail(String email);
    Employee getDistinctByEmail(String email);
    @Query("COUNT(emp) Employee emp WHERE")
    long countEmployeesByIdAndAndEmail(long id, String email);

}
