package com.example.SpringbootCore.service;

import com.example.SpringbootCore.dto.request.ProductRequest;
import com.example.SpringbootCore.dto.response.ProductResponse;
import com.example.SpringbootCore.entity.Product;
import com.example.SpringbootCore.exception.AppException;
import com.example.SpringbootCore.exception.ErrorCode;
import com.example.SpringbootCore.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(Product -> new ProductResponse(
                        Product.getId(),
                        Product.getName(),
                        Product.getPrice(),
                        Product.getCategory()
                ));

    }

    public ProductResponse getProductById(long id){

        return productRepository.findById(id)
                .map(Product -> new ProductResponse(
                        Product.getId(),
                        Product.getName(),
                        Product.getPrice(),
                        Product.getCategory()
                )).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

    }

    public List<ProductResponse> getProductsByName(String name){
        List<Product> products = productRepository.findByName(name);

        if(products.isEmpty()){
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        return products.stream()
                .map(Product -> new ProductResponse(
                        Product.getId(),
                        Product.getName(),
                        Product.getPrice(),
                        Product.getCategory()
                ))
                .toList();
    }

     public List<ProductResponse> getProductsByCategory(String category){
         List<Product> products = productRepository.findByCategory(category);
         if(products.isEmpty()){
             throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
         }
         return products.stream()
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

        if (productRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        }
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

    public ProductResponse updateProduct(long id, ProductRequest request) {
            Product existingProduct = productRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

                existingProduct.setName(request.getName());
                existingProduct.setPrice(request.getPrice());
                existingProduct.setCategory(request.getCategory());
                return new ProductResponse(
                        existingProduct.getId(),
                        existingProduct.getName(),
                        existingProduct.getPrice(),
                        existingProduct.getCategory()
                );

    }

    public void deleteProduct(long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productRepository.delete(product);
    }

}
