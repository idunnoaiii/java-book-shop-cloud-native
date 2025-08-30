package com.bookshop.order_service.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Flux<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Mono<Order> submitOrder(String bookIsbn, Integer quantity) {
    return Mono.just(buildRejectedOrder(bookIsbn, quantity)).flatMap(orderRepository::save);
  }

  private Order buildRejectedOrder(String bookIsbn, Integer quantity) {
    return Order.of(bookIsbn, null, null, quantity, OrderStatus.REJECTED);
  }
}
