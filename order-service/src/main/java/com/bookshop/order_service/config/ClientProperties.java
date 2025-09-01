package com.bookshop.order_service.config;

import jakarta.validation.constraints.NotNull;
import java.net.URI;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "shop")
public record ClientProperties(@NotNull URI catalogServiceUri) {}
