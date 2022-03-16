package de.cloudwards.spring.crm.employee;

/**
 * Jpa repository for Employee class
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLastNameContaining(String search);

}
