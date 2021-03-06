package de.cloudwards.spring.crm.employee;

import de.cloudwards.spring.crm.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Employee class
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * get all Employee objects as EmployeeResponse
     *
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return EmployeeResponse
     */
    public EmployeeResponse getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Employee> employees = employeeRepository.findAll(pageable);

        List<Employee> listOfEmployees = employees.getContent();

        List<EmployeeDto> content = listOfEmployees.stream().map(employee -> EmployeeMapper.INSTANCE.outgoing(employee)).collect(Collectors.toList());

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setContent(content);
        employeeResponse.setPageNo(employees.getNumber());
        employeeResponse.setPageSize(employees.getSize());
        employeeResponse.setTotalElements(employees.getTotalElements());
        employeeResponse.setTotalPages(employees.getTotalPages());
        employeeResponse.setLast(employees.isLast());

        return employeeResponse;

    }

    /**
     * get all Employee objects as list
     *
     * @return List<EmployeeDto>
     */
    public List<EmployeeDto> getAllEmployeesList() {

        List<Employee> listOfEmployees = employeeRepository.findAll();

        return listOfEmployees.stream().map(employee -> EmployeeMapper.INSTANCE.outgoing(employee)).collect(Collectors.toList());

    }

    /**
     * get all Employee objects as list, filtered by lastName
     *
     * @param search
     * @return List<EmployeeDto>
     */
    public List<EmployeeDto> getEmployeesListFilteredLastName(String search) {

        List<Employee> listOfEmployees = employeeRepository.findByLastNameContaining(search);

        return listOfEmployees.stream().map(employee -> EmployeeMapper.INSTANCE.outgoing(employee)).collect(Collectors.toList());

    }

    /**
     * get one Employee object
     *
     * @param id
     * @return EmployeeDto
     */
    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        return EmployeeMapper.INSTANCE.outgoing(employee);

    }

    /**
     * create a new Employee object
     *
     * @param employeeDto
     * @return EmployeeDto
     */
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.INSTANCE.incoming(employeeDto);

        Employee newEmployee = employeeRepository.save(employee);

        EmployeeDto employeeResponse = EmployeeMapper.INSTANCE.outgoing(newEmployee);

        return employeeResponse;

    }

    /**
     * update an existing Employee object
     *
     * @param employeeDto
     * @param id
     * @return EmployeeDto
     */
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.INSTANCE.outgoing(updatedEmployee);

    }

    /**
     * delete an existing Employee object
     *
     * @param id
     */
    public void deleteEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employeeRepository.delete(employee);

    }

}
