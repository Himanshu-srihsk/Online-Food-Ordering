package com.fun.repository;

import com.fun.model.Cart;
import com.fun.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
