package com.example.SpringbootCore.service;

import com.example.SpringbootCore.dto.request.ProductRequest;
import com.example.SpringbootCore.dto.response.ProductResponse;
import com.example.SpringbootCore.entity.Product;
import com.example.SpringbootCore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.lang.Long;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAllBy().stream()
                .map(Product -> new ProductResponse(
                        Product.getId(),
                        Product.getName(),
                        Product.getPrice(),
                        Product.getCategory()
                ))
                .toList();
    }

    public ProductResponse getProductById(long id){

        return productRepository.findById(id)
                .map(Product -> new ProductResponse(
                        Product.getId(),
                        Product.getName(),
                        Product.getPrice(),
                        Product.getCategory()
                )).orElse(null
                );

    }

    public List<ProductResponse> getProductsByName(String name){
        return productRepository.findByname(name).stream()
                .map(Product -> new ProductResponse(
                        Product.getId(),
                        Product.getName(),
                        Product.getPrice(),
                        Product.getCategory()
                ))
                .toList();
    }

     public List<ProductResponse> getProductsByCategory(String category){
         return productRepository.findBycategory(category).stream()
                 .map(Product -> new ProductResponse(
                         Product.getId(),
                         Product.getName(),
                         Product.getPrice(),
                         Product.getCategory()
                 ))
                 .toList();
    }

    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getCategory()
        );
    }

    public ProductResponse updateProduct(long id, Product updatedProduct){
            Product existingProduct = productRepository.findById(id).orElse(null);
            if (existingProduct != null) {
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setCategory(updatedProduct.getCategory());
                return new ProductResponse(
                        existingProduct.getId(),
                        existingProduct.getName(),
                        existingProduct.getPrice(),
                        existingProduct.getCategory()
                );
            }

        return null;
    }

    public boolean deleteProduct(long id){

        if (productRepository.existsById(id)) {;
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
