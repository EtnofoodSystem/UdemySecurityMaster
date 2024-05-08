package com.api.course.repository;

import com.api.course.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public class CategoryRepository extends JpaRepository<Category, Long> {
}
