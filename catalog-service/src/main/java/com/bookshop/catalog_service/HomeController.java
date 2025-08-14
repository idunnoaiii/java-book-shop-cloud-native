package com.bookshop.catalog_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("home")
public class HomeController {

    @GetMapping("/")
    public String getGreeting() {
        return "Welcome to the book catalog";
    }
}
