package com.fun.repository;

import com.fun.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    @Query("select r from Restaurant r where lower(r.name) like lower(concat('%', :query, '%')) " +
            "or lower(r.cuisineType) like lower(concat('%', :query, '%'))")
    List<Restaurant> findBySearchQuery(String query);


    Restaurant findByOwnerId(Long userId);

}
