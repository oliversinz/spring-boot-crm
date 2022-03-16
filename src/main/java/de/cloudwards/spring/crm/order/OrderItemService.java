package de.cloudwards.spring.crm.order;

import de.cloudwards.spring.crm.book.Book;
import de.cloudwards.spring.crm.book.BookRepository;
import de.cloudwards.spring.crm.customer.Customer;
import de.cloudwards.spring.crm.customer.CustomerRepository;
import de.cloudwards.spring.crm.employee.Employee;
import de.cloudwards.spring.crm.employee.EmployeeRepository;
import de.cloudwards.spring.crm.exception.APIException;
import de.cloudwards.spring.crm.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Service implementation for OrderItem class
 */
@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;

    /**
     * create a new orderItem object specifying bookId, customerId, employeeId
     *
     * @param bookId
     * @param customerId
     * @param employeeId
     * @param orderItemDto
     * @return OrderItemDto
     */
    public OrderItemDto createOrderItem(Long bookId, Long customerId, Long employeeId, OrderItemDto orderItemDto) {

        OrderItem orderItem = mapToEntity(orderItemDto);

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", bookId));

        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", customerId));

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId));

        orderItem.setBook(book);
        orderItem.setCustomer(customer);
        orderItem.setEmployee(employee);

        OrderItem newOrderItem = orderItemRepository.save(orderItem);

        return mapToDTO(newOrderItem);

    }

    /**
     * create a new orderItem object specifying book, customer and employee
     *
     * @param book
     * @param customer
     * @param employee
     * @param orderItemDto
     * @return OrderItemDto
     */
    public OrderItemDto createOrderItemByObject(Book book, Customer customer, Employee employee, OrderItemDto orderItemDto) {

        OrderItem orderItem = mapToEntity(orderItemDto);

        orderItem.setBook(book);
        orderItem.setCustomer(customer);
        orderItem.setEmployee(employee);

        OrderItem newOrderItem = orderItemRepository.save(orderItem);

        return mapToDTO(newOrderItem);

    }

    /**
     * get all orderItem objects as OrderItemResponse
     *
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return OrderItemResponse
     */
    public OrderItemResponse getAllOrderItems(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<OrderItem> orderItems = orderItemRepository.findAll(pageable);

        List<OrderItem> listOfOrderItems = orderItems.getContent();

        List<OrderItemDto> content = listOfOrderItems.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setContent(content);
        orderItemResponse.setPageNo(orderItems.getNumber());
        orderItemResponse.setPageSize(orderItems.getSize());
        orderItemResponse.setTotalElements(orderItems.getTotalElements());
        orderItemResponse.setTotalPages(orderItems.getTotalPages());
        orderItemResponse.setLast(orderItems.isLast());

        return orderItemResponse;

    }

    /**
     * get all orderItem objects as list
     *
     * @return List<OrderItemDto>
     */
    public List<OrderItemDto> getAllOrderItemsList() {

        List<OrderItem> orderItems = orderItemRepository.findAll();

        return orderItems.stream().map(orderItem -> mapToDTO(orderItem)).collect(Collectors.toList());

    }

    /**
     * get all orderItem objects as list, filtered by bookId
     *
     * @param bookId
     * @return
     */
    public List<OrderItemDto> getOrderItemsByBookId(Long bookId) {

        List<OrderItem> orderItems = orderItemRepository.findByBookId(bookId);

        return orderItems.stream().map(orderItem -> mapToDTO(orderItem)).collect(Collectors.toList());

    }

    /**
     * get all orderItem objects as list, filtered by customerId
     *
     * @param customerId
     * @return
     */
    public List<OrderItemDto> getOrderItemsByCustomerId(Long customerId) {

        List<OrderItem> orderItems = orderItemRepository.findByCustomerId(customerId);

        return orderItems.stream().map(orderItem -> mapToDTO(orderItem)).collect(Collectors.toList());

    }

    /**
     * get all orderItem objects as list, filtered by employeeId
     *
     * @param employeeId
     * @return
     */
    public List<OrderItemDto> getOrderItemsByEmployeeId(Long employeeId) {

        List<OrderItem> orderItems = orderItemRepository.findByEmployeeId(employeeId);

        return orderItems.stream().map(orderItem -> mapToDTO(orderItem)).collect(Collectors.toList());

    }

    /**
     * get one orderItem object
     *
     * @param id
     * @return OrderItemDto
     */
    public OrderItemDto getOrderItemById(Long id) {

        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", id));

        return mapToDTO(orderItem);

    }

    /**
     * update an existing orderItem object
     *
     * @param orderItemId
     * @param orderItemDtoRequest
     * @return
     */
    public OrderItemDto updateOrderItemById(Long orderItemId, OrderItemDto orderItemDtoRequest) {

        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new ResourceNotFoundException("OrderItem", "id", orderItemId));

        orderItem.setCommissionLevel(orderItemDtoRequest.getCommissionLevel());
        orderItem.setEndDate(orderItemDtoRequest.getEndDate());
        orderItem.setQuantity(orderItemDtoRequest.getQuantity());
        orderItem.setStartDate(orderItemDtoRequest.getStartDate());

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);

        return mapToDTO(updatedOrderItem);

    }

    /**
     * delete an existing orderItem object
     *
     * @param orderItemId
     */
    public void deleteOrderItemById(Long orderItemId) {

        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new ResourceNotFoundException("OrderItem", "id", orderItemId));

        orderItemRepository.delete(orderItem);

    }

    /**
     * delete an existing orderItem object, test
     *
     * @param bookId
     * @param customerId
     * @param employeeId
     */
    public void deleteOrderItemByRef(Long bookId, Long customerId, Long employeeId) {

        List<OrderItem> orderItems = new ArrayList<>();

        bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "id", bookId));

        customerRepository.findById(customerId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "id", customerId));

        employeeRepository.findById(employeeId).orElseThrow(()
                -> new ResourceNotFoundException("Employee", "id", employeeId));
        try {
            orderItems = orderItemRepository.findAll().stream()
                    .filter(obj -> bookRepository.existsById(bookId))
                    .filter(obj -> customerRepository.existsById(customerId))
                    .filter(obj -> employeeRepository.existsById(employeeId))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Resource", "id", employeeId);
        }

        if (!(orderItems.get(0).getBook().getId().equals(bookId)
                && orderItems.get(0).getCustomer().getId().equals(customerId)
                && orderItems.get(0).getEmployee().getId().equals(employeeId))) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Book, Employee and Customer do not mtach");
        }

        orderItemRepository.delete(orderItems.get(0));

    }

    /**
     * convert Entity to DTO
     *
     * @param orderItem
     * @return
     */
    private OrderItemDto mapToDTO(OrderItem orderItem) {
        OrderItemDto orderItemDto = mapper.map(orderItem, OrderItemDto.class);
        return orderItemDto;
    }

    /**
     * convert Dto to Entity
     *
     * @param orderItemDto
     * @return
     */
    private OrderItem mapToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = mapper.map(orderItemDto, OrderItem.class);
        return orderItem;
    }

}
