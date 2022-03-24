package de.cloudwards.spring.crm.book;

import de.cloudwards.spring.crm.utils.AppConstants;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * RestController implementation for Book class
 */
@RestController
@RequestMapping("/api")
public class BookApiController {

    @Autowired
    private BookService bookService;

    /**
     * get all Book objects as BookResponse
     *
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return BookResponse
     */
    @GetMapping(path = "/books")
    public BookResponse getAllBooks(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return bookService.getAllBooks(pageNo, pageSize, sortBy, sortDir);

    }

    /**
     * get one Book object
     *
     * @param id
     * @return ResponseEntity<BookDto>
     */
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);

    }

    /**
     * create a new Book object
     *
     * @param bookDto
     * @return ResponseEntity<BookDto>
     */
    @PostMapping(path = "/books")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {

        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);

    }

    /**
     * update an existing Book object
     *
     * @param bookDto
     * @param id
     * @return ResponseEntity<BookDto>
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable(name = "id") Long id) {

        BookDto bookResponse = bookService.updateBook(bookDto, id);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);

    }

    /**
     * delete an existing Book object
     *
     * @param id
     * @return
     */
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable(name = "id") Long id) {

        bookService.deleteBookById(id);

        return new ResponseEntity<>("Book entity deleted successfully.", HttpStatus.OK);

    }

}
