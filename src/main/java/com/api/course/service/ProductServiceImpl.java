package com.api.course.service;

import com.api.course.dto.SaveProduct;
import com.api.course.entity.Category;
import com.api.course.entity.Product;
import com.api.course.exception.ObjectNotFoundException;
import com.api.course.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
@Autowired
private ProductRepository productRepository;
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOneById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createOne(SaveProduct saveProduct) {
        Product product = new Product();
        product.setName(saveProduct.getName());
        product.setPrice(saveProduct.getPrice());
        product.setStatus(Product.ProductSatus.ENABLED);
        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updateOneById(Long productId, SaveProduct saveProduct) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product with id " + productId + " not found"));
        productFromDB.setName(saveProduct.getName());
        productFromDB.setPrice(saveProduct.getPrice());
        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        productFromDB.setCategory(category);
        return productRepository.save(productFromDB);

    }

    @Override
    public Product disableOneById(Long productId) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product with id " + productId + " not found"));
        productFromDB.setStatus(Product.ProductSatus.DISABLED);
        return productRepository.save(productFromDB);
    }
}
