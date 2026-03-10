package com.example.SpringbootCore.repository;

import com.example.SpringbootCore.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);
    List<Product> findByCategory(String category);
    List<Product>  findAllBy();

    boolean existsByName(String name);
}
