package de.cloudwards.spring.crm;

import de.cloudwards.spring.crm.book.Book;
import de.cloudwards.spring.crm.book.BookRepository;
import de.cloudwards.spring.crm.customer.Customer;
import de.cloudwards.spring.crm.customer.CustomerRepository;
import de.cloudwards.spring.crm.employee.Employee;
import de.cloudwards.spring.crm.employee.EmployeeRepository;
import de.cloudwards.spring.crm.order.OrderItem;
import de.cloudwards.spring.crm.order.OrderItemRepository;
import de.cloudwards.spring.crm.role.Role;
import de.cloudwards.spring.crm.role.RoleRepository;
import de.cloudwards.spring.crm.user.User;
import de.cloudwards.spring.crm.user.UserRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Role role1 = new Role("ROLE_ADMIN");
        roleRepository.save(role1);

        Role role2 = new Role("ROLE_USER");
        roleRepository.save(role2);

        User user1 = new User("Roland", "Dreyer", "admin", "admin@reise-idee.de", "$2a$10$xNUtlI7iUX49XFmM7vSfyujf5jtMGzkjll4krbcGKZxDWmB0vmb4y");
        userRepository.save(user1);
        user1.getRoles().add(role1);
        userRepository.save(user1);

        User user2 = new User("Franz", "Bauer", "user", "user@reise-idee.de", "$2a$10$xNUtlI7iUX49XFmM7vSfyujf5jtMGzkjll4krbcGKZxDWmB0vmb4y");
        userRepository.save(user2);
        user2.getRoles().add(role2);
        userRepository.save(user2);

        Book book1 = new Book("Typisch Allgäu", "978-3-934739-58-1", 2019, 9.80);
        bookRepository.save(book1);

        Book book2 = new Book("Allgäu sportlich und aktiv", "978-3-934739-62-8", 2020, 14.95);
        bookRepository.save(book2);

        Customer customer1 = new Customer();
        customer1.setCompanyName("Goldene Traube");
        customer1.setFirstName("Michael");
        customer1.setLastName("Stoerch");
        customer1.setSalutation("Herr");
        customer1.setStreetAddress("Memminger Str. 7");
        customer1.setZipCode("87439");
        customer1.setCity("Kempten");
        customer1.setCountry("Deutschland");
        customer1.setPhoneNumber("0831 22187");
        customer1.setEmail("info@traube-kempten.de");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setCompanyName("Hotel Adler");
        customer2.setFirstName("Armin");
        customer2.setLastName("Hollweck");
        customer2.setSalutation("Herr");
        customer2.setStreetAddress("Kirchplatz 6");
        customer2.setZipCode("87534");
        customer2.setCity("Oberstaufen");
        customer2.setCountry("Deutschland");
        customer2.setPhoneNumber("08386 93210");
        customer2.setEmail("info@adler-oberstaufen.de");
        customerRepository.save(customer2);

        Employee employee1 = new Employee("Roland", "Dreyer", "rolanddreyer@gmx.de");
        employeeRepository.save(employee1);

        Employee employee2 = new Employee("Franz", "Bauer", "franzbauer@gmx.de");
        employeeRepository.save(employee2);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setBook(book1);
        orderItem1.setQuantity(10);
        orderItem1.setCommissionLevel(0.2);
        orderItem1.setCustomer(customer1);
        orderItem1.setEmployee(employee1);
        orderItem1.setStartDate(LocalDate.now());
        orderItem1.setEndDate(LocalDate.of(2022, Month.JUNE, 30));
        orderItemRepository.save(orderItem1);

    }

}
