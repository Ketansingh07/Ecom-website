package com.angularspringbootecommerce.backend.dtos;

import lombok.Data;

import java.math.BigDecimal;

import jakarta.validation.constraints.Size;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
}
