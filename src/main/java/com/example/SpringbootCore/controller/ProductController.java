package com.example.SpringbootCore.controller;

import com.example.SpringbootCore.dto.ApiResponse;
import com.example.SpringbootCore.dto.request.ProductRequest;
import com.example.SpringbootCore.dto.response.ProductResponse;
import com.example.SpringbootCore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        Page<ProductResponse> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @Valid @PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse product = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable long id,
            @Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(ApiResponse.success(productService.updateProduct(id, productRequest)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchProducts(
            @RequestParam String name) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductsByName(name)));
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchByCategory(
            @Valid @RequestParam String category) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductsByCategory(category)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        String message = getMessage("success.deleted");
        return ResponseEntity.ok(ApiResponse.success(200, message, null));
    }
}
