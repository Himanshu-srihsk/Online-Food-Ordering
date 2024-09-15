package com.fun.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class IngredientsCategoryRequest {
    private String name;
    private Long restaurantId;

}
