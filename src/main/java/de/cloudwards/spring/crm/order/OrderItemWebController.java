package de.cloudwards.spring.crm.order;

import de.cloudwards.spring.crm.book.Book;
import de.cloudwards.spring.crm.book.BookService;
import de.cloudwards.spring.crm.customer.Customer;
import de.cloudwards.spring.crm.customer.CustomerService;
import de.cloudwards.spring.crm.employee.Employee;
import de.cloudwards.spring.crm.employee.EmployeeService;
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
 * Controller implementation for OrderItem class
 */
@Controller
public class OrderItemWebController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * create a new orderItem object, show form
     *
     * @param model
     * @return String
     */
    @GetMapping("/new-orderitem")
    public String getCreateOrderItemForm(Model model) {
        model.addAttribute("orderitem", new OrderItemDto());
        model.addAttribute("books", bookService.getAllBooksList());
        model.addAttribute("customers", customerService.getAllCustomersList());
        model.addAttribute("employees", employeeService.getAllEmployeesList());
        return "order-form-new";
    }

    /**
     * create a new orderItem object, save orderItem
     *
     * @param orderItemDto
     * @param result
     * @param book
     * @param customer
     * @param employee
     * @param model
     * @return
     */
    @PostMapping("/new-orderitem")
    public String createOrderItem(@Valid @ModelAttribute("orderitem") OrderItemDto orderItemDto, BindingResult result,
            @RequestParam("book.id") Book book, @RequestParam("customer.id") Customer customer,
            @RequestParam("employee.id") Employee employee, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderitem", orderItemDto);
            model.addAttribute("books", bookService.getAllBooksList());
            model.addAttribute("customers", customerService.getAllCustomersList());
            model.addAttribute("employees", employeeService.getAllEmployeesList());
            return "order-form-new";
        }
        orderItemService.createOrderItemByObject(book, customer, employee, orderItemDto);
        return "redirect:/orderitems";
    }

    /**
     * create a new orderItem object based on customer, show form
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/customers/new-order/{id}")
    public String getCreateOrderItemCustomerForm(@PathVariable Long id, Model model) {
        model.addAttribute("orderitem", new OrderItemDto());
        model.addAttribute("books", bookService.getAllBooksList());
        model.addAttribute("customer", customerService.getCustomerById(id));
        model.addAttribute("employees", employeeService.getAllEmployeesList());
        return "order-form-new-customer";
    }

    /**
     * create a new orderItem object based on customer, save orderitem
     *
     * @param id
     * @param orderItemDto
     * @param result
     * @param book
     * @param employee
     * @param model
     * @return String
     */
    @PostMapping("/customers/new-order/{id}")
    public String createOrderItemCustomer(@PathVariable Long id,
            @Valid @ModelAttribute("orderitem") OrderItemDto orderItemDto, BindingResult result,
            @RequestParam("book.id") Book book, @RequestParam("employee.id") Employee employee, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderitem", orderItemDto);
            model.addAttribute("books", bookService.getAllBooksList());
            model.addAttribute("customer", customerService.getCustomerById(id));
            model.addAttribute("employees", employeeService.getAllEmployeesList());
            return "order-form-new-customer";
        }
        orderItemService.createOrderItem(book.getId(), id, employee.getId(), orderItemDto);
        return "redirect:/orderitems";
    }

    /**
     * get all orderItem objects as list
     *
     * @param model
     * @return
     */
    @GetMapping("/orderitems")
    public String getAllOrderItemsList(Model model) {
        model.addAttribute("orderitems", orderItemService.getAllOrderItemsList());
        return "order-list-view";
    }

    /**
     * update an existing orderItem object, show form
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/orderitems/edit/{id}")
    public String getUpdateOrderItemForm(@PathVariable Long id, Model model) {
        model.addAttribute("orderitem", orderItemService.getOrderItemById(id));
        model.addAttribute("books", bookService.getAllBooksList());
        model.addAttribute("customers", customerService.getAllCustomersList());
        model.addAttribute("employees", employeeService.getAllEmployeesList());
        return "order-form-update";
    }

    /**
     * update an existing orderItem object, save orderitem
     *
     * @param id
     * @param orderItemDto
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/orderitems/edit/{id}")
    public String updateOrderItem(@PathVariable Long id, @Valid @ModelAttribute("orderitem") OrderItemDto orderItemDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderitem", orderItemDto);
            model.addAttribute("books", bookService.getAllBooksList());
            model.addAttribute("customers", customerService.getAllCustomersList());
            model.addAttribute("employees", employeeService.getAllEmployeesList());
            return "order-form-update";
        }
        orderItemService.updateOrderItemById(id, orderItemDto);
        return "redirect:/orderitems";
    }

    /**
     * delete an existing orderItem object
     *
     * @param id
     * @return
     */
    @GetMapping("/orderitems/delete/{id}")
    public String deleteOrderItemById(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
        return "redirect:/orderitems";
    }

}
