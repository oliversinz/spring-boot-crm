package de.cloudwards.spring.crm.book;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.cloudwards.spring.crm.order.OrderItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Domain model for Book class
 */
@Entity
@Table(name = "books")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_isbn", nullable = false)
    private String isbn;

    @Column(name = "book_pub_year", nullable = false)
    private Integer pubYear;

    @Column(name = "book_price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "book-order")
    private List<OrderItem> orders = new ArrayList<>();

    public Book() {
    }

    public Book(String title, String isbn, Integer pubYear, Double price) {
        this.title = title;
        this.isbn = isbn;
        this.pubYear = pubYear;
        this.price = price;
    }

    public Book(Long id, String title, String isbn, Integer pubYear, Double price) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.pubYear = pubYear;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPubYear() {
        return pubYear;
    }

    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

}
