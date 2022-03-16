package de.cloudwards.spring.crm.customer;

import de.cloudwards.spring.crm.utils.AppConstants;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController implementation for Customer class
 */
@RestController
@RequestMapping("/api")
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    /**
     * create a new Customer object
     *
     * @param customerDto
     * @return ResponseEntity<CustomerDto>
     */
    @PostMapping(path = "/customers")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {

        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);

    }

    /**
     * get all Customer objects as CustomerResponse
     *
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return CustomerResponse
     */
    @GetMapping(path = "/customers")
    public CustomerResponse getAllCustomers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return customerService.getAllCustomers(pageNo, pageSize, sortBy, sortDir);

    }

    /**
     * get one Customer object
     *
     * @param id
     * @return ResponseEntity<CustomerDto>
     */
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);

    }

    /**
     * update an existing Customer object
     *
     * @param id
     * @param customerDto
     * @return ResponseEntity<CustomerDto>
     */
    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @PathVariable(name = "id") Long id, @RequestBody CustomerDto customerDto) {

        CustomerDto customerResponse = customerService.updateCustomer(customerDto, id);

        return new ResponseEntity<>(customerResponse, HttpStatus.OK);

    }

    /**
     * delete an existing Customer object
     *
     * @param id
     * @return ResponseEntity<String>
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable(name = "id") Long id) {

        customerService.deleteCustomerById(id);

        return new ResponseEntity<>("Customer entity deleted successfully.", HttpStatus.OK);

    }

}
