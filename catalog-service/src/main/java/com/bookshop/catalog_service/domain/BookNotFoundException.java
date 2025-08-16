package com.bookshop.catalog_service.domain;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String isbn) {
    super("Could not find book with ISBN " + isbn);
  }
}
