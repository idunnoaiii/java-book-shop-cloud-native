package com.bookshop.catalog_service.persistence;

import com.bookshop.catalog_service.domain.Book;
import com.bookshop.catalog_service.domain.BookRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {

  private static final Map<String, Book> books = new ConcurrentHashMap<>();

  @Override
  public Iterable<Book> findAll() {
    return books.values();
  }

  @Override
  public Optional<Book> findByIsbn(String isbn) {
    return isExistByIsbn(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
  }

  @Override
  public boolean isExistByIsbn(String isbn) {
    return books.containsKey(isbn);
  }

  @Override
  public Book save(Book book) {
    books.put(book.isbn(), book);
    return book;
  }

  @Override
  public void deleteByIsbn(String isbn) {
    books.remove(isbn);
  }
}
