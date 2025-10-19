package br.edu.tp1.productservice.repository;

import br.edu.tp1.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
