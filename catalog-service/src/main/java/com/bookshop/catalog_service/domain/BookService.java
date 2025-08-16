package com.bookshop.catalog_service.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> getList() {
    return bookRepository.findAll();
  }

  public Book getDetails(String isbn) {
    return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
  }

  public Book addToCatalog(Book book) {
    if (bookRepository.isExistByIsbn(book.isbn())) {
      throw new BookAlreadyExistsException(book.isbn());
    }
    return bookRepository.save(book);
  }

  public void remove(String isbn) {
    bookRepository.deleteByIsbn(isbn);
  }

  public Book editDetails(String isbn, Book book) {
    return bookRepository
        .findByIsbn(isbn)
        .map(
            existingBook -> {
              var bookToUpdate =
                  new Book(existingBook.isbn(), book.title(), book.author(), book.price());
              return bookRepository.save(bookToUpdate);
            })
        .orElseThrow(() -> new BookNotFoundException(isbn));
  }
}
