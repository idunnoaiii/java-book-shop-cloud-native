package com.bookshop.order_service.book;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BookClient {
  private final WebClient webClient;
  private static final String BOOKS_PATH = "/books/";

  public BookClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Book> getBookByIsbn(String isbn) {
    return webClient.get().uri(BOOKS_PATH + isbn).retrieve().bodyToMono(Book.class);
  }
}
