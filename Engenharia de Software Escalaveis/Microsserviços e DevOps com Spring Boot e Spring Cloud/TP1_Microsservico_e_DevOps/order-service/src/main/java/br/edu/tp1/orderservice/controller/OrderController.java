package br.edu.tp1.orderservice.controller;

import br.edu.tp1.orderservice.client.Product;
import br.edu.tp1.orderservice.client.ProductClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/orders")
    public List<Product> listProductsFromOrder() {
        return productClient.getAllProducts();
    }
}
