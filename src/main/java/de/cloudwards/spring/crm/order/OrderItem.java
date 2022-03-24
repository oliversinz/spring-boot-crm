package de.cloudwards.spring.crm.order;

import de.cloudwards.spring.crm.book.Book;
import de.cloudwards.spring.crm.customer.Customer;
import de.cloudwards.spring.crm.employee.Employee;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Domain model for OrderItem class
 */
@Entity
@Table(name = "orderitems")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "order_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "order_commission_level", nullable = false)
    private Integer commissionLevel;

    @Column(name = "order_start_date", nullable = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Column(name = "order_end_date", nullable = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Integer commissionLevel, LocalDate startDate, LocalDate endDate, Book book, Customer customer, Employee employee) {
        this.quantity = quantity;
        this.commissionLevel = commissionLevel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.book = book;
        this.customer = customer;
        this.employee = employee;
    }

    public OrderItem(Long id, Integer quantity, Integer commissionLevel, LocalDate startDate, LocalDate endDate, Book book, Customer customer, Employee employee) {
        this.id = id;
        this.quantity = quantity;
        this.commissionLevel = commissionLevel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.book = book;
        this.customer = customer;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCommissionLevel() {
        return commissionLevel;
    }

    public void setCommissionLevel(Integer commissionLevel) {
        this.commissionLevel = commissionLevel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
