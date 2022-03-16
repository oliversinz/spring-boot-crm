package de.cloudwards.spring.crm.customer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa repository for Customer class
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByZipCodeContaining(String search);

}
