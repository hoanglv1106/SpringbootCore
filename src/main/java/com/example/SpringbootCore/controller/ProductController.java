package com.example.SpringbootCore.controller;

import com.example.SpringbootCore.dto.request.ProductRequest;
import com.example.SpringbootCore.dto.response.ProductResponse;
import com.example.SpringbootCore.entity.Product;
import com.example.SpringbootCore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import  org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {


        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/{id}")
    public ProductResponse GetProductByID(@Valid @PathVariable  long id){

        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductResponse>  CreateProduct(@Valid @RequestBody ProductRequest productRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse>  UpdateProduct( @PathVariable long id ,@Valid @RequestBody ProductRequest productRequest){

        return ResponseEntity.ok(productService.updateProduct(id, productRequest)) ;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String name){
        return ResponseEntity.ok(productService.getProductsByName(name));
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<List<ProductResponse>> searchByCategory(@Valid @RequestParam String Category){
        return ResponseEntity.ok(productService.getProductsByCategory(Category));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteProduct(@PathVariable long id){
       productService.deleteProduct(id);
       return ResponseEntity.noContent().build();

    }
}
