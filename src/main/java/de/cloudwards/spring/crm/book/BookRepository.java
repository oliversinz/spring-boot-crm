package de.cloudwards.spring.crm.book;

/**
 * Jpa repository for Book class
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContaining(String search);

}
