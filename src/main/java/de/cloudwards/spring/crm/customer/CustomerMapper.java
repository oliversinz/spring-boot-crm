package de.cloudwards.spring.crm.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for Customer class
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto outgoing(Customer customer);

    Customer incoming(CustomerDto customerDto);

}
