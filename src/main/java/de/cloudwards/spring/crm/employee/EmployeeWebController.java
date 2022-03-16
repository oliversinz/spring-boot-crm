package de.cloudwards.spring.crm.employee;

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
 * Controller implementation for Employee class
 */
@Controller
public class EmployeeWebController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * create a new Employee object, show form
     *
     * @param model
     * @return String
     */
    @GetMapping("/new-employee")
    public String getCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        return "employee-form-new";
    }

    /**
     * create a new Employee object, save employee
     *
     * @param employeeDto
     * @param result
     * @return String
     */
    @PostMapping("/new-employee")
    public String createEmployee(@Valid @ModelAttribute("employee") EmployeeDto employeeDto, BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form-new";
        }
        employeeService.createEmployee(employeeDto);
        return "redirect:/employees";
    }

    /**
     * get all Employee objects as list
     *
     * @param model
     * @return String
     */
    @GetMapping("/employees")
    public String getAllEmployeesList(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployeesList());
        return "employee-list-view";
    }

    /**
     * get all Employee objects as list, filtered by lastName
     *
     * @param lastName
     * @param model
     * @return String
     */
    @PostMapping("/employees/search")
    public String getEmployeesListFilteredLastName(@RequestParam("lastName") String lastName, Model model) {
        model.addAttribute("employees", employeeService.getEmployeesListFilteredLastName(lastName));
        return "employee-list-view";
    }

    /**
     * update an existing Employee object, show form
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/employees/edit/{id}")
    public String getUpdateEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employee-form-update";
    }

    /**
     * update an existing Employee object, save employee
     *
     * @param id
     * @param employeeDto
     * @param result
     * @return
     */
    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @Valid @ModelAttribute("employee") EmployeeDto employeeDto, BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form-update";
        }
        employeeService.updateEmployee(employeeDto, id);
        return "redirect:/employees";
    }

    /**
     * delete an existing Employee object
     *
     * @param id
     * @return
     */
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

}
