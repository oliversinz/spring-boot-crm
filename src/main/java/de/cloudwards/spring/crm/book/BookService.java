package de.cloudwards.spring.crm.book;

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
 * Service implementation for Book class
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    /**
     * create a new Book object
     *
     * @param bookDto
     * @return BookDto
     */
    public BookDto createBook(BookDto bookDto) {

        Book book = mapToEntity(bookDto);

        Book newBook = bookRepository.save(book);

        BookDto bookResponse = mapToDTO(newBook);

        return bookResponse;

    }

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

        List<BookDto> content = listOfBooks.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

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
     * @return List<Book>
     */
    public List<BookDto> getAllBooksList() {

        List<Book> listOfBooks = bookRepository.findAll();

        return listOfBooks.stream().map(element -> mapToDTO(element)).collect(Collectors.toList());

    }

    /**
     * get all Book objects as list, filtered by title
     *
     * @param search
     * @return List<Book>
     */
    public List<BookDto> getBooksListFilteredTitle(String search) {

        List<Book> listOfBooks = bookRepository.findByTitleContaining(search);

        return listOfBooks.stream().map(element -> mapToDTO(element)).collect(Collectors.toList());

    }

    /**
     * get one Book object
     *
     * @param id
     * @return BookDto
     */
    public BookDto getBookById(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        return mapToDTO(book);

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

        return mapToDTO(updatedBook);

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

    /**
     * convert Entity to DTO
     *
     * @param book
     * @return BookDto
     */
    private BookDto mapToDTO(Book book) {
        BookDto bookDto = mapper.map(book, BookDto.class);
        return bookDto;
    }

    /**
     * convert DTO to entity
     *
     * @param bookDto
     * @return Book
     */
    private Book mapToEntity(BookDto bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        return book;
    }

}
