package de.cloudwards.spring.crm.book;

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
 * Service implementation for Book class
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * get all Book objects as BookResponse
     *
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return BookResponse
     */
    public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Book> books = bookRepository.findAll(pageable);

        List<Book> listOfBooks = books.getContent();

        List<BookDto> content = listOfBooks.stream().map(book -> BookMapper.INSTANCE.outgoing(book)).collect(Collectors.toList());

        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(content);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setLast(books.isLast());

        return bookResponse;

    }

    /**
     * get all Book objects as list
     *
     * @return List<BookDto>
     */
    public List<BookDto> getAllBooksList() {

        List<Book> listOfBooks = bookRepository.findAll();

        return listOfBooks.stream().map(book -> BookMapper.INSTANCE.outgoing(book)).collect(Collectors.toList());

    }

    /**
     * get all Book objects as list, filtered by title
     *
     * @param search
     * @return List<BookDto>
     */
    public List<BookDto> getBooksListFilteredTitle(String search) {

        List<Book> listOfBooks = bookRepository.findByTitleContaining(search);

        return listOfBooks.stream().map(book -> BookMapper.INSTANCE.outgoing(book)).collect(Collectors.toList());

    }

    /**
     * get one Book object
     *
     * @param id
     * @return BookDto
     */
    public BookDto getBookById(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        return BookMapper.INSTANCE.outgoing(book);

    }

    /**
     * create a new Book object
     *
     * @param bookDto
     * @return BookDto
     */
    public BookDto createBook(BookDto bookDto) {

        Book book = BookMapper.INSTANCE.incoming(bookDto);

        Book newBook = bookRepository.save(book);

        BookDto bookResponse = BookMapper.INSTANCE.outgoing(newBook);

        return bookResponse;

    }

    /**
     * update an existing Book object
     *
     * @param bookDto
     * @param id
     * @return BookDto
     */
    public BookDto updateBook(BookDto bookDto, Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPubYear(bookDto.getPubYear());
        book.setPrice(bookDto.getPrice());

        Book updatedBook = bookRepository.save(book);

        return BookMapper.INSTANCE.outgoing(updatedBook);

    }

    /**
     * delete an existing Book object
     *
     * @param id
     */
    public void deleteBookById(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        bookRepository.delete(book);

    }

}
