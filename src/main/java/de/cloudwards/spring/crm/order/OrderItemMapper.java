package de.cloudwards.spring.crm.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for OrderItem class
 */
@Mapper
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDto outgoing(OrderItem orderItem);

    OrderItem incoming(OrderItemDto orderItemDto);

}
