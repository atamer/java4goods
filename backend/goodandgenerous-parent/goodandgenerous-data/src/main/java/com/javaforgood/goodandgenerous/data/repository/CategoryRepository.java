package com.javaforgood.goodandgenerous.data.repository;

import com.javaforgood.goodandgenerous.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
