package com.bookshop.order_service.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
    @NotBlank(message = "The book ISBN must be provided") String isbn,
    @NotNull(message = "The book quantity must be provided")
        @Min(value = 1, message = "The book quantity must be greater than zero")
        @Max(value = 5, message = "The book quantity must be less than or equal to 5")
        Integer quantity) {}
