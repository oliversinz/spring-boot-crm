package de.cloudwards.spring.crm.book;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data transfer object for Book class
 */
public class BookDto {

    private Long id;

    @NotEmpty
    @Size(min = 5, message = "mindestens 5 Zeichen")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "mindestens 10 Zeichen")
    private String isbn;

    @NotNull
    @Digits(integer = 4, fraction = 0, message = "Format: 2020")
    private Integer pubYear;

    @NotNull
    @Digits(integer = 2, fraction = 2, message = "Format: 14.95")
    private Double price;

    public BookDto() {
    }

    public BookDto(String title, String isbn, Integer pubYear, Double price) {
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

    @Override
    public String toString() {
        return title;
    }

}
