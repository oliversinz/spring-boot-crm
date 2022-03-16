package de.cloudwards.spring.crm.order;

/**
 * Jpa repository for OrderItem class
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByBookId(Long bookId);

    List<OrderItem> findByCustomerId(Long customerId);

    List<OrderItem> findByEmployeeId(Long employeeId);

}
