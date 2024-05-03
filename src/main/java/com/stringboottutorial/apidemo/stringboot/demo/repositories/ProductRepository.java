package com.stringboottutorial.apidemo.stringboot.demo.repositories;

import com.stringboottutorial.apidemo.stringboot.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
