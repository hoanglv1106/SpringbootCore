package com.example.SpringbootCore.controller;

import com.example.SpringbootCore.dto.request.ProductRequest;
import com.example.SpringbootCore.dto.response.ProductResponse;
import com.example.SpringbootCore.entity.Product;
import com.example.SpringbootCore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse GetProductByID(@PathVariable  long id){

        return productService.getProductById(id);
    }

    @PostMapping
    public ProductResponse CreateProduct(@RequestBody ProductRequest productRequest){

        return productService.createProduct(productRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse UpdateProduct(@PathVariable long id , @RequestBody Product product){

        return productService.updateProduct(id, product);
    }

    @GetMapping("/search")
    public List<ProductResponse> SearchProducts(@RequestParam String name){
        return productService.getProductsByName(name);
    }

    @GetMapping("/searchByCategory")
    public List<ProductResponse> searchByCategory(@RequestParam String Category){
        return productService.getProductsByCategory(Category);
    }

    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable long id){
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return "Product with ID " + id + " deleted successfully.";
        } else {
            return "Product with ID " + id + " not found.";
        }


    }
}
