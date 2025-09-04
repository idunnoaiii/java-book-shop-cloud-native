package com.bookshop.order_service;

import com.bookshop.order_service.book.BookClient;
import java.io.IOException;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class BookClientTests {
  private MockWebServer mockWebServer;
  private BookClient bookClient;

  @BeforeEach
  void setup() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();
    var webClient = WebClient.builder().baseUrl(mockWebServer.url("/").uri().toString()).build();
    this.bookClient = new BookClient(webClient);
  }

  @AfterEach
  void clean() throws IOException {
    this.mockWebServer.shutdown();
  }

  @Test
  public void whenBookExistsThenReturnBook() {
    String bookIsbn = "1234567890";

    var mockResponse =
        new MockResponse()
            .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(
                """
                {
                  "isbn": "%s",
                  "title": "Title",
                  "author": "Author",
                  "price": 9.90,
                  "publisher": "Prentice Hall"
                }
                """
                    .formatted(bookIsbn));

    mockWebServer.enqueue(mockResponse);

    var book = bookClient.getBookByIsbn(bookIsbn);

    StepVerifier.create(book).expectNextMatches(b -> b.isbn().equals(bookIsbn)).verifyComplete();
  }
}
