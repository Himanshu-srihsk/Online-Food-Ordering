package com.fun.repository;

import com.fun.model.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsCategoryRepository extends JpaRepository<IngredientsCategory, Long> {
    List<IngredientsCategory> findByResturantId(Long id);
}
