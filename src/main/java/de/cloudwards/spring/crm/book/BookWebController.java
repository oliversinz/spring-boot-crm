package de.cloudwards.spring.crm.book;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller implementation for Book class
 */
@Controller
public class BookWebController {

    @Autowired
    private BookService bookService;

    /**
     * get all Book objects as list
     *
     * @param model
     * @return String
     */
    @GetMapping("/books")
    public String getAllBooksList(Model model) {
        model.addAttribute("books", bookService.getAllBooksList());
        return "book-list-view";
    }

    /**
     * get all Book objects as list, filtered by title
     *
     * @param title
     * @param model
     * @return String
     */
    @PostMapping("/books/search")
    public String getBooksListFilteredTitle(@RequestParam("title") String title, Model model) {
        model.addAttribute("books", bookService.getBooksListFilteredTitle(title));
        return "book-list-view";
    }

    /**
     * create a new Book object, show form
     *
     * @param model
     * @return String
     */
    @GetMapping("/new-book")
    public String getCreateBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "book-form-new";
    }

    /**
     * create a new Book object, save book
     *
     * @param bookDto
     * @param result
     * @return
     */
    @PostMapping("/new-book")
    public String createBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form-new";
        }
        bookService.createBook(bookDto);
        return "redirect:/books";
    }

    /**
     * update an existing Book object, show form
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/books/edit/{id}")
    public String getUpdateBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book-form-update";
    }

    /**
     * update an existing Book object, save book
     *
     * @param id
     * @param bookDto
     * @param result
     * @return
     */
    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("book") BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form-update";
        }
        bookService.updateBook(bookDto, id);
        return "redirect:/books";
    }

    /**
     * delete an existing Book object
     *
     * @param id
     * @return
     */
    @GetMapping("/books/delete/{id}")
    public String deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

}
