package de.cloudwards.spring.crm.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data transfer object for Customer class
 */
public class CustomerDto {

    private Long id;

    @NotEmpty
    @Size(min = 5, message = "mindestens 5 Zeichen")
    private String companyName;

    private String firstName;

    private String lastName;

    private String salutation;

    @NotEmpty
    @Size(min = 5, message = "mindestens 5 Zeichen")
    private String streetAddress;

    @NotNull
    private String zipCode;

    @NotEmpty
    @Size(min = 5, message = "mindestens 5 Zeichen")
    private String city;

    @NotEmpty
    @Size(min = 5, message = "mindestens 5 Zeichen")
    private String country;

    @NotEmpty
    @Size(min = 5, message = "mindestens 5 Zeichen")
    private String phoneNumber;

    @NotEmpty
    @Email
    private String email;

    public CustomerDto() {
    }

    public CustomerDto(String companyName, String firstName, String lastName, String salutation, String streetAddress, String zipCode, String city, String country, String phoneNumber, String email) {
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

    @Override
    public String toString() {
        return companyName;
    }

}
