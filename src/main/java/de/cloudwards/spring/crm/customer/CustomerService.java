package de.cloudwards.spring.crm.customer;

import de.cloudwards.spring.crm.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Customer class
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    /**
     * create a new Customer object
     *
     * @param customerDto
     * @return CustomerDto
     */
    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer = mapToEntity(customerDto);

        Customer newCustomer = customerRepository.save(customer);

        CustomerDto customerResponse = mapToDTO(newCustomer);

        return customerResponse;

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
    public CustomerResponse getAllCustomers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Customer> customers = customerRepository.findAll(pageable);

        List<Customer> listOfCustomers = customers.getContent();

        List<CustomerDto> content = listOfCustomers.stream().map(customer -> mapToDTO(customer)).collect(Collectors.toList());

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setContent(content);
        customerResponse.setPageNo(customers.getNumber());
        customerResponse.setPageSize(customers.getSize());
        customerResponse.setTotalElements(customers.getTotalElements());
        customerResponse.setTotalPages(customers.getTotalPages());
        customerResponse.setLast(customers.isLast());

        return customerResponse;

    }

    /**
     * get all Customer objects as list
     *
     * @return List<Customer>
     */
    public List<CustomerDto> getAllCustomersList() {

        List<Customer> listOfCustomers = customerRepository.findAll();

        return listOfCustomers.stream().map(element -> mapToDTO(element)).collect(Collectors.toList());

    }

    /**
     * get all Customer objects as list, filtered by zipCode
     *
     * @param search
     * @return List<Customer>
     */
    public List<CustomerDto> getCustomersListFilteredZipCode(String search) {

        List<Customer> listOfCustomers = customerRepository.findByZipCodeContaining(search);

        return listOfCustomers.stream().map(element -> mapToDTO(element)).collect(Collectors.toList());

    }

    /**
     * get one Customer object
     *
     * @param id
     * @return CustomerDto
     */
    public CustomerDto getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        return mapToDTO(customer);

    }

    /**
     * update an existing Customer object
     *
     * @param customerDto
     * @param id
     * @return CustomerDto
     */
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customer.setCompanyName(customerDto.getCompanyName());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setSalutation(customerDto.getSalutation());
        customer.setStreetAddress(customerDto.getStreetAddress());
        customer.setZipCode(customerDto.getZipCode());
        customer.setCity(customerDto.getCity());
        customer.setCountry(customerDto.getCountry());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getEmail());

        Customer updatedCustomer = customerRepository.save(customer);

        return mapToDTO(updatedCustomer);

    }

    /**
     * delete an existing Customer object
     *
     * @param id
     */
    public void deleteCustomerById(Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.delete(customer);

    }

    /**
     * convert Entity to DTO
     *
     * @param customer
     * @return CustomerDto
     */
    private CustomerDto mapToDTO(Customer customer) {
        CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    /**
     * convert DTO to entity
     *
     * @param customerDto
     * @return Customer
     */
    private Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);
        return customer;
    }

}
