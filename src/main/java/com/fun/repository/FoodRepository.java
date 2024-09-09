package com.fun.repository;

import com.fun.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByResturantId(Long restaurantId);
    @Query("select f from Food f where f.name like %:keyword% or f.foodCategory.name like %:keyword% ")
    List<Food> searchFood(@Param("keyword") String keyword);
}
