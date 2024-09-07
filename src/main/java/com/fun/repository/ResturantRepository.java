package com.fun.repository;

import com.fun.model.Resturant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResturantRepository extends JpaRepository<Resturant,Long> {
   @Query("select * from Resturant r where lower(r.name) like lower(concat('%',:query,'%') " +
           "or lower(r.cuisineType) like lower(concat('%',:query,'%'))")
    List<Resturant> findBySearchQuery(String query);
    Resturant findByOwnerId(Long userId);

}
