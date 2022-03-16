package de.cloudwards.spring.crm.employee;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Data transfer object for Employee class
 */
public class EmployeeDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "mindestens 2 Zeichen")
    private String firstName;

    @NotEmpty
    @Size(min = 2, message = "mindestens 2 Zeichen")
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
