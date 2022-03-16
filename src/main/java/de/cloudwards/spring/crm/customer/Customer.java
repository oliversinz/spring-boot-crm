package de.cloudwards.spring.crm.customer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.cloudwards.spring.crm.order.OrderItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Domain model for Customer class
 */
@Entity
@Table(name = "customers")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long id;

    @Column(name = "customer_company_name", nullable = false)
    private String companyName;

    @Column(name = "customer_first_name", nullable = true)
    private String firstName;

    @Column(name = "customer_last_name", nullable = true)
    private String lastName;

    @Column(name = "customer_salutation", nullable = true)
    private String salutation;

    @Column(name = "customer_street_address", nullable = false)
    private String streetAddress;

    @Column(name = "customer_zip_code", nullable = false)
    private String zipCode;

    @Column(name = "customer_city", nullable = false)
    private String city;

    @Column(name = "customer_country", nullable = false)
    private String country;

    @Column(name = "customer_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "customer_email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "customer-order")
    private List<OrderItem> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String companyName, String firstName, String lastName, String salutation, String streetAddress, String zipCode, String city, String country, String phoneNumber, String email) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salutation = salutation;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer(Long id, String companyName, String firstName, String lastName, String salutation, String streetAddress, String zipCode, String city, String country, String phoneNumber, String email) {
        this.id = id;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salutation = salutation;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (!this.orders.contains(orderItem)) {
            this.orders.add(orderItem);
            orderItem.setCustomer(this);
        }
    }

}
