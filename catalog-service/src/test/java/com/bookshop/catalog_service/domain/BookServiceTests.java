package com.bookshop.catalog_service.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

  @Mock private BookRepository bookRepository;

  @InjectMocks private BookService bookService;

  @Test
  void whenBookToCreateAlreadyExistsThenThrows() {
    var bookIsbn = "1234561232";
    var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
    when(bookRepository.isExistByIsbn(bookIsbn)).thenReturn(true);
    assertThatThrownBy(() -> bookService.addToCatalog(bookToCreate))
        .isInstanceOf(BookAlreadyExistsException.class)
        .hasMessage("A book with ISBN " + bookIsbn + " already exists");
  }

  @Test
  void whenBookToReadDoesNotExistThenThrows() {
    var bookIsbn = "1234561232";
    when(bookRepository.findByIsbn(bookIsbn)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> bookService.getDetails(bookIsbn))
        .isInstanceOf(BookNotFoundException.class)
        .hasMessage("Could not find book with ISBN " + bookIsbn);
  }
}
