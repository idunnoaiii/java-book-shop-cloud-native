package com.bookshop.order_service.domain;

import com.bookshop.order_service.book.Book;
import com.bookshop.order_service.book.BookClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final BookClient bookClient;

  public OrderService(OrderRepository orderRepository, BookClient bookClient) {
    this.orderRepository = orderRepository;
    this.bookClient = bookClient;
  }

  public Flux<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Mono<Order> submitOrder(String bookIsbn, Integer quantity) {
    // return Mono.just(buildRejectedOrder(bookIsbn, quantity)).flatMap(orderRepository::save);
    return bookClient
        .getBookByIsbn(bookIsbn)
        .map(book -> buildAcceptedOrder(book, quantity))
        .defaultIfEmpty(buildRejectedOrder(bookIsbn, quantity))
        .flatMap(orderRepository::save);
  }

  private Order buildAcceptedOrder(Book book, Integer quantity) {
    return Order.of(
        book.isbn(),
        book.title() + " - " + book.author(),
        book.price(),
        quantity,
        OrderStatus.ACCEPTED);
  }

  private Order buildRejectedOrder(String bookIsbn, Integer quantity) {
    return Order.of(bookIsbn, null, null, quantity, OrderStatus.REJECTED);
  }
}
