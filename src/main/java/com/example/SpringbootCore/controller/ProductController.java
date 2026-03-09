package com.example.SpringbootCore.controller;

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
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product GetProductByID(@PathVariable  long id){

        return productService.getProductById(id);
    }

    @PostMapping
    public Product CreateProduct(@RequestBody Product product){

        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product UpdateProduct(@PathVariable long id , @RequestBody Product product){

        return productService.updateProduct(id, product);
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
