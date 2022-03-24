package de.cloudwards.spring.crm.book;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for Book class
 */
@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto outgoing(Book book);

    Book incoming(BookDto bookDto);

}
