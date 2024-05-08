package com.api.course.service;

import com.api.course.dto.SaveCategory;
import com.api.course.entity.Category;
import com.api.course.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findOneById(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category createOne(SaveCategory saveCategory) {
        return null;
    }

    @Override
    public Category updateOneById(Long categoryId, SaveCategory saveCategory) {
        return null;
    }

    @Override
    public Category disableOneById(Long categoryId) {
        return null;
    }
}
