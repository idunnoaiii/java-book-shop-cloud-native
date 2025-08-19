package com.bookshop.catalog_service.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookshop.catalog_service.config.DataConfig;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {
  @Autowired private BookRepository bookRepository;
  @Autowired private JdbcAggregateTemplate jdbcAggregateTemplate;

  @Test
  public void findBookByIsbnWhenExisting() {
    var bookIsbn = "1234561237";
    var book = Book.of(bookIsbn, "Title", "Author", 12.90);
    jdbcAggregateTemplate.insert(book);

    Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

    assertThat(actualBook).isNotNull();
    assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
  }
}
