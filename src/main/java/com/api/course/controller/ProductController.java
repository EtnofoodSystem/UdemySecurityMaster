package com.api.course.controller;

import com.api.course.dto.SaveProduct;
import com.api.course.entity.Product;
import com.api.course.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        Page<Product> productsPage = productService.findAll(pageable);

        if (productsPage.hasContent()) {
            return ResponseEntity.ok(productsPage);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable long productId) {
        Optional<Product> product = productService.findOneById(productId);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid SaveProduct saveProduct) {
        Product product = productService.createOne(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId,
                                                 @RequestBody  @Valid SaveProduct saveProduct) {
        Product product = productService.updateOneById(productId,saveProduct);
        return ResponseEntity.ok(product);

    }
    @DeleteMapping("/{productId}/disable")
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId) {
        Product product = productService.disableOneById(productId);
        return ResponseEntity.ok(product);
    }

}
