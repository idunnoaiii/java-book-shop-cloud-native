package com.bookshop.catalog_service.demo;

import com.bookshop.catalog_service.domain.Book;
import com.bookshop.catalog_service.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
/* Use profile as feature flag */
// @Profile("testdata")
/* Only load if the shop.testdata.enabled defined in application.yml */
@ConditionalOnProperty(name = "shop.testdata.enabled", havingValue = "true")
public class BookDataLoader {
  private final BookRepository bookRepository;

  public BookDataLoader(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void loadBookTestData() {
    var book1 = new Book("1234567891", "Northern Lights", "Lyra Belacqua", 9.90);
    var book2 = new Book("1234567892", "Polar Journey", "Iorek Byrnison", 12.90);
    bookRepository.save(book1);
    bookRepository.save(book2);
  }
}
