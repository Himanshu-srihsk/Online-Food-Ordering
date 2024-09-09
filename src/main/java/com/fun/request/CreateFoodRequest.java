package com.fun.request;

import com.fun.model.Category;
import com.fun.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long resturantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientsItem> ingredientsItems;
}
