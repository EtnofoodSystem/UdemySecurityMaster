package com.api.course.service;

import com.api.course.dto.SaveCategory;
import com.api.course.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface CategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(long categoryId);

    Category createOne(SaveCategory saveCategory);

    Category updateOneById(Long categoryId, SaveCategory saveCategory);

    Category disableOneById(Long categoryId);
}
