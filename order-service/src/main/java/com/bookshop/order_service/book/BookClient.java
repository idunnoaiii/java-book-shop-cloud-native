package com.bookshop.order_service.book;

import java.time.Duration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Component
public class BookClient {
  private final WebClient webClient;
  private static final String BOOKS_PATH = "/books/";

  public BookClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Book> getBookByIsbn(String isbn) {
    return webClient
        .get()
        .uri(BOOKS_PATH + isbn)
        .retrieve()
        .bodyToMono(Book.class)
        .timeout(Duration.ofSeconds(3), Mono.empty())
        .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
        .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
        .onErrorResume(Exception.class, exception -> Mono.empty());
  }
}
