package de.cloudwards.spring.crm.customer;

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
 * Controller implementation for Customer class
 */
@Controller
public class CustomerWebController {

    @Autowired
    private CustomerService customerService;

    /**
     * get all Customer objects as list
     *
     * @param model
     * @return String
     */
    @GetMapping("/customers")
    public String getAllCustomersList(Model model) {
        model.addAttribute("customers", customerService.getAllCustomersList());
        return "customer-list-view";
    }

    /**
     * get all Customer objects as list, filtered by companyName
     *
     * @param zipCode
     * @param model
     * @return String
     */
    @PostMapping("/customers/search")
    public String getCustomersListFilteredZipCode(@RequestParam("zipCode") String zipCode, Model model) {
        model.addAttribute("customers", customerService.getCustomersListFilteredZipCode(zipCode));
        return "customer-list-view";
    }

    /**
     * create a new Customer object, show form
     *
     * @param model
     * @return String
     */
    @GetMapping("/new-customer")
    public String getCreateCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "customer-form-new";
    }

    /**
     * create a new Customer object, save customer
     *
     * @param customerDto
     * @param result
     * @return String
     */
    @PostMapping("/new-customer")
    public String createCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            return "customer-form-new";
        }
        customerService.createCustomer(customerDto);
        return "redirect:/customers";
    }

    /**
     * update an existing Customer object, show form
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/customers/edit/{id}")
    public String getUpdateCustomerForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer-form-update";
    }

    /**
     * update an existing Customer object, save customer
     *
     * @param id
     * @param customerDto
     * @param result
     * @return String
     */
    @PostMapping("/customers/edit/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            return "customer-form-update";
        }
        customerService.updateCustomer(customerDto, id);
        return "redirect:/customers";
    }

    /**
     * delete an existing Customer object
     *
     * @param id
     * @return
     */
    @GetMapping("/customers/delete/{id}")
    public String deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }

}
