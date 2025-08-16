package com.bookshop.catalog_service.web;

import com.bookshop.catalog_service.domain.Book;
import com.bookshop.catalog_service.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public Iterable<Book> get() {
    return bookService.getList();
  }

  @GetMapping("/{isbn}")
  public Book getByIsbn(@PathVariable String isbn) {
    return bookService.getDetails(isbn);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book post(@Valid @RequestBody Book book) {
    return bookService.addToCatalog(book);
  }

  @DeleteMapping("/{isbn}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String isbn) {
    bookService.remove(isbn);
  }

  @PutMapping("/{isbn}")
  public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
    return bookService.editDetails(isbn, book);
  }
}
