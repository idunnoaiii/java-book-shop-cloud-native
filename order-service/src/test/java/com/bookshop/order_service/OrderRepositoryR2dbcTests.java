package com.bookshop.order_service;

import com.bookshop.order_service.config.DataConfig;
import com.bookshop.order_service.domain.OrderRepository;
import com.bookshop.order_service.domain.OrderService;
import com.bookshop.order_service.domain.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(DataConfig.class)
@Testcontainers
public class OrderRepositoryR2dbcTests {

  @Container
  static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:latest");

  @Autowired private OrderRepository orderRepository;

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.r2dbc.url", OrderRepositoryR2dbcTests::r2dbcUrl);
    registry.add("spring.r2dbc.username", postgresql::getUsername);
    registry.add("spring.r2dbc.password", postgresql::getPassword);
    registry.add("spring.flyway.url", postgresql::getJdbcUrl);
  }

  private static String r2dbcUrl() {
    return String.format(
        "r2dbc:postgresql://%s:%s/%s",
        postgresql.getHost(), postgresql.getFirstMappedPort(), postgresql.getDatabaseName());
  }

  @Test
  public void createRejectOrder() {
    var rejectedOrder = OrderService.buildRejectedOrder("1234567890", 3);
    StepVerifier.create(orderRepository.save(rejectedOrder))
        .expectNextMatches(order -> order.status().equals(OrderStatus.REJECTED))
        .verifyComplete();
  }
}
