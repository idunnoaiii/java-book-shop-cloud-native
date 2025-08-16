package com.bookshop.catalog_service.domain;

import java.util.Optional;

public interface BookRepository {
  Iterable<Book> findAll();

  Optional<Book> findByIsbn(String isbn);

  boolean isExistByIsbn(String isbn);

  Book save(Book book);

  void deleteByIsbn(String isbn);
}
