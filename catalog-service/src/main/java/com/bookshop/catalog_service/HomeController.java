package com.bookshop.catalog_service;

import com.bookshop.catalog_service.config.ShopProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("home")
public class HomeController {

  private final ShopProperties shopProperties;

  public HomeController(ShopProperties shopProperties) {
    this.shopProperties = shopProperties;
  }

  @GetMapping("/")
  public String getGreeting() {
    return shopProperties.getGreeting();
  }
}
