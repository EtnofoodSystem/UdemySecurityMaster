package com.api.course.service;

import com.api.course.dto.SaveProduct;
import com.api.course.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findOneById(long id);
    Product createOne(SaveProduct saveProduct);
    Product updateOneById(Long productId, SaveProduct saveProduct);
    Product disableOneById(Long productId);
}
