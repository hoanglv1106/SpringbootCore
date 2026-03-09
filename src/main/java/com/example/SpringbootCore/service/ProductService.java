package com.example.SpringbootCore.service;

import com.example.SpringbootCore.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        // sample data
        products.add(new Product(1L, "Laptop", 999.99));
        products.add(new Product(2L, "Phone", 499.99));
        products.add(new Product(3L, "Tablet", 299.99));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(long id){
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product createProduct(Product product){
        products.add(product);
        return product;
    }

    public Product updateProduct(long id, Product updatedProduct){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    public boolean deleteProduct(long id){
        return products.removeIf(product -> product.getId() == id);
    }

}
