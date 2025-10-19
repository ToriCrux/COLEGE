package br.edu.tp1.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<Product> getAllProducts();
}
