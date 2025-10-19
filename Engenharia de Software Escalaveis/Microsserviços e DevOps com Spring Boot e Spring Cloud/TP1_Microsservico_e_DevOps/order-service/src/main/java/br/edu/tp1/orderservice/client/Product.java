package br.edu.tp1.orderservice.client;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
}
