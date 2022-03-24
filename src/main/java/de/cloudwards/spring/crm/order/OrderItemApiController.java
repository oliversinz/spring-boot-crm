package de.cloudwards.spring.crm.order;

import de.cloudwards.spring.crm.utils.AppConstants;
import java.util.List;
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
 * RestController implementation for OrderItem class
 */
@RestController
@RequestMapping("/api")
public class OrderItemApiController {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * get all orderItem objects as OrderItemResponse
     *
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return OrderItemResponse
     */
    @GetMapping("/orders")
    public OrderItemResponse getAllOrderItems(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return orderItemService.getAllOrderItems(pageNo, pageSize, sortBy, sortDir);

    }

    /**
     * get all orderItem objects as list, filtered by bookId
     *
     * @param id
     * @return List<OrderItemDto>
     */
    @GetMapping("/books/{bookId}/orders")
    public List<OrderItemDto> getOrderItemsByBookId(@PathVariable(name = "bookId") Long id) {

        return orderItemService.getOrderItemsByBookId(id);

    }

    /**
     * get all orderItem objects as list, filtered by customerId
     *
     * @param id
     * @return List<OrderItemDto>
     */
    @GetMapping("/customers/{customerId}/orders")
    public List<OrderItemDto> getOrderItemsByCustomerId(@PathVariable(name = "customerId") Long id) {

        return orderItemService.getOrderItemsByCustomerId(id);

    }

    /**
     * get all orderItem objects as list, filtered by employeeId
     *
     * @param id
     * @return List<OrderItemDto>
     */
    @GetMapping("/employees/{employeeId}/orders")
    public List<OrderItemDto> getOrderItemsByEmployeeId(@PathVariable(name = "employeeId") Long id) {

        return orderItemService.getOrderItemsByEmployeeId(id);

    }

    /**
     * get one orderItem object
     *
     * @param id
     * @return ResponseEntity<OrderItemDto>
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(orderItemService.getOrderItemById(id), HttpStatus.OK);

    }

    /**
     * create a new orderItem object specifying bookId, customerId, employeeId
     *
     * @param bookId
     * @param customerId
     * @param employeeId
     * @param orderItemDto
     * @return ResponseEntity<OrderItemDto>
     */
    @PostMapping("/orders/{bookId}/{customerId}/{employeeId}")
    public ResponseEntity<OrderItemDto> createOrderItem(
            @PathVariable(value = "bookId") Long bookId,
            @PathVariable(value = "customerId") Long customerId,
            @PathVariable(value = "employeeId") Long employeeId,
            @Valid @RequestBody OrderItemDto orderItemDto) {
        return new ResponseEntity<>(orderItemService.createOrderItem(bookId, customerId, employeeId, orderItemDto), HttpStatus.CREATED);
    }

    /**
     * update an existing orderItem object
     *
     * @param orderItemId
     * @param orderItemDto
     * @return ResponseEntity<OrderItemDto>
     */
    @PutMapping("/orders/{orderItemId}")
    public ResponseEntity<OrderItemDto> updateOrderItemById(
            @PathVariable(name = "orderItemId") Long orderItemId, @Valid @RequestBody OrderItemDto orderItemDto) {

        OrderItemDto updatedOrderItem = orderItemService.updateOrderItemById(orderItemId, orderItemDto);

        return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);

    }

    /**
     * delete an existing orderItem object
     *
     * @param orderItemId
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/orders/{orderItemId}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable(value = "orderItemId") Long orderItemId) {

        orderItemService.deleteOrderItemById(orderItemId);

        return new ResponseEntity<>("OrderItem deleted successfully", HttpStatus.OK);

    }

    /**
     * delete an existing orderItem object, test
     *
     * @param bookId
     * @param customerId
     * @param employeeId
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/orders/{bookId}/{customerId}/{employeeId}")
    public ResponseEntity<String> deleteOrderItemByRef(
            @PathVariable(value = "bookId") Long bookId,
            @PathVariable(value = "customerId") Long customerId,
            @PathVariable(value = "employeeId") Long employeeId) {

        orderItemService.deleteOrderItemByRef(bookId, customerId, employeeId);

        return new ResponseEntity<>("OrderItem deleted successfully", HttpStatus.OK);

    }

}
