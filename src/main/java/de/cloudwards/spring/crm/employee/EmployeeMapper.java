package de.cloudwards.spring.crm.employee;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for Employee class
 */
@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto outgoing(Employee employee);

    Employee incoming(EmployeeDto employeeDto);

}
