package de.cloudwards.spring.crm.order;

import de.cloudwards.spring.crm.book.BookDto;
import de.cloudwards.spring.crm.customer.CustomerDto;
import de.cloudwards.spring.crm.employee.EmployeeDto;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Data transfer object for OrderItem class
 */
public class OrderItemDto {

    private Long id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer commissionLevel;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private BookDto book;

    private CustomerDto customer;

    private EmployeeDto employee;

    public OrderItemDto() {
    }

    public OrderItemDto(Integer quantity, Integer commissionLevel, LocalDate startDate, LocalDate endDate, BookDto book, CustomerDto customer, EmployeeDto employee) {
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

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

}
