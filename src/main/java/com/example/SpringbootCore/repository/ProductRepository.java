package com.example.SpringbootCore.repository;

import com.example.SpringbootCore.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByname(String name);
    List<Product> findBycategory(String category);
    List<Product>  findAllBy();
}
